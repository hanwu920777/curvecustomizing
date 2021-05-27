package com.sumscope.fe.curvecustomizing.service.curveservice;

import com.sumscope.fe.curvecustomizing.entity.CurveDataModelTo;

import java.util.List;

public interface FindDataService {
    List<CurveDataModelTo> findFactorData(String userId);
    List<CurveDataModelTo> findInterestRateData(String userId);
}
