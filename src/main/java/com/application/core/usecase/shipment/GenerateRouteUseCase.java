package com.application.core.usecase.shipment;

import com.application.core.model.dto.BranchDto;
import com.application.core.model.dto.EtFlightDto;
import com.application.core.model.dto.IncidentDto;
import com.application.core.model.dto.PathDto;
import com.application.core.model.dto.RouteDto;
import com.application.core.model.dto.ShipmentForBranchDto;
import com.application.core.usecase.util.algorithm.AlgorithmExecutor;
import com.application.core.usecase.util.algorithm.AstarAlgorithmExecutorImpl;
import com.application.core.usecase.util.algorithm.structure.Network;
import com.application.core.usecase.util.algorithm.structure.Problem;
import com.application.core.usecase.util.algorithm.util.NetworkCreator;
import com.application.core.usecase.util.algorithm.util.NetworkCreatorImpl;
import com.application.core.usecase.util.detector.incident.IncidentDetector;
import com.application.core.usecase.util.detector.incident.IncidentDetectorImpl;
import com.application.data.gateway.BranchGateway;
import com.application.data.gateway.EtFlightGateway;
import com.application.data.gateway.IncidentGateway;
import com.application.data.gateway.ShipmentForBranchGateway;
import com.application.shared.Constant;
import com.application.shared.exception.custom.BranchNotAvailableException;
import com.application.shared.exception.custom.IncidentDetectedException;
import com.application.shared.exception.custom.RouteNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class GenerateRouteUseCase {
    public final BranchGateway branchGateway;
    public final EtFlightGateway etFlightGateway;
    public final AlgorithmExecutor findPathAlgorithm;
    public final NetworkCreator networkCreator;
    public final IncidentGateway incidentGateway;
    public final IncidentDetector incidentDetector;
    public final ShipmentForBranchGateway shipmentForBranchGateway;

    public GenerateRouteUseCase(BranchGateway branchGateway, EtFlightGateway etFlightGateway,
                                AstarAlgorithmExecutorImpl findPathAlgorithm, NetworkCreatorImpl networkCreator,
                                IncidentGateway incidentGateway, IncidentDetectorImpl incidentDetector,
                                ShipmentForBranchGateway shipmentForBranchGateway) {
        this.branchGateway = branchGateway;
        this.etFlightGateway = etFlightGateway;
        this.findPathAlgorithm = findPathAlgorithm;
        this.networkCreator = networkCreator;
        this.incidentGateway = incidentGateway;
        this.incidentDetector = incidentDetector;
        this.shipmentForBranchGateway = shipmentForBranchGateway;
    }

    public PathDto execute(RouteDto routeDto, Boolean isSimulationProccess) {
        // first of all we check if the route points are available
        checkIfBranchsAreAvailable(routeDto);
        // get all the branches with its data
        List<BranchDto> branchDtoList = branchGateway.fildAllActive();
        // get all the flights depends on the restrictions
        List<EtFlightDto> etFlightDtoList = etFlightGateway.findAllInRange(routeDto);
        // create the network with the data
        Network network = networkCreator.createNetwork(branchDtoList, etFlightDtoList);
        // create the problem with the data
        Problem problem = new Problem(
                routeDto.getStartPoint(),
                routeDto.getEndPoint(),
                routeDto.getRequestDateTime(),
                network);
        // call the algorithm to be executed
        List<RouteDto> onlyRoute = findPathAlgorithm.execute(problem);
        // if there is not route call an exception (depending  of simulationProccessvariable)
        if (onlyRoute == null && isSimulationProccess)
            return null;
        else if (onlyRoute == null)
            throw new RouteNotFoundException(Constant.ROUTE_NOT_FOUND_MSG);
        // else create the tripPlan to return
        List<RouteDto> tripPlan = extractInformation(onlyRoute, routeDto.getRequestDateTime());
        // now we validate if the tripPlan is valid
        List<IncidentDto> incidentsDetected = detectIncidents(tripPlan, isSimulationProccess);
        Boolean thereAreIncidents = incidentsDetected.size() != 0;
        if (thereAreIncidents && isSimulationProccess) {
            Boolean thereIsOnlyFlightIncidents = onlyFlightIncidents(incidentsDetected);
            if (!thereIsOnlyFlightIncidents)
                return null;
        } else if (thereAreIncidents)
            throw new IncidentDetectedException(Constant.INCIDENT_DETECTED_MSG);

        return new PathDto()
                .setDepartureDateTime(getDepartureOf(tripPlan))
                .setArrivalDateTime(getArrivalOf(tripPlan))
                .setScaleNumber(tripPlan.size())
                .setTripPlan(tripPlan)
                .setPrice(calculatePrice(tripPlan, routeDto.getNumberOfArticles()));
    }

    private Boolean onlyFlightIncidents(List<IncidentDto> incidentsDetected) {
        for (IncidentDto incident : incidentsDetected) {
            if (incident.getIncidentType().equals(Constant.BRANCH_CAPACITY_INCIDENT))
                return false;
        }
        return true;
    }

    public void checkIfBranchsAreAvailable(RouteDto routeDto) {
        if (!branchGateway.askIfIsActive(routeDto.getStartPoint()) && !branchGateway.askIfIsActive(routeDto.getEndPoint()))
            throw new BranchNotAvailableException(BranchDto.class, "friendlyIdStart", routeDto.getStartPoint(), "friendlyIdEnd", routeDto.getEndPoint());
        else {
            if (!branchGateway.askIfIsActive(routeDto.getStartPoint()))
                throw new BranchNotAvailableException(BranchDto.class, "friendlyId", routeDto.getStartPoint());
            if (!branchGateway.askIfIsActive(routeDto.getEndPoint()))
                throw new BranchNotAvailableException(BranchDto.class, "friendlyId", routeDto.getEndPoint());
        }
    }

    public List<RouteDto> extractInformation(List<RouteDto> basePath, LocalDateTime requestDateTime) {
        basePath.forEach(
                routeDto -> {
                    routeDto.setCurrentDepartureDateTime(etFlightGateway.getDepartureLocalDateTime(routeDto.getFlightFriendlyId()));
                    routeDto.setFutureArrivalDateTime(etFlightGateway.getArrivalLocalDateTime(routeDto.getFlightFriendlyId()));
                    // getting currentArrivalDateTime
                    int index = basePath.indexOf(routeDto);
                    if (index == 0)
                        routeDto.setCurrentArrivalDateTime(requestDateTime);
                    else
                        routeDto.setCurrentArrivalDateTime(basePath.get(index - 1).getFutureArrivalDateTime());
                    // also save the sequence
                    routeDto.setSequence(index + 1);
                    // rest of data
                    routeDto.setStartCity(branchGateway.findNameOf(routeDto.getStartPoint()));
                    routeDto.setEndCity(branchGateway.findNameOf(routeDto.getEndPoint()));
                    routeDto.setStartCityId(branchGateway.findIdOf(routeDto.getStartPoint()));
                    routeDto.setEndCityId(branchGateway.findIdOf(routeDto.getEndPoint()));
                }
        );
        return basePath;
    }

    public LocalDateTime getDepartureOf(List<RouteDto> tripPlan) {
        return tripPlan.get(0).getCurrentDepartureDateTime();
    }

    public LocalDateTime getArrivalOf(List<RouteDto> tripPlan) {
        return tripPlan.get(tripPlan.size() - 1).getFutureArrivalDateTime();
    }

    public Float calculatePrice(List<RouteDto> tripPlan, Integer numberOfArticles) {
        Integer scaleNumber = tripPlan.size();
        if (numberOfArticles != null)
            return Constant.PRICE_PER_ARTICLE * numberOfArticles + Constant.PRICE_PER_SCALE * scaleNumber;
        else
            return Constant.PRICE_PER_SCALE * scaleNumber;
    }

    public List<IncidentDto> detectIncidents(List<RouteDto> tripPlan, Boolean isASimulation) {
        List<RouteDto> evaluatedTripPlan = giveMeTripPlanToEvaluate(tripPlan);
        List<IncidentDto> incidentDtoList = new ArrayList<>();
        //now we start the evaluation
        for (RouteDto route : evaluatedTripPlan) {
            BranchDto branch = branchGateway.findByFriendlyId(route.getStartPoint());
            List<ShipmentForBranchDto> allTripPlans = shipmentForBranchGateway
                    .finalAllByBranchIdAndShipmentActive(branch);
            // we detect if there is an branch oncident there
            IncidentDto branchIncident = incidentDetector.detectIncident(
                    allTripPlans,
                    route.getCurrentArrivalDateTime(),
                    route.getCurrentDepartureDateTime(),
                    branch,
                    isASimulation
            );
            if (branchIncident != null)
                incidentDtoList.add(branchIncident);
            // then we detect if there are flight incident in the same way
            if (evaluatedTripPlan.indexOf(route) != evaluatedTripPlan.size() - 1) {
                IncidentDto flightIncident = incidentDetector.detectIncident(route.getFlightFriendlyId(), isASimulation);
                if (flightIncident != null)
                    incidentDtoList.add(flightIncident);
            }
        }
        if (incidentDtoList.size() != 0) {
            incidentGateway.saveAllIncidents(incidentDtoList);
        }
        return incidentDtoList;
    }

    public List<RouteDto> giveMeTripPlanToEvaluate(List<RouteDto> originalTripPlan) {
        List<RouteDto> evaluatedTripPlan = new ArrayList<>();
        for (RouteDto route : originalTripPlan) {
            evaluatedTripPlan.add(new RouteDto()
                    .setStartPoint(route.getStartPoint())
                    .setCurrentDepartureDateTime(route.getCurrentDepartureDateTime())
                    .setCurrentArrivalDateTime(route.getCurrentArrivalDateTime())
                    .setFlightFriendlyId(route.getFlightFriendlyId())
            );
            if (originalTripPlan.indexOf(route) == originalTripPlan.size() - 1) {
                evaluatedTripPlan.add(new RouteDto()
                        .setStartPoint(route.getEndPoint())
                        .setCurrentDepartureDateTime(route.getFutureArrivalDateTime())
                        .setCurrentArrivalDateTime(route.getFutureArrivalDateTime())
                );
            }
        }
        return evaluatedTripPlan;
    }

}
