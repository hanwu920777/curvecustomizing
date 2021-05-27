package com.sumscope.fe.curvecustomizing.service.curveservice;

import com.sumscope.fe.curvecustomizing.model.response.GeneralResponse;
import com.sumscope.fe.curvecustomizing.model.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ListNameService {
    @Autowired
    private FindDataServiceImpl findDataServiceImpl;

    public GeneralResponse listName(String userId) {
        List<Map<String,String>> list=new ArrayList<>();
        for (int i = 0; i < findDataServiceImpl.findInterestRateData(userId).size(); i++) {
            Map<String, String> hashMap1=new HashMap<>();
            hashMap1.put("curveName", findDataServiceImpl.findInterestRateData(userId).get(i).getCurveName());
            hashMap1.put("curveModel", findDataServiceImpl.findInterestRateData(userId).get(i).getCurveModel());
            list.add(hashMap1);
        }
        for(int i = 0; i< findDataServiceImpl.findFactorData(userId).size(); i++){
            Map<String, String> hashMap2=new HashMap<>();
            hashMap2.put("curveName", findDataServiceImpl.findFactorData(userId).get(i).getCurveName());
            hashMap2.put("curveModel", findDataServiceImpl.findFactorData(userId).get(i).getCurveModel());
            list.add(hashMap2);
        }


        //result.forEach(System.out::println);
        GeneralResponse generalResponse = new GeneralResponse();
        ResponseData<List<Map<String,String>>> responseData = new ResponseData<>();
        responseData.setResult(list);
        generalResponse.setData(responseData);
        return generalResponse;
    }


}
