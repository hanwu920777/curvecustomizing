package com.sumscope.fe.curvecustomizing.service;

import com.sumscope.fe.curvecustomizing.entity.FactorDTO;
import com.sumscope.fe.curvecustomizing.entity.InterestRateDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface InterestRateService extends CurveService{
    InterestRateDTO insertCurve(InterestRateDTO interestRateDTO);
    void editInterestRateCurve(InterestRateDTO interestRateDTO);
    Page<InterestRateDTO> findAll(int page, int pageSize);
    void deleteInterestRateByUserId(String userId);
//    void deleteInterestRateByUserId(String userId);
    InterestRateDTO findCurById(int id);
    List<InterestRateDTO> findAll();
    List<InterestRateDTO> findCurByUserId(String userId);
}
