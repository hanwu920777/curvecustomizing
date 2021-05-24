package com.sumscope.fe.curvecustomizing.service;

import com.sumscope.fe.curvecustomizing.entity.InterestRateDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface CurveService {

    void deleteAll();
    void deleteByCurveId(int id);

    }

