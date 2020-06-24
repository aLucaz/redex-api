package com.application.data.parser;

import com.application.core.model.business.Branch;
import com.application.core.model.business.Shipment;
import com.application.core.model.business.ShipmentForBranch;
import com.application.core.model.business.ShipmentState;
import com.application.core.model.dto.ShipmentForBranchDto;
import com.application.rest.api.request.registerShipment.RegisterShipmentBranchWrapper;
import com.application.rest.api.request.registerShipment.RegisterShipmentRequest;

import java.util.ArrayList;
import java.util.List;

public class ShipmentForBranchParser {

    public static List<ShipmentForBranchDto> mapToDto(RegisterShipmentRequest request) {
        List<ShipmentForBranchDto> shipmentForBranchDtoList = new ArrayList<>();
        for (RegisterShipmentBranchWrapper wrapper : request.getTripPlan()) {
            shipmentForBranchDtoList.add(
                    new ShipmentForBranchDto()
                            .setIdBranch(wrapper.getStartCityId())
                            .setCurrentArrivalDateTime(wrapper.getCurrentArrivalDateTime())
                            .setCurrentDepartureDateTime(wrapper.getCurrentDepartureDateTime())
                            .setFutureArrivalDateTime(wrapper.getFutureArrivalDateTime())
                            .setFlightFriendlyId(wrapper.getFlightFriendlyId())
                            .setSequence(wrapper.getSequence())
            );
        }
        return shipmentForBranchDtoList;
    }

    public static List<ShipmentForBranch> mapToRowList(List<ShipmentForBranchDto> shipmentForBranchDtoList) {
        List<ShipmentForBranch> shipmentForBranchList = new ArrayList<>();
        for (ShipmentForBranchDto shipmentForBranchDto : shipmentForBranchDtoList) {
            shipmentForBranchList.add(
                    new ShipmentForBranch()
                            .setShipment(new Shipment().setIdShipment(shipmentForBranchDto.getIdShipment()))
                            .setBranch(new Branch().setIdBranch(shipmentForBranchDto.getIdBranch()))
                            .setShipmentState(new ShipmentState().setIdShipmentState(shipmentForBranchDto.getIdShipmentState()))
                            .setCurrentArrivalDate(shipmentForBranchDto.getCurrentArrivalDateTime())
                            .setCurrentDepartureDate(shipmentForBranchDto.getCurrentDepartureDateTime())
                            .setFutureArrivalDate(shipmentForBranchDto.getFutureArrivalDateTime())
                            .setFlightFriendlyId(shipmentForBranchDto.getFlightFriendlyId())
                            .setSequence(shipmentForBranchDto.getSequence())
            );
        }
        return shipmentForBranchList;
    }
}
