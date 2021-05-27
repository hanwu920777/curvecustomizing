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
public class InsertDataService {
    @Autowired
    private FindDataServiceImpl findDataServiceImpl;
    @Autowired
    private FactorCurveServiceImpl factorCurveServiceImpl;
    @Autowired
    private InterestRateCurveServiceImpl interestRateCurveServiceimpl;
    @Autowired
    private InterestRateDTO interestRateDTO;
    @Autowired
    private FactorDTO factorDTO;

    public GeneralResponse insertInterestRateData(CurveDataModel curveDataModel){
        boolean flag = true;
        for (int i = 0; i < findDataServiceImpl.findInterestRateData(curveDataModel.getUserId()).size(); i++) {
            if (curveDataModel.getCurveName().equals(findDataServiceImpl.findInterestRateData(curveDataModel.getUserId()).get(i).getCurveName())) {
                flag = false;
                break;
            }
        }
        if (flag == false) {
            GeneralResponse generalResponse = new GeneralResponse();
            ResponseData<String> responseData = new ResponseData<>();
            responseData.setResult("Repeated curveName");
            generalResponse.setData(responseData);
            return generalResponse;

        }else{
            interestRateDTO =new InterestRateDTO();
            BeanUtils.copyProperties(curveDataModel, interestRateDTO);
            interestRateDTO.setInterestRateAndFactorList(JSON.toJSONString(curveDataModel.getInterestRateAndFactorList()));
            //return interestRateCurveServiceimpl.insertCurve(interestRateDTO);
            GeneralResponse generalResponse = new GeneralResponse();
            ResponseData<InterestRateDTO> responseData = new ResponseData<>();
            responseData.setResult(interestRateCurveServiceimpl.insertCurve(interestRateDTO));
            generalResponse.setData(responseData);
            return generalResponse;

        }
    }

    public GeneralResponse insertFactorData(CurveDataModel curveDataModel){
        boolean flag = true;
        for (int i = 0; i < findDataServiceImpl.findFactorData(curveDataModel.getUserId()).size(); i++) {
            if (curveDataModel.getCurveName().equals(findDataServiceImpl.findFactorData(curveDataModel.getUserId()).get(i).getCurveName())) {
                flag = false;
                break;
            }
        }
        if (flag == false) {
            GeneralResponse generalResponse = new GeneralResponse();
            ResponseData<String> responseData = new ResponseData<>();
            responseData.setResult("Repeated curveName");
            generalResponse.setData(responseData);
            return generalResponse;
        }else{
            for(int i=0;i<curveDataModel.getInterestRateAndFactorList().size();i++){
                if(curveDataModel.getInterestRateAndFactorList().get(i).getInterestRateOrFactor()<0||curveDataModel.getInterestRateAndFactorList().get(i).getInterestRateOrFactor()>1){
                    GeneralResponse generalResponse = new GeneralResponse();
                    ResponseData<String> responseData = new ResponseData<>();
                    responseData.setResult("Wrong Typing,The value must be between 0 and 1");
                    generalResponse.setData(responseData);
                    return generalResponse;
                }
            }

            factorDTO =new FactorDTO();
            BeanUtils.copyProperties(curveDataModel, factorDTO);
            factorDTO.setInterestRateAndFactorList(JSON.toJSONString(curveDataModel.getInterestRateAndFactorList()));
            //return factorCurveServiceImpl.insertCurve(factorDTO);
            GeneralResponse generalResponse = new GeneralResponse();
            ResponseData<FactorDTO> responseData = new ResponseData<>();
            responseData.setResult(factorCurveServiceImpl.insertCurve(factorDTO));
            generalResponse.setData(responseData);
            return generalResponse;

        }
    }
}
