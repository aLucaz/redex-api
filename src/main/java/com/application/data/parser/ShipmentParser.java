package com.application.data.parser;

import com.application.core.model.business.Branch;
import com.application.core.model.business.Shipment;
import com.application.core.model.business.ShipmentForBranch;
import com.application.core.model.business.ShipmentState;
import com.application.core.model.dto.PathDto;
import com.application.core.model.dto.RouteDto;
import com.application.core.model.dto.ShipmentDto;
import com.application.rest.api.request.registerShipment.RegisterShipmentRequest;
import com.application.shared.Constant;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ShipmentParser {
    public static ShipmentDto mapToDto(RegisterShipmentRequest request) {
        return new ShipmentDto()
                .setPrice(request.getPrice())
                .setLastModifiedBy(request.getLastModifiedBy())
                .setRegisteredBy(request.getRegisteredBy());
    }

    public static Shipment mapToRow(ShipmentDto shipmentDto) {
        return new Shipment()
                .setPrice(shipmentDto.getPrice())
                .setLastModifiedBy(shipmentDto.getLastModifiedBy())
                .setLastModifiedDate(LocalDateTime.now())
                .setRegisteredBy(shipmentDto.getRegisteredBy())
                .setRegisteredDate(LocalDateTime.now())
                .setReferenceCode(shipmentDto.getReferenceCode())
                .setIsActive(Constant.IS_ACTIVE)
                .setIsSimulated(Constant.IS_NOT_A_SIMULATION);
    }

    public static ShipmentDto mapToDto(Shipment shipment) {
        return new ShipmentDto()
                .setPrice(shipment.getPrice())
                .setIdShipment(shipment.getIdShipment())
                .setReferenceCode(shipment.getReferenceCode());
    }

    public static List<Shipment> mapToRowList(List<PathDto> pathDtoList, Integer idShipmentState) {
        List<Shipment> shipmentList = new ArrayList<>();
        for (PathDto pathDto : pathDtoList) {
            if (pathDto != null) {
                Shipment shipment = new Shipment()
                        .setPrice(pathDto.getPrice())
                        .setLastModifiedBy(Constant.DEFAULT_USER_REGISTRATOR)
                        .setLastModifiedDate(LocalDateTime.now())
                        .setRegisteredBy(Constant.DEFAULT_USER_REGISTRATOR)
                        .setRegisteredDate(LocalDateTime.now())
                        .setIsSimulated(Constant.IS_A_SIMULATION)
                        .setIsActive(Constant.IS_ACTIVE);
                // now we create the shipment for branches
                Set<ShipmentForBranch> shipmentForBranchSet = new HashSet<>();
                for (RouteDto routeDto : pathDto.getTripPlan()) {
                    shipmentForBranchSet.add(new ShipmentForBranch()
                            .setShipment(shipment)
                            .setBranch(new Branch().setIdBranch(routeDto.getStartCityId()))
                            .setShipmentState(new ShipmentState().setIdShipmentState(idShipmentState))
                            .setCurrentArrivalDateTime(routeDto.getCurrentArrivalDateTime())
                            .setCurrentDepartureDateTime(routeDto.getCurrentDepartureDateTime())
                            .setFutureArrivalDateTime(routeDto.getFutureArrivalDateTime())
                            .setFlightFriendlyId(routeDto.getFlightFriendlyId())
                            .setSequence(routeDto.getSequence()));
                }
                // now add the set to the created shipment
                shipment.setShipmentForBranches(shipmentForBranchSet);
                // now add the shipment to the list
                shipmentList.add(shipment);
            }
        }
        return shipmentList;
    }
}
