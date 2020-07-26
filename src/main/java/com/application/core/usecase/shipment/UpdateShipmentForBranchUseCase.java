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
    private final EtFlightGateway etFlightGateway;

    public void execute(List<ShipmentForBranchDto> shipmentForBranchDtoList) {
        List<ShipmentForBranch> shipmentForBranchList = new ArrayList<>();
        Map<String,Integer> mapStates = getShipmentStateMap();
        Integer nextState;
        ShipmentForBranch sfb;
        for (ShipmentForBranchDto sfbd : shipmentForBranchDtoList) {
            Boolean lastBranch = false;
            Shipment shipment = shipmentGateway.findById(sfbd.getIdShipment());
            Branch branch = branchGateway.findByIdBranch(sfbd.getIdBranch());
            String lastBranchCode  = shipment.getArrivalPoint();
            String currentBranchCode = branch.getFriendlyId();
            if(!lastBranchCode.equals(currentBranchCode)){
                sfb = shipmentForBranchGateway.findByShipmentAndBranch(sfbd.getIdShipment(), sfbd.getIdBranch());
                lastBranch = false;
            }
            else{
                //Buscamos todos los vuelos que terminen en este branch
                List<String> flightFriendlyIds = new ArrayList<String>();
                List<EtFlight> flightList = etFlightGateway.findByArrivalPoint(branch.getFriendlyId());
                for(EtFlight etFlight : flightList)
                    flightFriendlyIds.add(etFlight.getFriendlyId());

                //Buscamos el penultimo vuelo
                sfb = shipmentForBranchGateway.findByFlightListAndShipment(flightFriendlyIds,sfbd.getIdShipment());
                lastBranch = true;
            }
            saveNextState(sfb,mapStates,lastBranch);
        }
    }

    public void saveNextState(ShipmentForBranch sfb,Map<String,Integer> mapStates,Boolean lastBranch){
        Integer nextState = 0;
        /*
        Set<String> lista = mapStates.keySet();
        for(String str : lista){
            System.out.println(mapStates.get(str));
        }*/
        ShipmentState st = sfb.getShipmentState();
        Shipment shipment = shipmentGateway.findById(sfb.getShipment().getIdShipment());
        Branch branch = branchGateway.findByIdBranch(sfb.getBranch().getIdBranch());
        switch (st.getFriendlyId()){
            case Constant.IN_TRANSIT_IN:
                nextState = mapStates.get(Constant.IN_TRANSIT_OUT);
                branch.setQuantity(branch.getQuantity()+1);
                break;
            case Constant.IN_TRANSIT_OUT:
                nextState = mapStates.get(Constant.BRANCH_OUT);
                branch.setQuantity(branch.getQuantity()-1);
                break;
            case Constant.BRANCH_OUT:
                if(lastBranch)
                    nextState = mapStates.get(Constant.TO_DELIVER);
                else
                    nextState = mapStates.get(Constant.BRANCH_OUT);
                break;
            case Constant.TO_DELIVER:
                nextState = mapStates.get(Constant.FINISHED);
                break;
            case Constant.FINISHED:
                nextState = mapStates.get(Constant.FINISHED);
                branch.setQuantity(branch.getQuantity()-1);
                break;
        }
        if(nextState.equals(Constant.FINISHED)){
            List<ShipmentForBranch> shipmentForBranchList =
                    shipmentForBranchGateway.findByShipment(sfb.getShipment().getIdShipment());
            for(ShipmentForBranch finishedSfb : shipmentForBranchList){
                finishedSfb.setShipmentState(
                        finishedSfb.getShipmentState().
                                setIdShipmentState(mapStates.get(Constant.FINISHED)));
            }
            shipmentForBranchGateway.update(shipmentForBranchList);
        }else{
            sfb.setShipmentState(new ShipmentState().setIdShipmentState(nextState));
            shipmentForBranchGateway.update(sfb);
        }
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
