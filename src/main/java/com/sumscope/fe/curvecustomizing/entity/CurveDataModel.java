package com.sumscope.fe.curvecustomizing.entity;

import lombok.Data;
import org.springframework.stereotype.Component;
import java.util.List;


@Data
@Component
public class CurveDataModel {
    private String userId;
    private String curveName;
    private String curveModel;
    private String curveDate;
    private String insertType;
    private List<InterestRateAndFactorModel> interestRateAndFactorList;
}
