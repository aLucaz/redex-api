package com.application.core.usecase.shipment;

import com.application.core.model.business.*;
import com.application.core.model.dto.ShipmentForBranchDto;
import com.application.data.gateway.*;
import com.application.shared.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class UpdateShipmentForBranchUseCase {
    private final ShipmentForBranchGateway shipmentForBranchGateway;
    private final ShipmentStateGateway shipmentStateGateway;
    private final ShipmentGateway shipmentGateway;
    private final BranchGateway branchGateway;

    public void execute(List<ShipmentForBranchDto> shipmentForBranchDtoList) {
        List<ShipmentForBranch> shipmentForBranchList = new ArrayList<>();
        Map<String,Integer> mapStates = getShipmentStateMap();
        Integer nextState;
        ShipmentForBranch sfb;
        for (ShipmentForBranchDto sfbd : shipmentForBranchDtoList) {
            sfb = shipmentForBranchGateway.findByShipmentAndBranch(sfbd.getIdShipment(), sfbd.getIdBranch());
            saveNextState(sfb,mapStates);
        }
    }

    public void saveNextState(ShipmentForBranch sfb,Map<String,Integer> mapStates){
        Integer nextState = 0;
        /*
        Set<String> lista = mapStates.keySet();
        for(String str : lista){
            System.out.println(mapStates.get(str));
        }*/
        ShipmentState st = sfb.getShipmentState();
        Shipment shipment = shipmentGateway.findById(sfb.getShipment().getIdShipment());
        Branch branch = branchGateway.findByIdBranch(sfb.getBranch().getIdBranch());
        String lastBranchCode  = shipment.getArrivalPoint();
        String currentBranchCode = sfb.getBranch().getFriendlyId();
        switch (st.getFriendlyId()){
            case Constant.IN_TRANSIT_IN:
                System.out.println(lastBranchCode);
                System.out.println(currentBranchCode);
                if(branch.getQuantity() < branch.getCapacity()){
                    if(lastBranchCode == currentBranchCode)
                        nextState = mapStates.get(Constant.TO_DELIVER);
                    else
                        nextState = mapStates.get(Constant.IN_TRANSIT_OUT);
                    branch.setQuantity(branch.getQuantity()+1);
                }
                else{
                    nextState = mapStates.get(Constant.IN_TRANSIT_IN);
                }
                break;
            case Constant.IN_TRANSIT_OUT:
                nextState = mapStates.get(Constant.BRANCH_OUT);
                branch.setQuantity(branch.getQuantity()-1);
                break;
            case Constant.TO_DELIVER:
                nextState = mapStates.get(Constant.FINISHED);
                branch.setQuantity(branch.getQuantity()-1);
                break;
            case Constant.FINISHED:
                nextState = mapStates.get(Constant.FINISHED);
                break;
            case Constant.BRANCH_OUT:
                nextState = mapStates.get(Constant.BRANCH_OUT);
                break;
        }
        sfb.setShipmentState(new ShipmentState().setIdShipmentState(nextState));
        shipmentForBranchGateway.update(sfb);
    }

    public  Map<String,Integer> getShipmentStateMap(){
        Map<String,Integer> mapStates = new HashMap<String,Integer>();
        List<ShipmentState> shipmentStateList = shipmentStateGateway.findAll();
        for(ShipmentState st : shipmentStateList){
            switch (st.getFriendlyId()){
                case Constant.IN_TRANSIT_IN:
                    mapStates.put(Constant.IN_TRANSIT_IN,st.getIdShipmentState());
                    break;
                case Constant.IN_TRANSIT_OUT:
                    mapStates.put(Constant.IN_TRANSIT_OUT,st.getIdShipmentState());
                    break;
                case Constant.TO_DELIVER:
                    mapStates.put(Constant.TO_DELIVER,st.getIdShipmentState());
                    break;
                case Constant.FINISHED:
                    mapStates.put(Constant.FINISHED,st.getIdShipmentState());
                    break;
                case Constant.BRANCH_OUT:
                    mapStates.put(Constant.BRANCH_OUT,st.getIdShipmentState());
                    break;
            }
        }
        return mapStates;
    }
}
