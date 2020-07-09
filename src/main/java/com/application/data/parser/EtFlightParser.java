package com.application.data.parser;

import com.application.core.model.business.EtFlight;
import com.application.core.model.dto.EtFlightDto;
import com.application.core.model.dto.EtFlightFiltersDto;
import com.application.rest.api.request.GetFilteredEtFlightListRequest;

import java.sql.*;
import java.time.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class EtFlightParser {
    public static EtFlightDto mapToDto(Hashtable<String, String> etFlightDict) {
        return new EtFlightDto()
                .setIdEdFlight(Integer.parseInt(etFlightDict.get("idEdFlight")))
                .setFriendlyId(etFlightDict.get("friendlyId"))
                .setDeparturePoint(etFlightDict.get("departurePoint"))
                .setArrivalPoint(etFlightDict.get("arrivalPoint"))
                .setDepartureTime(etFlightDict.get("departureTime"))
                .setArrivalTime(etFlightDict.get("arrivalTime"))
                .setEtFlightDate(etFlightDict.get("etFlightDate"))
                .setCapacity(Integer.parseInt(etFlightDict.get("capacity")))
                .setQuantity(Integer.parseInt(etFlightDict.get("quantity")))
                .setIsActive(Integer.parseInt(etFlightDict.get("isActive")));
    }

    public static EtFlightDto mapToDto(EtFlight etFlight) {
        return new EtFlightDto()
                .setIdEdFlight(etFlight.getIdEdFlight())
                .setFriendlyId(etFlight.getFriendlyId())
                .setDeparturePoint(etFlight.getDeparturePoint())
                .setArrivalPoint(etFlight.getArrivalPoint())
                .setDepartureTime(etFlight.getDepartureTime())
                .setArrivalTime(etFlight.getArrivalTime())
                .setEtFlightDate(etFlight.getEtFlightDate())
                .setCapacity(etFlight.getCapacity())
                .setQuantity(etFlight.getQuantity())
                .setIsActive(etFlight.getIsActive());
    }

    public static List<EtFlightDto> mapToDtoList(List<EtFlight> etFlightList) {
        List<EtFlightDto> etFlightDtoList = new ArrayList<>();
        for (EtFlight etFlight : etFlightList) {
            etFlightDtoList.add(mapToDto(etFlight));
        }
        return etFlightDtoList;
    }

    public static EtFlight mapToRow(EtFlightDto etFlightDto) {
        return new EtFlight()
                .setIdEdFlight(etFlightDto.getIdEdFlight())
                .setFriendlyId(etFlightDto.getFriendlyId())
                .setDeparturePoint(etFlightDto.getDeparturePoint())
                .setArrivalPoint(etFlightDto.getArrivalPoint())
                .setDepartureTime(etFlightDto.getDepartureTime())
                .setArrivalTime(etFlightDto.getArrivalTime())
                .setEtFlightDate(etFlightDto.getEtFlightDate())
                .setCapacity(etFlightDto.getCapacity())
                .setQuantity(etFlightDto.getQuantity())
                .setIsActive(etFlightDto.getIsActive());
    }

    public static List<EtFlight> mapToRowList(List<EtFlightDto> etFlightDtoList) {
        List<EtFlight> etFlightList = new ArrayList<>();
        for (EtFlightDto etFlightDto : etFlightDtoList) {
            etFlightList.add(mapToRow(etFlightDto));
        }
        return etFlightList;
    }

    public static EtFlightFiltersDto mapToDtoFilters(GetFilteredEtFlightListRequest request) {
        EtFlightFiltersDto filters = new EtFlightFiltersDto();
        filters.setDeparturePoint(request.getDeparturePoint());
        filters.setEtFlightDate(String.valueOf(Date.valueOf(request.getEtFlightDate())));
        return filters;
    }
}
