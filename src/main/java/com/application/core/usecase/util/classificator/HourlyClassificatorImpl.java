package com.application.core.usecase.util.classificator;

import com.application.core.model.dto.IncidentDto;
import com.application.core.model.dto.ShipmentForBranchDto;
import com.application.core.model.dto.report.DetailHourDto;
import com.application.core.usecase.util.algorithm.util.Time;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class HourlyClassificatorImpl implements HourlyClassificator {
    @Override
    public List<DetailHourDto> classificateByHourOfDay(List<IncidentDto> incidents, List<ShipmentForBranchDto> routes, LocalDate localDate) {
        List<Integer> hours = Stream.iterate(0, num -> num + 1).limit(24).collect(Collectors.toList());
        List<DetailHourDto> detailHourDtoList = new ArrayList<>();
        hours.forEach(hour -> {
            Predicate<IncidentDto> byIncidentHour = incidentDto -> incidentDto.getIncidentDateTime().toLocalTime().getHour() == hour;
            List<IncidentDto> incidentFiltered = incidents.stream().filter(byIncidentHour).collect(Collectors.toList());
            LocalDateTime thisDateTime = LocalDateTime.of(localDate, LocalTime.of(hour, 0));
            Predicate<ShipmentForBranchDto> byRouteHour = route ->
                    ((route.getCurrentArrivalDateTime().isBefore(thisDateTime) ||
                            (route.getCurrentArrivalDateTime().isEqual(thisDateTime))) &&
                            route.getCurrentDepartureDateTime().isAfter(thisDateTime));
            List<ShipmentForBranchDto> routesFiltered = routes.stream().filter(byRouteHour).collect(Collectors.toList());

            DetailHourDto detailHourDto = new DetailHourDto()
                    .setQuantityOfIncidents(incidentFiltered.size())
                    .setQuantityOfShipments(routesFiltered.size())
                    .setTime(new Time().setHours(hour).setMinutes(0).toString());

            detailHourDtoList.add(detailHourDto);
        });
        return detailHourDtoList;
    }
}
