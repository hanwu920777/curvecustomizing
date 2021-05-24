package com.sumscope.fe.curvecustomizing.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Data
@Component
public class CurveDataModelTo {
    private int id;
    private String userId;
    private String curveName;
    private String curveModel;
    private LocalDate curveDate;
    private String insertType;
    private List<InterestRateAndFactorModelTo> interestRateAndFactorList;
}
