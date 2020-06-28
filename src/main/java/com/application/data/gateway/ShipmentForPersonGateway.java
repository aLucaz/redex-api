package com.application.data.gateway;

import com.application.core.model.business.ShipmentForPerson;
import com.application.core.model.dto.ShipmentForPersonDto;
import com.application.data.parser.ShipmentForPersonParser;
import com.application.data.repository.ShipmentForPersonRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ShipmentForPersonGateway {
    private final ShipmentForPersonRepository repository;

    public ShipmentForPersonGateway(ShipmentForPersonRepository repository) {
        this.repository = repository;
    }

    public void persistAll(List<ShipmentForPersonDto> shipmentForPersonDtoList){
        List<ShipmentForPerson> shipmentForPersonList = ShipmentForPersonParser.mapToRowList(shipmentForPersonDtoList);
        repository.saveAll(shipmentForPersonList);
    }
}
