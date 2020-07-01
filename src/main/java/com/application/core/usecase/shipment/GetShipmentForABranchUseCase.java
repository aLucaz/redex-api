package com.application.core.usecase.shipment;

import com.application.core.model.business.ShipmentForBranch;
import com.application.core.model.dto.*;
import com.application.data.gateway.*;
import com.application.data.parser.*;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class GetShipmentForABranchUseCase {
    ShipmentForBranchGateway shipmentForBranchGateway;
    BranchGateway branchGateway;

    public GetShipmentForABranchUseCase(ShipmentForBranchGateway shipmentForBranchGateway,
                                        BranchGateway branchGateway){
        this.shipmentForBranchGateway = shipmentForBranchGateway;
        this.branchGateway = branchGateway;
    }
    public BranchDto execute(Integer idBranch) {

        List<ShipmentForBranch> shipmentForBranchList = shipmentForBranchGateway.getShipmentForBranchList(idBranch);

        BranchDto branchDtoResponse = BranchParser.mapToDto(branchGateway.findByIdBranch(idBranch));
        branchDtoResponse.setShipments(
                ShipmentForBranchParser.mapToDtoListFromShipmentForBranch(shipmentForBranchList));
        return branchDtoResponse;
    }
}
