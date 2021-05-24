package com.sumscope.fe.curvecustomizing.service;

import com.sumscope.fe.curvecustomizing.entity.FactorDTO;
import com.sumscope.fe.curvecustomizing.entity.InterestRateDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface FactorService extends CurveService{
    FactorDTO insertCurve(FactorDTO factorDTO);
    void editFactorCurve(FactorDTO factorDTO);
    void deleteFactorByUserId(String userId);
    Page<FactorDTO> findAll(int page, int pageSize);
    FactorDTO findCurById(int id);
    List<FactorDTO> findCurByUserId(String userId);
    List<FactorDTO> findAll();

}
