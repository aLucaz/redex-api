package com.application.data.parser;

import com.application.core.model.business.Branch;
import com.application.core.model.business.Shipment;
import com.application.core.model.business.ShipmentForBranch;
import com.application.core.model.business.ShipmentState;
import com.application.core.model.dto.PathDto;
import com.application.core.model.dto.RouteDto;
import com.application.core.model.dto.ShipmentForBranchDto;
import com.application.rest.api.request.*;
import com.application.rest.api.request.registerShipment.RegisterShipmentBranchWrapper;
import com.application.rest.api.request.registerShipment.RegisterShipmentRequest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public static ShipmentForBranchDto mapToDto(ShipmentForBranch shipmentForBranch) {
        return new ShipmentForBranchDto()
                .setIdBranch(shipmentForBranch.getBranch().getIdBranch())
                .setIdShipment(shipmentForBranch.getShipment().getIdShipment())
                .setCurrentArrivalDateTime(shipmentForBranch.getCurrentArrivalDateTime())
                .setCurrentDepartureDateTime(shipmentForBranch.getCurrentDepartureDateTime())
                .setFutureArrivalDateTime(shipmentForBranch.getFutureArrivalDateTime())
                .setFlightFriendlyId(shipmentForBranch.getFlightFriendlyId())
                .setSequence(shipmentForBranch.getSequence());
    }

    public static List<ShipmentForBranchDto> mapToDtoListFromRowList(List<ShipmentForBranch> shipmentForBranches) {
        List<ShipmentForBranchDto> shipmentForBranchDtoList = new ArrayList<>();
        for (ShipmentForBranch shipmentForBranch : shipmentForBranches) {
            shipmentForBranchDtoList.add(mapToDto(shipmentForBranch));
        }
        return shipmentForBranchDtoList;
    }

    public static Set<ShipmentForBranchDto> mapToDtoSet(Set<ShipmentForBranch> shipmentForBranchSet) {
        // its possible to receive null Here
        if (shipmentForBranchSet == null)
            return null;
        // continue with normal process
        Set<ShipmentForBranchDto> shipmentForBranchDtoSet = new HashSet<>();
        for (ShipmentForBranch shipmentForBranch : shipmentForBranchSet) {
            shipmentForBranchDtoSet.add(mapToDto(shipmentForBranch));
        }
        return shipmentForBranchDtoSet;
    }

    public static List<ShipmentForBranch> mapToRowList(List<ShipmentForBranchDto> shipmentForBranchDtoList) {
        List<ShipmentForBranch> shipmentForBranchList = new ArrayList<>();
        for (ShipmentForBranchDto shipmentForBranchDto : shipmentForBranchDtoList) {
            shipmentForBranchList.add(
                    new ShipmentForBranch()
                            .setShipment(new Shipment().setIdShipment(shipmentForBranchDto.getIdShipment()))
                            .setBranch(new Branch().setIdBranch(shipmentForBranchDto.getIdBranch()))
                            .setShipmentState(new ShipmentState().setIdShipmentState(shipmentForBranchDto.getIdShipmentState()))
                            .setCurrentArrivalDateTime(shipmentForBranchDto.getCurrentArrivalDateTime())
                            .setCurrentDepartureDateTime(shipmentForBranchDto.getCurrentDepartureDateTime())
                            .setFutureArrivalDateTime(shipmentForBranchDto.getFutureArrivalDateTime())
                            .setFlightFriendlyId(shipmentForBranchDto.getFlightFriendlyId())
                            .setSequence(shipmentForBranchDto.getSequence())
            );
        }
        return shipmentForBranchList;
    }

    public static List<ShipmentForBranchDto> mapToDtoList(List<PathDto> pathDtoList) {
        List<ShipmentForBranchDto> shipmentForBranchDtoList = new ArrayList<>();
        for (PathDto pathDto : pathDtoList) {
            for (RouteDto routeDto : pathDto.getTripPlan()) {
                shipmentForBranchDtoList.add(new ShipmentForBranchDto()
                        .setIdBranch(routeDto.getStartCityId())
                        .setCurrentArrivalDateTime(routeDto.getCurrentArrivalDateTime())
                        .setCurrentDepartureDateTime(routeDto.getCurrentDepartureDateTime())
                        .setFutureArrivalDateTime(routeDto.getFutureArrivalDateTime())
                        .setFlightFriendlyId(routeDto.getFlightFriendlyId())
                        .setSequence(routeDto.getSequence()));
            }
        }
        return shipmentForBranchDtoList;
    }

    public static List<ShipmentForBranchDto> mapToDtoListFromShipmentForBranch(List<ShipmentForBranch> shipmentForBranchList) {
        List<ShipmentForBranchDto> shipmentForBranchDtoList = new ArrayList<>();
        for (ShipmentForBranch shipmentForBranch : shipmentForBranchList) {
            shipmentForBranchDtoList.add(
                    new ShipmentForBranchDto()
                            .setIdBranch(shipmentForBranch.getBranch().getIdBranch())
                            .setIdShipment(shipmentForBranch.getShipment().getIdShipment())
                            .setReferenceCode(shipmentForBranch.getShipment().getReferenceCode())
                            .setShipmentStateFriendlyId(shipmentForBranch.getShipmentState().getFriendlyId())
                            .setReferenceCode(shipmentForBranch.getShipment().getReferenceCode())
                            .setIdShipmentState(shipmentForBranch.getShipmentState().getIdShipmentState())
                            .setCurrentArrivalDateTime(shipmentForBranch.getCurrentArrivalDateTime())
                            .setCurrentDepartureDateTime(shipmentForBranch.getCurrentDepartureDateTime())
                            .setFutureArrivalDateTime(shipmentForBranch.getFutureArrivalDateTime())
                            .setFlightFriendlyId(shipmentForBranch.getFlightFriendlyId())
                            .setSequence(shipmentForBranch.getSequence())
            );
        }
        return shipmentForBranchDtoList;
    }

    public static List<ShipmentForBranchDto> mapToDto(List<UpdateShipmentStateRequest> requestList) {
        List<ShipmentForBranchDto> shipmentForBranchDtoList = new ArrayList<>();
        for (UpdateShipmentStateRequest wrapper : requestList) {
            shipmentForBranchDtoList.add(
                    new ShipmentForBranchDto()
                            .setIdBranch(wrapper.getIdBranch())
                            .setIdShipment(wrapper.getIdShipment())
                            .setIdShipmentState(wrapper.getIdShipmentState())
            );
        }
        return shipmentForBranchDtoList;
    }
}

