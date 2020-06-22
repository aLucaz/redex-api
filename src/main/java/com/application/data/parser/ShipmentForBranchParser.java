package com.application.data.parser;

import com.application.core.model.business.Branch;
import com.application.core.model.business.Package;
import com.application.core.model.business.Shipment;
import com.application.core.model.business.ShipmentForBranch;
import com.application.core.model.dto.ShipmentForBranchDto;
import com.application.rest.api.request.registerShipment.RegisterShipmentBranchWrapper;
import com.application.rest.api.request.registerShipment.RegisterShipmentRequest;

import java.util.ArrayList;
import java.util.List;

public class ShipmentForBranchParser {

    public static List<ShipmentForBranchDto> mapToDto(RegisterShipmentRequest request) {
        List<ShipmentForBranchDto> lista = new ArrayList<ShipmentForBranchDto>();
        for(RegisterShipmentBranchWrapper wra : request.getTripPlan()){
            lista.add(
                    new ShipmentForBranchDto()
                            .setChecked(false)
                            .setDepartureDate(wra.getDepartureDate())
                            .setFlightFriendlyId(wra.getFlightFriendlyId())
                            .setArrivalDate(wra.getArrivalDate())
                            .setIdBranch(wra.getOriginCity())
            );

        }
        return lista;
    }

    public static List<ShipmentForBranch> mapToRow(List<ShipmentForBranchDto> shipmentForBranchDtoList) {
        List<ShipmentForBranch> lista = new ArrayList<ShipmentForBranch>();
        for(ShipmentForBranchDto shipmentForBranchDto : shipmentForBranchDtoList){
            lista.add(
                    new ShipmentForBranch()
                            .setChecked(shipmentForBranchDto.getChecked())
                            .setFlightFriendlyId(shipmentForBranchDto.getFlightFriendlyId())
                            .setDepartureDate(shipmentForBranchDto.getDepartureDate())
                            .setArrivalDate(shipmentForBranchDto.getArrivalDate())
                            .setIdBranch(new Branch().setIdBranch(shipmentForBranchDto.getIdBranch()))
                            .setIdShipment(new Shipment().setIdShipment(shipmentForBranchDto.getIdShipment()))
            );

        }
        return lista;
    }


}
