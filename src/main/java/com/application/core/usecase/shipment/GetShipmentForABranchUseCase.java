package com.application.core.usecase.shipment;

import com.application.core.model.business.*;
import com.application.core.model.dto.BranchDto;
import com.application.data.gateway.*;
import com.application.data.parser.BranchParser;
import com.application.data.parser.ShipmentForBranchParser;
import com.application.shared.Constant;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class GetShipmentForABranchUseCase {
    ShipmentForBranchGateway shipmentForBranchGateway;
    BranchGateway branchGateway;
    ShipmentGateway shipmentGateway;
    EtFlightGateway etFlightGateway;
    private final UpdateShipmentForBranchUseCase updateShipmentForBranchUseCase;
    public GetShipmentForABranchUseCase(ShipmentForBranchGateway shipmentForBranchGateway,
                                        BranchGateway branchGateway,
                                        ShipmentGateway shipmentGateway,
                                        EtFlightGateway etFlightGateway,
                                        UpdateShipmentForBranchUseCase updateShipmentForBranchUseCase) {
        this.shipmentForBranchGateway = shipmentForBranchGateway;
        this.branchGateway = branchGateway;
        this.shipmentGateway = shipmentGateway;
        this.etFlightGateway = etFlightGateway;
        this.updateShipmentForBranchUseCase = updateShipmentForBranchUseCase;
    }

    public BranchDto execute(Integer idBranch) {
        Integer inTransitInCount = 0;
        Integer inTransitOutCount = 0;
        Integer toDeliverCount = 0;
        //Mapa de estados con sus ids
        Map<String,Integer> mapStates = updateShipmentForBranchUseCase.getShipmentStateMap();

        //Lista Final de Shipment for branch
        List<ShipmentForBranch> finalList = new ArrayList<ShipmentForBranch>();

        //Branch actual
        BranchDto branchDtoResponse = BranchParser.mapToDto(branchGateway.findByIdBranch(idBranch));

        //Buscamos todos los sfb de este branch
        List<ShipmentForBranch> shipmentForBranchList = shipmentForBranchGateway.getShipmentForBranchList(idBranch);

        //Buscamos todos los vuelos que terminen en este branch
        List<String> flightFriendlyIds = new ArrayList<String>();
        List<EtFlight> flightList = etFlightGateway.findByArrivalPoint(branchDtoResponse.getFriendlyId());
        for(EtFlight etFlight : flightList)
            flightFriendlyIds.add(etFlight.getFriendlyId());

        //Buscamos todos los penultimos sfb cuyo punto final sea este branch
        List<ShipmentForBranch> secondLastSFB =
                shipmentForBranchGateway.findByFlightListAndShipmentArrivalPoint(flightFriendlyIds,branchDtoResponse.getFriendlyId());

        //Creamos los sfb para este branch cuando es el punto final
        for(ShipmentForBranch sfbSL : secondLastSFB){
            ShipmentForBranch sfbToAdd =
                    new ShipmentForBranch()
                            .setShipment(sfbSL.getShipment())
                            .setBranch(sfbSL.getBranch().setIdBranch(branchDtoResponse.getIdBranch()))
                            .setCurrentArrivalDateTime(sfbSL.getFutureArrivalDateTime())
                            .setSequence(sfbSL.getSequence()+1)
                    ;
            switch(sfbSL.getShipmentState().getFriendlyId()){
                case Constant.IN_TRANSIT_IN:
                    sfbToAdd.setShipmentState(
                            new ShipmentState().setIdShipmentState(mapStates.get(Constant.IN_TRANSIT_IN))
                                    .setFriendlyId(Constant.IN_TRANSIT_IN));
                    break;
                case Constant.IN_TRANSIT_OUT:
                    sfbToAdd.setShipmentState(
                            new ShipmentState().setIdShipmentState(mapStates.get(Constant.IN_TRANSIT_IN))
                                    .setFriendlyId(Constant.IN_TRANSIT_IN));
                    break;
                case Constant.BRANCH_OUT:
                    sfbToAdd.setShipmentState(
                            new ShipmentState().setIdShipmentState(mapStates.get(Constant.IN_TRANSIT_IN))
                                    .setFriendlyId(Constant.IN_TRANSIT_IN));
                    break;
                case Constant.TO_DELIVER:
                    sfbToAdd.setShipmentState(
                            new ShipmentState()
                                    .setIdShipmentState(mapStates.get(Constant.TO_DELIVER))
                                    .setFriendlyId(Constant.TO_DELIVER));
                    break;
                case Constant.FINISHED:
                    sfbToAdd.setShipmentState(
                            new ShipmentState()
                                    .setIdShipmentState(mapStates.get(Constant.FINISHED))
                                    .setFriendlyId(Constant.FINISHED));
                    break;
            }
            finalList.add(sfbToAdd);
        }

        //Modificamos los sfb que si existen en la bd
        for(ShipmentForBranch sfbE : shipmentForBranchList){
            switch(sfbE.getShipmentState().getFriendlyId()){
                case Constant.IN_TRANSIT_IN:
                    break;
                case Constant.IN_TRANSIT_OUT:
                    break;
                case Constant.BRANCH_OUT:
                    break;
                case Constant.TO_DELIVER:
                    sfbE.setShipmentState(
                            new ShipmentState()
                                    .setIdShipmentState(mapStates.get(Constant.BRANCH_OUT))
                                    .setFriendlyId(Constant.BRANCH_OUT));
                    break;
                case Constant.FINISHED:
                    break;
            }
            finalList.add(sfbE);

        }

        //Conteo
        for (ShipmentForBranch sfb : finalList) {
            switch (sfb.getShipmentState().getFriendlyId()) {
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
        branchDtoResponse.setQuantity(finalList.size());
        branchDtoResponse.setShipments(
                ShipmentForBranchParser.mapToDtoListFromShipmentForBranch(finalList));
        return branchDtoResponse;
    }
}
