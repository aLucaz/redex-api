package com.application.data.repository;

import com.application.core.model.business.Shipment;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ShipmentRepository extends CrudRepository<Shipment, Integer> {
    List<Shipment> findAllByIsSimulatedAndIsActive(Boolean isSimulated, Boolean isActive);
    List<Shipment> findAllByIsActive(Boolean isActive);
    Shipment findByReferenceCode(String referenceCode);
    Shipment findByIdShipment(Integer idShipment);
    List<Shipment> findAllByIsActiveAndIsSimulatedAndArrivalPointAndArrivalDateTimeBetween(
            Boolean isActive,
            Boolean isSimulated,
            String arrivalPoint,
            LocalDateTime start,
            LocalDateTime end
    );

    List<Shipment> findByArrivalPoint(String arrivalPoint);
}
