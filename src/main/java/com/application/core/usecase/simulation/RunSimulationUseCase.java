package com.application.core.usecase.simulation;

import com.application.core.model.dto.IncidentDto;
import com.application.core.model.dto.PathDto;
import com.application.core.model.dto.ShipmentDto;
import com.application.core.model.dto.ShipmentForBranchDto;
import com.application.core.model.dto.ShipmentRequestDto;
import com.application.core.usecase.shipment.GenerateRouteUseCase;
import com.application.core.usecase.util.detector.incident.IncidentDetector;
import com.application.core.usecase.util.detector.incident.IncidentDetectorImpl;
import com.application.data.gateway.BranchGateway;
import com.application.data.gateway.IncidentGateway;
import com.application.data.gateway.ShipmentGateway;
import com.application.data.gateway.ShipmentStateGateway;
import com.application.data.parser.RouteParser;
import com.application.data.util.reading.CsvReader;
import com.application.data.util.reading.CsvReaderImpl;
import com.application.shared.Constant;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RunSimulationUseCase {
    private final CsvReader csvReader;
    private final BranchGateway branchGateway;
    private final GenerateRouteUseCase generateRouteUseCase;
    private final ShipmentGateway shipmentGateway;
    private final ShipmentStateGateway shipmentStateGateway;
    private final IncidentDetector incidentDetector;
    private final IncidentGateway incidentGateway;

    public RunSimulationUseCase(CsvReaderImpl csvReader, BranchGateway branchGateway,
                                GenerateRouteUseCase generateRouteUseCase, ShipmentGateway shipmentGateway,
                                ShipmentStateGateway shipmentStateGateway, IncidentDetectorImpl incidentDetector,
                                IncidentGateway incidentGateway) {
        this.csvReader = csvReader;
        this.branchGateway = branchGateway;
        this.generateRouteUseCase = generateRouteUseCase;
        this.shipmentGateway = shipmentGateway;
        this.shipmentStateGateway = shipmentStateGateway;
        this.incidentDetector = incidentDetector;
        this.incidentGateway = incidentGateway;
    }

    public void execute(MultipartFile file) {
        List<ShipmentRequestDto> shipmentRequestDtoList = addContinentInformation(csvReader.readShipmentRequestZip(file));
        // now we have to proccess all the requests one by one and create the list of tripPlans
        List<PathDto> pathDtoList = shipmentRequestDtoList
                .stream()
                .map(shipmentRequestDto -> generateRouteUseCase.execute(RouteParser.mapToDto(shipmentRequestDto), Constant.IS_A_SIMULATION))
                .collect(Collectors.toList());
        // now save all the trip plans
        shipmentGateway.saveAllSimulatedShipments(pathDtoList, shipmentStateGateway.getDefaultShipmentState(Constant.DEFAULT_SHIPMENT_STATE_SIMULATION));
        // once all the trip plans are saved, we obtain the incidents
        List<IncidentDto> incidents = getAllIncidentsSimulated();
        // and then save them
        if (incidents != null) incidentGateway.saveAllSimulated(incidents);
    }

    public List<ShipmentRequestDto> addContinentInformation(List<ShipmentRequestDto> shipmentRequestDtoList) {
        // foeach shipment add the SameContinet value
        shipmentRequestDtoList.forEach(shipmentRequestDto -> {
            boolean sameContinent = branchGateway.findContinentOf(shipmentRequestDto.getFrom())
                    .equals(branchGateway.findContinentOf(shipmentRequestDto.getTo()));
            if (sameContinent) shipmentRequestDto.setSameContinent(Constant.TRUE);
            else shipmentRequestDto.setSameContinent(Constant.FALSE);
        });
        return shipmentRequestDtoList;
    }

    public List<IncidentDto> getAllIncidentsSimulated() {
        List<ShipmentDto> shipmentDtoList = shipmentGateway.getAllSimulatedShipments();
        List<ShipmentForBranchDto> shipmentForBranchDtoList = new ArrayList<>();
        shipmentDtoList.forEach(shipmentDto -> {
            shipmentForBranchDtoList.addAll(shipmentDto.getShipmentForBranches());
        });
        // now we collect all the incidents in all the routes
        List<IncidentDto> incidentDtoList = new ArrayList<>();
        for (ShipmentForBranchDto shipmentForBranchDto : shipmentForBranchDtoList) {
            // detect if when i arrived an incident ocurred
            IncidentDto incident = incidentDetector.detectIncident(
                    shipmentForBranchDtoList,
                    shipmentForBranchDto.getCurrentArrivalDateTime(),
                    branchGateway.findById(shipmentForBranchDto.getIdBranch())
            );
            if (incident != null) {
                incidentDtoList.add(incident.setFlightFriendlyId(shipmentForBranchDto.getFlightFriendlyId()));
            }
        }
        return incidentDtoList;
    }
}
