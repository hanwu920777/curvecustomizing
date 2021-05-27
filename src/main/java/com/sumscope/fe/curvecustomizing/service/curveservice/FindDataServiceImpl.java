package com.sumscope.fe.curvecustomizing.service.curveservice;

import com.alibaba.fastjson.JSONArray;
import com.sumscope.fe.curvecustomizing.entity.*;
import com.sumscope.fe.curvecustomizing.service.impl.FactorCurveServiceImpl;
import com.sumscope.fe.curvecustomizing.service.impl.InterestRateCurveServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class FindDataServiceImpl implements FindDataService {
    @Autowired
    InterestRateCurveServiceImpl interestRateCurveServiceImpl;
    @Autowired
    FactorCurveServiceImpl factorCurveServiceImpl;

    @Override
    public List<CurveDataModelTo> findInterestRateData(String userId){
        List<InterestRateDTO> interestRateList = interestRateCurveServiceImpl.findCurByUserId(userId);
        List<CurveDataModelTo> result=new ArrayList<>();
        CurveDataModelTo curveDataModelTo;
        for (InterestRateDTO dto: interestRateList) {
            curveDataModelTo=new CurveDataModelTo();
            String list = dto.getInterestRateAndFactorList();
            List<InterestRateAndFactorModelTo> interestRateModels = JSONArray.parseArray(list, InterestRateAndFactorModelTo.class);

            LocalDate dateTime2 = LocalDate.parse(dto.getCurveDate(),  DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            BeanUtils.copyProperties(dto,curveDataModelTo);
            curveDataModelTo.setCurveDate(dateTime2);
            curveDataModelTo.setInterestRateAndFactorList(interestRateModels);


            result.add(curveDataModelTo);
        }

        return result;
    }

    @Override
    public List<CurveDataModelTo> findFactorData(String userId){
        List<FactorDTO> factorList = factorCurveServiceImpl.findCurByUserId(userId);
        List<CurveDataModelTo> result1=new ArrayList<>();
        CurveDataModelTo curveDataModelTo;
        for (FactorDTO dto: factorList) {
            curveDataModelTo=new CurveDataModelTo();
            String interestRateList = dto.getInterestRateAndFactorList();
            List<InterestRateAndFactorModelTo> factorModels = JSONArray.parseArray(interestRateList, InterestRateAndFactorModelTo.class);
            LocalDate dateTime = LocalDate.parse(dto.getCurveDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            BeanUtils.copyProperties(dto,curveDataModelTo);
            curveDataModelTo.setCurveDate(dateTime);
            curveDataModelTo.setInterestRateAndFactorList(factorModels);
            result1.add(curveDataModelTo);
        }

        return result1;
    }
}
