package com.application.core.usecase.util.classificator;

import com.application.core.model.dto.IncidentDto;
import com.application.core.model.dto.ShipmentForBranchDto;
import com.application.core.model.dto.report.DetailDayDto;
import com.application.core.model.dto.report.DetailHourDto;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Component
public class DailyClassificatorImpl implements DailyClassificator {

    private final HourlyClassificator hourlyClassificator;

    public DailyClassificatorImpl(HourlyClassificatorImpl hourlyClassificator) {
        this.hourlyClassificator = hourlyClassificator;
    }

    @Override
    public List<DetailDayDto> classificateByDateAndHour(List<LocalDate> dateRange, List<IncidentDto> incidents, List<ShipmentForBranchDto> routes) {
        List<DetailDayDto> detailDayDtoList = new ArrayList<>();
        dateRange.forEach(localDate -> {
            // first we get only the
            Predicate<IncidentDto> byIncidentDate = incidentDto -> incidentDto.getIncidentDateTime().toLocalDate().isEqual(localDate);
            List<IncidentDto> incidentsFiltered = incidents.stream().filter(byIncidentDate).collect(Collectors.toList());
            Predicate<ShipmentForBranchDto> byArrivalDate = route -> route.getCurrentArrivalDateTime().toLocalDate().isEqual(localDate);
            List<ShipmentForBranchDto> routesFiltered = routes.stream().filter(byArrivalDate).collect(Collectors.toList());
            // once we have the filtered lists
            DetailDayDto detailDayDto = new DetailDayDto()
                    .setDate(localDate)
                    .setQuantityOfIncidents(incidentsFiltered.size())
                    .setQuantityOfShipments(routesFiltered.size());
            // we now filter by hours
            List<DetailHourDto> hourDtoList = hourlyClassificator.classificateByHourOfDay(incidentsFiltered, routesFiltered);
            detailDayDtoList.add(detailDayDto.setHoursDetail(hourDtoList));
        });
        return detailDayDtoList;
    }
}
