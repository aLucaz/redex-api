package com.application.core.usecase.shipment;

import com.application.core.model.business.ShipmentForBranch;
import com.application.core.model.dto.*;
import com.application.data.gateway.*;
import com.application.data.parser.*;
import com.application.shared.*;
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
        Integer inTransitInCount = 0;
        Integer inTransitOutCount = 0;
        Integer toDeliverCount = 0;
        BranchDto branchDtoResponse = BranchParser.mapToDto(branchGateway.findByIdBranch(idBranch));

        List<ShipmentForBranch> shipmentForBranchList = shipmentForBranchGateway.getShipmentForBranchList(idBranch);

        for(ShipmentForBranch sfb : shipmentForBranchList){
            switch(sfb.getShipmentState().getFriendlyId()) {
                case Constant.IN_TRANSIT_IN:
                    inTransitInCount++;
                    break;
                case Constant.IN_TRANSIT_OUT:
                    inTransitOutCount++;
                    break;
                case Constant.TO_DELIVER:
                    toDeliverCount++;
                    break;
            }
        }
        branchDtoResponse.setInTransitIn(inTransitInCount);
        branchDtoResponse.setInTransitOut(inTransitOutCount);
        branchDtoResponse.setToDeliver(toDeliverCount);
        branchDtoResponse.setShipments(
                ShipmentForBranchParser.mapToDtoListFromShipmentForBranch(shipmentForBranchList));
        return branchDtoResponse;
    }
}
