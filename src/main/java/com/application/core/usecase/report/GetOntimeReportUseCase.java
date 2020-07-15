package com.application.core.usecase.report;

import com.application.core.model.dto.BranchDto;
import com.application.core.model.dto.report.DetailOnTimeBranchDto;
import com.application.core.model.dto.report.OnTimeReportDto;
import com.application.data.gateway.BranchGateway;
import com.application.data.gateway.ShipmentGateway;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

        });

        return null;
    }
}
