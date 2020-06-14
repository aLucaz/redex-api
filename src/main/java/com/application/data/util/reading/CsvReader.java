package com.application.data.util.reading;

import com.application.core.model.dto.EtFlightDto;

import java.util.List;

public interface CsvReader {
    List<EtFlightDto> readEtFlightCsv();
}
