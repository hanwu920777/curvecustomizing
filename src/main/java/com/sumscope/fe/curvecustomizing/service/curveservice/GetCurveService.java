package com.sumscope.fe.curvecustomizing.service.curveservice;

import com.sumscope.fe.curvecustomizing.entity.CurveDataModel;
import com.sumscope.fe.curvecustomizing.entity.CurveDataModelTo;
import com.sumscope.fe.curvecustomizing.model.response.GeneralResponse;
import com.sumscope.fe.curvecustomizing.model.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetCurveService {
    @Autowired
    private FindDataServiceImpl findDataServiceImpl;

    public GeneralResponse getInterestRateCurve(CurveDataModel curveDataModel) {
        List<CurveDataModelTo> interestRateData = findDataServiceImpl.findInterestRateData(curveDataModel.getUserId());
        return getGeneralResponse(curveDataModel, interestRateData);
    }

    private GeneralResponse getGeneralResponse(CurveDataModel curveDataModel, List<CurveDataModelTo> dataList) {
        for (int i = 0; i < dataList.size(); i++) {
            if (dataList.get(i).getCurveName().equals(curveDataModel.getCurveName())) {
                GeneralResponse generalResponse = new GeneralResponse();
                ResponseData<CurveDataModelTo> responseData = new ResponseData<>();
                responseData.setResult(dataList.get(i));
                generalResponse.setData(responseData);
                return generalResponse;
            }
        }
        return null;
    }

    public GeneralResponse getFactorCurve (CurveDataModel curveDataModel){
            List<CurveDataModelTo> factorData = findDataServiceImpl.findFactorData(curveDataModel.getUserId());
        return getGeneralResponse(curveDataModel, factorData);
    }
        }


