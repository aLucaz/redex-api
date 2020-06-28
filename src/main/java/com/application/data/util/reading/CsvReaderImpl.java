package com.application.data.util.reading;

import com.application.core.model.dto.EtFlightDto;
import com.application.core.model.dto.ShipmentRequestDto;
import com.application.data.parser.EtFlightParser;
import com.application.shared.Constant;
import com.application.shared.Galactus;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Component
public class CsvReaderImpl implements CsvReader {
    @SneakyThrows
    @Override
    public List<EtFlightDto> readEtFlightCsv() {
        List<EtFlightDto> etFlightDtoList = new ArrayList<>();
        LocalDate fromDate = LocalDate.now();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(Constant.CSV_FILE_PATH))) {
            String line;
            bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null) {
                Hashtable<String, String> etFlightDict = new Hashtable<>();
                String[] values = line.split(Constant.DELIMITER);
                etFlightDict.put("idEdFlight", values[0]);
                etFlightDict.put("friendlyId", values[1]);
                etFlightDict.put("departurePoint", values[2]);
                etFlightDict.put("arrivalPoint", values[3]);
                etFlightDict.put("departureTime", values[4]);
                etFlightDict.put("arrivalTime", values[5]);
                etFlightDict.put("etFlightDate", values[6]);
                etFlightDict.put("capacity", values[7]);
                etFlightDict.put("quantity", values[8]);
                etFlightDict.put("isActive", values[9]);
                // only add dates after the From date
                LocalDate etFlightDate = LocalDate.parse(etFlightDict.get("etFlightDate"));
                if (etFlightDate.compareTo(fromDate) >= 0)
                    etFlightDtoList.add(EtFlightParser.mapToDto(etFlightDict));
            }
        }
        return etFlightDtoList;
    }

    @SneakyThrows
    @Override
    public List<ShipmentRequestDto> readShipmentRequestCsv(MultipartFile file) {
        /*
         * Assuming this format
         * BIKF000008882-20200417-16:07-SGAS
         * dont do this at home, never.
         * */

        List<ShipmentRequestDto> shipmentRequestDtoList;
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
            shipmentRequestDtoList = readBufferedReader(bufferedReader);
        }
        return shipmentRequestDtoList;
    }

    @SneakyThrows
    @Override
    public List<ShipmentRequestDto> readShipmentRequestZip(MultipartFile file) {
        ZipInputStream zipInputStream = new ZipInputStream(file.getInputStream());
        ZipEntry entry;
        List<ShipmentRequestDto> allRequestList = new ArrayList<>();
        while ((entry = zipInputStream.getNextEntry()) != null) {
            byte[] bytes = zipInputStream.readNBytes(Math.toIntExact(entry.getSize()));
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(bytes)))) {
                List<ShipmentRequestDto> requestList = readBufferedReader(bufferedReader);
                allRequestList.addAll(requestList);
            }
        }
        return allRequestList;
    }

    @SneakyThrows
    @Override
    public List<ShipmentRequestDto> readBufferedReader(BufferedReader bufferedReader) {
        List<ShipmentRequestDto> shipmentRequestDtoList = new ArrayList<>();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            String[] values = line.split(Constant.REQUEST_DELIMITER);
            ShipmentRequestDto shipmentRequestDto = new ShipmentRequestDto()
                    .setFrom(values[0].substring(0, 4))
                    .setTo(values[3])
                    .setRequestDateTime(Galactus.parseRequestFormatToLocalDateTime(values[1], values[2]));
            shipmentRequestDtoList.add(shipmentRequestDto);
        }
        return shipmentRequestDtoList;
    }


}
