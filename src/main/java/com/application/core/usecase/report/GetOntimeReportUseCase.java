package com.application.core.usecase.report;

import com.application.core.model.dto.BranchDto;
import com.application.core.model.dto.ShipmentDto;
import com.application.core.model.dto.report.DetailOnTimeBranchDto;
import com.application.core.model.dto.report.OnTimeReportDto;
import com.application.core.usecase.util.algorithm.util.DrStrange;
import com.application.data.gateway.BranchGateway;
import com.application.data.gateway.ShipmentGateway;
import com.application.shared.Constant;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class GetOntimeReportUseCase {
    private final BranchGateway branchGateway;
    private final ShipmentGateway shipmentGateway;

    public GetOntimeReportUseCase(BranchGateway branchGateway, ShipmentGateway shipmentGateway) {
        this.branchGateway = branchGateway;
        this.shipmentGateway = shipmentGateway;
    }

    public OnTimeReportDto execute(OnTimeReportDto request) {
        List<BranchDto> branchDtoList = branchGateway.findAllActive();
        List<DetailOnTimeBranchDto> detailOnTimeBranchDtos = new ArrayList<>();

        branchDtoList.forEach(branchDto -> {
            /*
             * We find all shipment that is:
             * active(not deleted),
             * final branch is the actual branch
             * arrival date time is in the range date requested  */
            List<ShipmentDto> shipmentDtoList = shipmentGateway.findAllForOnTimeReport(request, branchDto.getFriendlyId());
            HashMap<String, Integer> detail = countElementsOnTime(shipmentDtoList);
            DetailOnTimeBranchDto detailOnTimeBranchDto = new DetailOnTimeBranchDto()
                    .setQuantityOnTime(detail.get(Constant.ON_TIME_KEY))
                    .setQuantityLate(detail.get(Constant.LATE_KEY))
                    .setIdBranch(branchDto.getIdBranch())
                    .setNameBranch(branchDto.getName());

            detailOnTimeBranchDtos.add(detailOnTimeBranchDto);
        });

        return new OnTimeReportDto()
                .setBranchesDetail(detailOnTimeBranchDtos);
    }

    public HashMap<String, Integer> countElementsOnTime(List<ShipmentDto> shipmentDtoList) {
        HashMap<String, Integer> detail = new HashMap<>();
        detail.put(Constant.ON_TIME_KEY, 0);
        detail.put(Constant.LATE_KEY, 0);

        for (ShipmentDto shipmentDto : shipmentDtoList) {
            String realDelayTime = shipmentDto.getTotalDelayTime();
            if (shipmentDto.getSameContinent()) {
                if (DrStrange.smallerThan(realDelayTime, Constant.MAX_DELAY_SAME_CONT))
                    detail.put(Constant.ON_TIME_KEY, detail.get(Constant.ON_TIME_KEY) + 1);
                else
                    detail.put(Constant.LATE_KEY, detail.get(Constant.LATE_KEY) + 1);
            } else {
                if (DrStrange.smallerThan(realDelayTime, Constant.MAX_DELAY_DIFF_CONT))
                    detail.put(Constant.ON_TIME_KEY, detail.get(Constant.ON_TIME_KEY) + 1);
                else
                    detail.put(Constant.LATE_KEY, detail.get(Constant.LATE_KEY) + 1);
            }
        }

//        shipmentDtoList.forEach(shipmentDto -> {
//            String realDelayTime = shipmentDto.getTotalDelayTime();
//            if (shipmentDto.getSameContinent()) {
//                if (DrStrange.smallerThan(realDelayTime, Constant.MAX_DELAY_SAME_CONT))
//                    detail.put(Constant.ON_TIME_KEY, detail.get(Constant.ON_TIME_KEY) + 1);
//                else
//                    detail.put(Constant.LATE_KEY, detail.get(Constant.LATE_KEY) + 1);
//            } else {
//                if (DrStrange.smallerThan(realDelayTime, Constant.MAX_DELAY_DIFF_CONT))
//                    detail.put(Constant.ON_TIME_KEY, detail.get(Constant.ON_TIME_KEY) + 1);
//                else
//                    detail.put(Constant.LATE_KEY, detail.get(Constant.LATE_KEY) + 1);
//            }
//        });
        return detail;
    }
}
