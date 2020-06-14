package com.application.data.util.reading;

import com.application.core.model.dto.EtFlightDto;
import com.application.data.parser.EtFlightParser;
import com.application.shared.Constant;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

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
}
