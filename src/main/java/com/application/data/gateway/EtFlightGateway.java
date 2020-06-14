package com.application.data.gateway;

import com.application.core.model.business.EtFlight;
import com.application.core.model.dto.EtFlightDto;
import com.application.core.model.dto.RouteDto;
import com.application.data.parser.EtFlightParser;
import com.application.data.repository.EtFlightRepository;
import com.application.data.util.reading.CsvReader;
import com.application.data.util.reading.CsvReaderImpl;
import com.application.shared.Constant;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Iterator;
import java.util.List;

@Repository
public class EtFlightGateway {
    private final EtFlightRepository repository;
    private final CsvReader csvReader;

    public EtFlightGateway(EtFlightRepository repository, CsvReaderImpl csvReader) {
        this.repository = repository;
        this.csvReader = csvReader;
    }

    public void persistMassive() {
        // read from csv file only after the date
        List<EtFlightDto> etFlightDtoList = csvReader.readEtFlightCsv();
        List<EtFlight> etFlightList = EtFlightParser.mapToRowList(etFlightDtoList);
        // save massive flights into the database
        repository.saveAll(etFlightList);
    }

    public List<EtFlightDto> findAllInRange(RouteDto routeDto) {
        LocalDateTime requestDateTime = routeDto.getRequestDateTime();
        LocalDate startRangeDate = requestDateTime.toLocalDate();
        // extract time and add the off mandatory hours given by the business
        LocalTime startRangeTime = requestDateTime.toLocalTime().plusHours(Constant.HOURS_OFF_TO_REQUEST_PATH);

        LocalDate endRangeDate = routeDto.getSameContinent().equals(Constant.TRUE) ?
                startRangeDate.plusDays(Constant.SAME_CONTINENT_AVAILABLE_DAYS) :
                startRangeDate.plusDays(Constant.ANOTHER_CONTINENT_AVAILABLE_DAYS);
        // get only the flights that are in the dates range
        List<EtFlight> etFlightListInDatesRange = repository.findAllByEtFlightDateBetweenAndIsActive(startRangeDate.toString(), endRangeDate.toString(), Constant.ACTIVE);
        // extract flights that arent in the time range
        Iterator<EtFlight> iterator = etFlightListInDatesRange.iterator();
        while (iterator.hasNext()) {
            EtFlight etFlight = iterator.next();
            LocalTime departureTime = LocalTime.parse(etFlight.getDepartureTime(), Constant.PROP_TIME_FORMATTER);
            LocalDate etFlightDate = LocalDate.parse(etFlight.getEtFlightDate(), Constant.DATE_FORMATTER);
            if ((etFlightDate.compareTo(startRangeDate) == 0) && (departureTime.compareTo(startRangeTime) < 0)) {
                iterator.remove();
            }
        }
        return EtFlightParser.mapToDtoList(etFlightListInDatesRange);
    }
}
