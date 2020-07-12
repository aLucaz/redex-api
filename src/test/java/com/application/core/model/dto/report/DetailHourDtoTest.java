package com.application.core.model.dto.report;

import com.application.core.usecase.util.algorithm.util.Time;
import org.junit.jupiter.api.Test;

class DetailHourDtoTest {

    @Test
    void testToString() {
        DetailHourDto detailHourDto = new DetailHourDto()
                .setQuantityOfIncidents(10)
                .setQuantityOfShipments(15);

        System.out.println(detailHourDto.getTime());
    }
}