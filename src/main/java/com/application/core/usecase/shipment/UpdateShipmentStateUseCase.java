package com.application.core.usecase.shipment;

import com.application.core.model.business.ShipmentForBranch;
import com.application.core.model.business.ShipmentState;
import com.application.core.model.dto.ShipmentForBranchDto;
import com.application.data.gateway.ShipmentForBranchGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UpdateShipmentStateUseCase {
    private final ShipmentForBranchGateway shipmentForBranchGateway;

    public void execute(List<ShipmentForBranchDto> shipmentForBranchDtoList) {
        List<ShipmentForBranch> shipmentForBranchList = new ArrayList<>();
        ShipmentForBranch sfb;
        for (ShipmentForBranchDto sfbd : shipmentForBranchDtoList) {
            sfb = shipmentForBranchGateway.findByShipmentAndBranch(sfbd.getIdShipment(), sfbd.getIdBranch());
            sfb.setShipmentState(new ShipmentState().setIdShipmentState(sfbd.getIdShipmentState()));
            shipmentForBranchList.add(sfb);
        }
        shipmentForBranchGateway.update(shipmentForBranchList);
    }
}
