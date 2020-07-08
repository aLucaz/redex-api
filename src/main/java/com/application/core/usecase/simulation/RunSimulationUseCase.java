package com.application.core.usecase.simulation;

import com.application.core.model.dto.PathDto;
import com.application.core.model.dto.RouteDto;
import com.application.core.model.dto.ShipmentRequestDto;
import com.application.core.usecase.shipment.GenerateRouteUseCase;
import com.application.data.gateway.BranchGateway;
import com.application.data.gateway.EtFlightGateway;
import com.application.data.gateway.ShipmentGateway;
import com.application.data.gateway.ShipmentStateGateway;
import com.application.data.parser.RouteParser;
import com.application.data.util.reading.CsvReader;
import com.application.data.util.reading.CsvReaderImpl;
import com.application.shared.Constant;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class RunSimulationUseCase {
    private final CsvReader csvReader;
    private final BranchGateway branchGateway;
    private final GenerateRouteUseCase generateRouteUseCase;
    private final ShipmentGateway shipmentGateway;
    private final ShipmentStateGateway shipmentStateGateway;
    private final EtFlightGateway etFlightGateway;

    public RunSimulationUseCase(CsvReaderImpl csvReader, BranchGateway branchGateway,
                                GenerateRouteUseCase generateRouteUseCase, ShipmentGateway shipmentGateway,
                                ShipmentStateGateway shipmentStateGateway, EtFlightGateway etFlightGateway) {
        this.csvReader = csvReader;
        this.branchGateway = branchGateway;
        this.generateRouteUseCase = generateRouteUseCase;
        this.shipmentGateway = shipmentGateway;
        this.shipmentStateGateway = shipmentStateGateway;
        this.etFlightGateway = etFlightGateway;
    }

    public void execute(MultipartFile file) {
        List<ShipmentRequestDto> shipmentRequestDtoList = addContinentInformation(csvReader.readShipmentRequestZip(file));
        // now we have to proccess all the requests one by one and create the list of tripPlans
        for (ShipmentRequestDto request : shipmentRequestDtoList) {
            PathDto pathDto = generateRouteUseCase.execute(RouteParser.mapToDto(request), Constant.IS_A_SIMULATION);
            if (pathDto != null) {
                shipmentGateway.persistWithPath(pathDto, shipmentStateGateway.getDefaultShipmentState(Constant.DEFAULT_SHIPMENT_STATE_SIMULATION));
                for (RouteDto route : pathDto.getTripPlan()) {
                    etFlightGateway.updateQuantity(route.getFlightFriendlyId());
                }
            }
        }
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
}
