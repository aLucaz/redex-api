package com.application.core.usecase.util.detector.incident;

import com.application.core.model.dto.BranchDto;
import com.application.core.model.dto.IncidentDto;
import com.application.core.model.dto.ShipmentForBranchDto;

import java.time.LocalDateTime;
import java.util.List;

public interface IncidentDetector {
    IncidentDto detectIncident(List<ShipmentForBranchDto> tripPlans, LocalDateTime arrivalDateTime, LocalDateTime departureDatetime, BranchDto requestBranch, Boolean isASimulation);

    IncidentDto detectIncident(String flightFriendlyId, Boolean isASimulation);
}
