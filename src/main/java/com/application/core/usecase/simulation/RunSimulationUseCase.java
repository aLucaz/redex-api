package com.application.core.usecase.simulation;

import com.application.core.model.dto.PathDto;
import com.application.core.model.dto.ShipmentRequestDto;
import com.application.core.usecase.shipment.GenerateRouteUseCase;
import com.application.data.gateway.BranchGateway;
import com.application.data.gateway.ShipmentForBranchGateway;
import com.application.data.gateway.ShipmentGateway;
import com.application.data.gateway.ShipmentStateGateway;
import com.application.data.parser.RouteParser;
import com.application.data.util.reading.CsvReader;
import com.application.data.util.reading.CsvReaderImpl;
import com.application.shared.Constant;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RunSimulationUseCase {
    private final CsvReader csvReader;
    private final BranchGateway branchGateway;
    private final GenerateRouteUseCase generateRouteUseCase;
    private final ShipmentGateway shipmentGateway;
    private final ShipmentStateGateway shipmentStateGateway;

    public RunSimulationUseCase(CsvReaderImpl csvReader, BranchGateway branchGateway, GenerateRouteUseCase generateRouteUseCase, ShipmentGateway shipmentGateway, ShipmentStateGateway shipmentStateGateway) {
        this.csvReader = csvReader;
        this.branchGateway = branchGateway;
        this.generateRouteUseCase = generateRouteUseCase;
        this.shipmentGateway = shipmentGateway;
        this.shipmentStateGateway = shipmentStateGateway;
    }

    public void execute(MultipartFile file) {
        List<ShipmentRequestDto> shipmentRequestDtoList = addContinentInformation(csvReader.readShipmentRequestCsv(file));
        // now we have to proccess all the requests one by one and create the list of tripPlans
        List<PathDto> pathDtoList = shipmentRequestDtoList
                .stream()
                .map(shipmentRequestDto -> generateRouteUseCase.execute(RouteParser.mapToDto(shipmentRequestDto), Constant.IS_A_SIMULATION))
                .collect(Collectors.toList());
        // now save all the trip plans
        shipmentGateway.saveAllSimulatedShipments(pathDtoList, shipmentStateGateway.getDefaultShipmentState(Constant.DEFAULT_SHIPMENT_STATE_SIMULATION));
//        System.out.println("go");
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
