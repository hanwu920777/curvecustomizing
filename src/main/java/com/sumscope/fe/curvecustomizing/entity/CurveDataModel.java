package com.sumscope.fe.curvecustomizing.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.stereotype.Component;
import java.util.List;


@Data
@Component
@ApiModel(value="曲线模型")
public class CurveDataModel {
    private String userId;
    private String curveName;
    private String curveModel;
    private String curveDate;
    private String insertType;
    private List<InterestRateAndFactorModel> interestRateAndFactorList;
}
