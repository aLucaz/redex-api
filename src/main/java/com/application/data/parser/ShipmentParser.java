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
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ShipmentParser {
    public static ShipmentDto mapToDto(RegisterShipmentRequest request) {
        return new ShipmentDto()
                .setPrice(request.getPrice())
                .setLastModifiedBy(request.getLastModifiedBy())
                .setRegisteredBy(request.getRegisteredBy())
                .setArrivalDateTime(request.getArrivalDateTime())
                .setDepartureDateTime(request.getDepartureDateTime())
                .setSameContinent(request.getSameContinent().equals(1))
                .setDeparturePoint(request.getDeparturePoint())
                .setArrivalPoint(request.getArrivalPoint());
    }

    public static ShipmentDto mapToDto(Shipment shipment) {
        return new ShipmentDto()
                .setPrice(shipment.getPrice())
                .setIdShipment(shipment.getIdShipment())
                .setReferenceCode(shipment.getReferenceCode())
                .setIsActive(shipment.getIsActive())
                .setIsSimulated(shipment.getIsSimulated())
                .setShipmentForBranches(ShipmentForBranchParser.mapToDtoSet(shipment.getShipmentForBranches()))
                .setShipmentForPeople(ShipmentForPersonParser.mapToDtoSet(shipment.getShipmentForPeople()));
    }

    public static List<ShipmentDto> mapToDtoList(List<Shipment> shipmentList) {
        List<ShipmentDto> shipmentDtoList = new ArrayList<>();
        for (Shipment shipment : shipmentList) {
            shipmentDtoList.add(mapToDto(shipment));
        }
        return shipmentDtoList;
    }

    public static Shipment mapToRow(ShipmentDto shipmentDto) {
        return new Shipment()
                .setPrice(shipmentDto.getPrice())
                .setIsActive(Constant.ACTIVEB)
                .setLastModifiedBy(shipmentDto.getLastModifiedBy())
                .setLastModifiedDate(LocalDateTime.now())
                .setRegisteredBy(shipmentDto.getRegisteredBy())
                .setRegisteredDate(LocalDateTime.now())
                .setReferenceCode(shipmentDto.getReferenceCode())
                .setIsActive(Constant.IS_ACTIVE)
                .setIsSimulated(Constant.IS_NOT_A_SIMULATION)
                .setTotalDelayTime(shipmentDto.getTotalDelayTime())
                .setTotalWaitingTime(shipmentDto.getTotalWaitingTime())
                .setTotalTransportTime(shipmentDto.getTotalTransportTime())
                .setSameContinent(shipmentDto.getSameContinent())
                .setDepartureDateTime(shipmentDto.getDepartureDateTime())
                .setArrivalDateTime(shipmentDto.getArrivalDateTime())
                .setDeparturePoint(shipmentDto.getDeparturePoint())
                .setArrivalPoint(shipmentDto.getArrivalPoint());
    }

    public static Shipment mapToRow(PathDto pathDto, Integer idShipmentState, Boolean isSimulated, Boolean sameContinent) {
        Shipment shipment = new Shipment()
                .setPrice(pathDto.getPrice())
                .setLastModifiedBy(Constant.DEFAULT_USER_REGISTRATOR)
                .setLastModifiedDate(LocalDateTime.now())
                .setRegisteredBy(Constant.DEFAULT_USER_REGISTRATOR)
                .setRegisteredDate(LocalDateTime.now())
                .setIsSimulated(isSimulated)
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
        // we get the final ShipmentForBranch
        RouteDto initialRoute = pathDto.getTripPlan()
                .stream()
                .min(Comparator.comparing(RouteDto::getSequence))
                .get();

        RouteDto finalRoute = pathDto.getTripPlan()
                .stream()
                .max(Comparator.comparing(RouteDto::getSequence))
                .get();

        // now add the set to the created shipment
        shipment.setShipmentForBranches(shipmentForBranchSet)
                .setSameContinent(sameContinent)
                .setArrivalDateTime(finalRoute.getFutureArrivalDateTime())
                .setDepartureDateTime(initialRoute.getCurrentDepartureDateTime())
                .setTotalTransportTime(finalRoute.getTransportTime())
                .setTotalWaitingTime(finalRoute.getWaitingTime())
                .setTotalDelayTime(finalRoute.getTotalTime());

        return shipment;
    }

}
