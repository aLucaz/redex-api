package com.application.data.util.reading;

import com.application.core.model.dto.EtFlightDto;
import com.application.core.model.dto.ShipmentRequestDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CsvReader {
    List<EtFlightDto> readEtFlightCsv();
    List<ShipmentRequestDto> readShipmentRequestCsv(MultipartFile file);
}
