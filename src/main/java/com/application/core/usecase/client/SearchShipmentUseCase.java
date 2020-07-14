package com.application.core.usecase.client;

import com.application.core.model.business.Shipment;
import com.application.core.model.business.ShipmentForBranch;
import com.application.core.model.business.ShipmentForPerson;
import com.application.core.model.dto.PersonDto;
import com.application.core.model.dto.SearchShipmentDto;
import com.application.core.model.dto.ShipmentDto;
import com.application.core.model.dto.ShipmentForBranchDto;
import com.application.core.model.dto.ShipmentForPersonDto;
import com.application.data.gateway.PersonGateway;
import com.application.data.gateway.ShipmentForBranchGateway;
import com.application.data.gateway.ShipmentGateway;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchShipmentUseCase {
    private final ShipmentGateway shipmentGateway;
    private final PersonGateway personGateway;

    public SearchShipmentUseCase(ShipmentGateway shipmentGateway, PersonGateway personGateway) {
        this.shipmentGateway = shipmentGateway;
        this.personGateway = personGateway;
    }

    public SearchShipmentDto execute(SearchShipmentDto request){
        ShipmentDto shipment = shipmentGateway.findByReferenceCode(request.getReferenceCode());
        if (!shipment.getIsActive())
            return null;
        List<ShipmentForBranchDto> routes = new ArrayList<>(shipment.getShipmentForBranches());
        List<ShipmentForPersonDto> shipmentForPersonDtoList = new ArrayList<>(shipment.getShipmentForPeople());
        List<PersonDto> people = new ArrayList<>();
        shipmentForPersonDtoList.forEach(shipmentForPersonDto -> {
            people.add(personGateway.findById(shipmentForPersonDto.getIdPerson()));
        });
        return new SearchShipmentDto()
                .setTripPlan(routes)
                .setPeople(people);
    }
}
