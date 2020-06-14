package com.application.core.usecase.shipment;

import com.application.core.model.dto.BranchDto;
import com.application.core.model.dto.EtFlightDto;
import com.application.core.model.dto.RouteDto;
import com.application.core.usecase.util.algorithm.AlgorithmExecutor;
import com.application.core.usecase.util.algorithm.AstarAlgorithmExecutorImpl;
import com.application.core.usecase.util.algorithm.structure.Network;
import com.application.core.usecase.util.algorithm.structure.Problem;
import com.application.core.usecase.util.algorithm.util.NetworkCreator;
import com.application.core.usecase.util.algorithm.util.NetworkCreatorImpl;
import com.application.data.gateway.BranchGateway;
import com.application.data.gateway.EtFlightGateway;
import com.application.shared.exception.custom.BranchNotAvailableException;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenerateRouteUseCase {
    public final BranchGateway branchGateway;
    public final EtFlightGateway etFlightGateway;
    public final AlgorithmExecutor findPathAlgorithm;
    public final NetworkCreator networkCreator;

    public GenerateRouteUseCase(BranchGateway branchGateway, EtFlightGateway etFlightGateway, AstarAlgorithmExecutorImpl findPathAlgorithm, NetworkCreatorImpl networkCreator) {
        this.branchGateway = branchGateway;
        this.etFlightGateway = etFlightGateway;
        this.findPathAlgorithm = findPathAlgorithm;
        this.networkCreator = networkCreator;
    }

    public List<RouteDto> execute(RouteDto routeDto) {
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
        return findPathAlgorithm.execute(problem);
    }

    public void checkIfBranchsAreAvailable(RouteDto routeDto){
        if(!branchGateway.askIfIsActive(routeDto.getStartPoint()) && !branchGateway.askIfIsActive(routeDto.getEndPoint()))
            throw new BranchNotAvailableException(BranchDto.class, "friendlyIdStart",routeDto.getStartPoint(),"friendlyIdEnd",routeDto.getEndPoint());
        else {
            if (!branchGateway.askIfIsActive(routeDto.getStartPoint()))
                throw new BranchNotAvailableException(BranchDto.class, "friendlyId",routeDto.getStartPoint());
            if (!branchGateway.askIfIsActive(routeDto.getEndPoint()))
                throw new BranchNotAvailableException(BranchDto.class, "friendlyId",routeDto.getEndPoint());
        }
    }
}
