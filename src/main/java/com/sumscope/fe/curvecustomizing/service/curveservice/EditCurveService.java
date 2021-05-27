package com.sumscope.fe.curvecustomizing.service.curveservice;

import com.alibaba.fastjson.JSON;
import com.sumscope.fe.curvecustomizing.entity.CurveDataModel;
import com.sumscope.fe.curvecustomizing.entity.FactorDTO;
import com.sumscope.fe.curvecustomizing.entity.InterestRateDTO;
import com.sumscope.fe.curvecustomizing.model.response.GeneralResponse;
import com.sumscope.fe.curvecustomizing.model.response.ResponseData;
import com.sumscope.fe.curvecustomizing.service.impl.FactorCurveServiceImpl;
import com.sumscope.fe.curvecustomizing.service.impl.InterestRateCurveServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EditCurveService {
    @Autowired
    private FindDataServiceImpl findDataServiceImpl;
    @Autowired
    private InterestRateDTO interestRateDTO;
    @Autowired
    private FactorCurveServiceImpl factorCurveServiceImpl;
    @Autowired
    InterestRateCurveServiceImpl interestRateCurveServiceImpl;

    @Autowired
    private FactorDTO factorDTO;

    public GeneralResponse editinterestRateCurve(CurveDataModel curveDataModel){
        interestRateDTO= new InterestRateDTO();

        boolean flag=false;
        int i=0;
        for (i = 0; i < findDataServiceImpl.findInterestRateData(curveDataModel.getUserId()).size(); i++) {
            if (findDataServiceImpl.findInterestRateData(curveDataModel.getUserId()).get(i).getCurveName().equals(curveDataModel.getCurveName())) {
                flag=true;
                break;
            }
        }
        if(flag==true){

            int id = findDataServiceImpl.findInterestRateData(curveDataModel.getUserId()).get(i).getId();
            BeanUtils.copyProperties(curveDataModel,interestRateDTO);
            interestRateDTO.setInterestRateAndFactorList(JSON.toJSONString(curveDataModel.getInterestRateAndFactorList()));
            factorDTO.setCurveDate(curveDataModel.getCurveDate());
            interestRateDTO.setId(id);
            interestRateCurveServiceImpl.editInterestRateCurve(interestRateDTO);
            GeneralResponse generalResponse = new GeneralResponse();
            ResponseData<String> responseData = new ResponseData<>();
            responseData.setResult(
                            "Editorial success");
            generalResponse.setData(responseData);
            return generalResponse;
        }else {
            return null;
        }
    }
    public GeneralResponse editFactorCurve(CurveDataModel curveDataModel){
        factorDTO=new FactorDTO();

        boolean flag=false;
        int i=0;
        for (i = 0; i < findDataServiceImpl.findFactorData(curveDataModel.getUserId()).size(); i++) {
            if (findDataServiceImpl.findFactorData(curveDataModel.getUserId()).get(i).getCurveName().equals(curveDataModel.getCurveName())) {
                flag=true;
                break;
            }
        }
        if(flag==true){

            int id = findDataServiceImpl.findFactorData(curveDataModel.getUserId()).get(i).getId();
            BeanUtils.copyProperties(curveDataModel,factorDTO);
            factorDTO.setInterestRateAndFactorList(JSON.toJSONString(curveDataModel.getInterestRateAndFactorList()));
            factorDTO.setCurveDate(curveDataModel.getCurveDate());
            factorDTO.setId(id);
            factorCurveServiceImpl.editFactorCurve(factorDTO);
            GeneralResponse generalResponse = new GeneralResponse();
            ResponseData<String> responseData = new ResponseData<>();
            responseData.setResult(
                    "Editorial success");
            generalResponse.setData(responseData);
            return generalResponse;
        }else {
            return null;
        }
    }
}
