package com.application.core.usecase.client;

import com.application.core.model.dto.BranchDto;
import com.application.core.model.dto.PathDto;
import com.application.core.model.dto.PersonDto;
import com.application.core.model.dto.RouteDto;
import com.application.core.model.dto.SearchShipmentDto;
import com.application.core.model.dto.ShipmentDto;
import com.application.core.model.dto.ShipmentForBranchDto;
import com.application.core.model.dto.ShipmentForPersonDto;
import com.application.core.model.dto.ShipmentStateDto;
import com.application.data.gateway.BranchGateway;
import com.application.data.gateway.EtFlightGateway;
import com.application.data.gateway.PersonGateway;
import com.application.data.gateway.ShipmentGateway;
import com.application.data.gateway.ShipmentStateGateway;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class SearchShipmentUseCase {
    private final ShipmentGateway shipmentGateway;
    private final PersonGateway personGateway;
    private final EtFlightGateway etFlightGateway;
    private final BranchGateway branchGateway;
    private final ShipmentStateGateway shipmentStateGateway;

    public SearchShipmentUseCase(ShipmentGateway shipmentGateway, PersonGateway personGateway,
                                 EtFlightGateway etFlightGateway, BranchGateway branchGateway, ShipmentStateGateway shipmentStateGateway) {
        this.shipmentGateway = shipmentGateway;
        this.personGateway = personGateway;
        this.etFlightGateway = etFlightGateway;
        this.branchGateway = branchGateway;
        this.shipmentStateGateway = shipmentStateGateway;
    }

    public SearchShipmentDto execute(SearchShipmentDto request) {
        ShipmentDto shipment = shipmentGateway.findByReferenceCode(request.getReferenceCode());
        if (!shipment.getIsActive())
            return null;
        // we get all the path information
        List<ShipmentForBranchDto> shipmentForBranchDtoList = new ArrayList<>(shipment.getShipmentForBranches());
        List<RouteDto> onlyRoute = convertToRoute(shipmentForBranchDtoList);

        Collections.sort(onlyRoute, (d1, d2) -> {
            return d1.getSequence() - d2.getSequence();
        });

        PathDto pathDto = new PathDto()
                .setDepartureDateTime(getDepartureOf(onlyRoute))
                .setArrivalDateTime(getArrivalOf(onlyRoute))
                .setScaleNumber(onlyRoute.size())
                .setTripPlan(onlyRoute)
                .setPrice(shipment.getPrice());

        // then we get the people information
        List<ShipmentForPersonDto> shipmentForPersonDtoList = new ArrayList<>(shipment.getShipmentForPeople());
        List<PersonDto> people = new ArrayList<>();
        shipmentForPersonDtoList.forEach(shipmentForPersonDto -> {
            people.add(personGateway.findById(shipmentForPersonDto.getIdPerson()));
        });

        return new SearchShipmentDto()
                .setTripPlan(pathDto)
                .setPeople(people);
    }

    public List<RouteDto> convertToRoute(List<ShipmentForBranchDto> shipmentForBranchDtoList) {
        List<RouteDto> routeDtoList = new ArrayList<>();
        shipmentForBranchDtoList.forEach(shipmentForBranchDto -> {
            ShipmentStateDto state = shipmentStateGateway.findById(shipmentForBranchDto.getIdShipmentState());
            String endCityFriendlyId = etFlightGateway.findByFriendlyId(shipmentForBranchDto.getFlightFriendlyId()).getArrivalPoint();
            BranchDto endBranch = branchGateway.findByFriendlyId(endCityFriendlyId);
            BranchDto startBranch = branchGateway.findById(shipmentForBranchDto.getIdBranch());
            RouteDto routeDto = new RouteDto()
                    .setCurrentArrivalDateTime(shipmentForBranchDto.getCurrentArrivalDateTime())
                    .setCurrentDepartureDateTime(shipmentForBranchDto.getCurrentDepartureDateTime())
                    .setFutureArrivalDateTime(shipmentForBranchDto.getFutureArrivalDateTime())
                    .setStartCityId(startBranch.getIdBranch())
                    .setEndCityId(endBranch.getIdBranch())
                    .setStartCity(startBranch.getName())
                    .setEndCity(endBranch.getName())
                    .setSequence(shipmentForBranchDto.getSequence())
                    .setFriendlyIdState(state.getFriendlyId())
                    .setNameState(state.getName());
            routeDtoList.add(routeDto);
        });
        return routeDtoList;
    }

    public LocalDateTime getDepartureOf(List<RouteDto> tripPlan) {
        return tripPlan.get(0).getCurrentDepartureDateTime();
    }

    public LocalDateTime getArrivalOf(List<RouteDto> tripPlan) {
        return tripPlan.get(tripPlan.size() - 1).getFutureArrivalDateTime();
    }

}
