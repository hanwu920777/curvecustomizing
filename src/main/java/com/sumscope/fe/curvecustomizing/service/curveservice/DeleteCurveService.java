package com.sumscope.fe.curvecustomizing.service.curveservice;

import com.sumscope.fe.curvecustomizing.entity.CurveDataModel;
import com.sumscope.fe.curvecustomizing.model.response.GeneralResponse;
import com.sumscope.fe.curvecustomizing.model.response.ResponseData;
import com.sumscope.fe.curvecustomizing.service.impl.FactorCurveServiceImpl;
import com.sumscope.fe.curvecustomizing.service.impl.InterestRateCurveServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteCurveService {

    @Autowired
    private FactorCurveServiceImpl factorCurveServiceImpl;
    @Autowired
    private InterestRateCurveServiceImpl interestRateCurveServiceimpl;

    public GeneralResponse delInterestRateCurve(CurveDataModel curveDataModel) {
        boolean flag = false;
        int i = 0;
        for (i = 0; i < interestRateCurveServiceimpl.findCurByUserId(curveDataModel.getUserId()).size(); i++) {
            if (interestRateCurveServiceimpl.findCurByUserId(curveDataModel.getUserId()).get(i).getCurveName().equals(curveDataModel.getCurveName())) {
                flag = true;
                break;
            }
        }
        if (flag == true) {
            int id = interestRateCurveServiceimpl.findCurByUserId(curveDataModel.getUserId()).get(i).getId();
            interestRateCurveServiceimpl.deleteByCurveId(id);
            GeneralResponse generalResponse = new GeneralResponse();
            ResponseData<String> responseData = new ResponseData<>();
            responseData.setResult(
                    "Deletion successful");
            generalResponse.setData(responseData);
            return generalResponse;
        } else {
            return null;
        }
    }

    public GeneralResponse delFactorCurve(CurveDataModel curveDataModel) {
        boolean flag = false;
        int i = 0;
        for (i = 0; i < factorCurveServiceImpl.findCurByUserId(curveDataModel.getUserId()).size(); i++) {
            if (factorCurveServiceImpl.findCurByUserId(curveDataModel.getUserId()).get(i).getCurveName().equals(curveDataModel.getCurveName())) {
                flag = true;
                break;
            }
        }
        if (flag == true) {
            int id = factorCurveServiceImpl.findCurByUserId(curveDataModel.getUserId()).get(i).getId();
            factorCurveServiceImpl.deleteByCurveId(id);
            GeneralResponse generalResponse = new GeneralResponse();
            ResponseData<String> responseData = new ResponseData<>();
            responseData.setResult(
                    "Deletion successful");
            generalResponse.setData(responseData);
            return generalResponse;
        } else {
            return null;
        }
    }





}
