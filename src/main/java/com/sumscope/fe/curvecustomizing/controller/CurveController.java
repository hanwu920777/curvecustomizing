package com.sumscope.fe.curvecustomizing.controller;

import com.sumscope.fe.curvecustomizing.entity.*;

import com.sumscope.fe.curvecustomizing.model.response.GeneralResponse;
import com.sumscope.fe.curvecustomizing.service.curveservice.*;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/irs")
@Slf4j
public class CurveController {


    @Autowired
    private InsertDataService insertDataService;
    @Autowired
    private ListNameService listNameService;
    @Autowired
    private DeleteCurveService deleteCurveService;
    @Autowired
    private EditCurveService editCurveService;
    @Autowired
    private GetCurveService getCurveService;

    //插入一条新的曲线
    @ApiOperation(value = "添加一条新曲线", notes = "添加曲线到数据库")
    @PostMapping("/insertData")
    public GeneralResponse insertInterestRate(@RequestBody CurveDataModel curveDataModel) {
        if (curveDataModel.getCurveModel().equals("INTERESTRATE_CURVE")) {
            log.info("插入曲线成功");
            return insertDataService.insertInterestRateData(curveDataModel);
        } else if (curveDataModel.getCurveModel().equals("FACTOR_CURVE")) {
            return insertDataService.insertFactorData(curveDataModel);
        } else {
            return null;
        }

    }


    //根据userid列出该用户名下所有曲线名称
    @ApiOperation(value = "获取所有曲线名称", notes = "根据userid获取该用户名下所有曲线名称")
    @PostMapping("/getAllCurveName")
    public GeneralResponse findCurveName(@RequestBody CurveDataModel curveDataModel) {
        return listNameService.listName(curveDataModel.getUserId());
    }

    //根据曲线名称获取某用户对应的曲线详细信息
    @ApiOperation(value = "获取曲线详细信息", notes = "根据curveName和userId获取该用户某曲线详细信息")
    @PostMapping("/getDetails")
    public GeneralResponse getDetails(@RequestBody CurveDataModel curveDataModel) {

        if (curveDataModel.getCurveModel().equals("INTERESTRATE_CURVE")) {
                return getCurveService.getInterestRateCurve(curveDataModel);
            }
        else if (curveDataModel.getCurveModel().equals("FACTOR_CURVE")) {
           return getCurveService.getFactorCurve(curveDataModel);
        }else {
            return null;
        }
    }



    //根据curveName删除该用户名下对应的曲线
    @ApiOperation(value = "删除曲线", notes = "根据userid和curveName删除用户名下某条曲线")
    @PostMapping("/delCurve")
    public GeneralResponse delCurve(@RequestBody CurveDataModel curveDataModel) {
        if (curveDataModel.getCurveModel().equals("INTERESTRATE_CURVE")) {
            return deleteCurveService.delInterestRateCurve(curveDataModel);
        } else if (curveDataModel.getCurveModel().equals("FACTOR_CURVE")) {
            return deleteCurveService.delFactorCurve(curveDataModel);

        } else {
            return null;
        }

    }


    //编辑某用户某条零息利率曲线
    @ApiOperation(value = "编辑曲线", notes = "可编辑interestRateAndFactorList和Date")
    @PostMapping("/editCurve")
    public GeneralResponse editInterestCurve(@RequestBody CurveDataModel curveDataModel) {
        if (curveDataModel.getCurveModel().equals("INTERESTRATE_CURVE")) {
            return editCurveService.editinterestRateCurve(curveDataModel);
        } else if (curveDataModel.getCurveModel().equals("FACTOR_CURVE")) {
            return editCurveService.editFactorCurve(curveDataModel);
        } else {
            return null;
        }
    }
}
//    //根据曲线名称获取某用户对应的贴现因子曲线的详细信息
//    @GetMapping("/findFactorDetails/{userId}/{curveName}")
//    public GeneralResponse findFactorDetails(@PathVariable String userId,@PathVariable String curveName){
//
//        boolean flag=false;
//        int i=0;
//        for(i=0; i< findDataService.findFactorData(userId).size(); i++) {
//            if (curveName.equals(findDataService.findFactorData(userId).get(i).getCurveName())) {
//                flag = true;
//                break;
//            }
//        }
//        if(flag==true){
//            //return findData.findFactorData(userId).get(i);
//            GeneralResponse generalResponse = new GeneralResponse();
//            ResponseData<CurveDataModelTo> responseData = new ResponseData<>();
//            responseData.setResult(findDataService.findFactorData(userId).get(i));
//            generalResponse.setData(responseData);
//            return generalResponse;
//        }else{
//            return null;
//        }
//    }
//    //查询零息利率表中所有信息
//    @GetMapping("/queryinterestRate")
//    public GeneralResponse queryinterestRate(Integer page, HttpServletResponse response){
//
//        //异步请求使用
//        response.setHeader("Access-Control-Allow-Origin","*");
//        if(page==null || page<=0){
//            page = 0;
//        }else{
//            page -= 1;
//        }
//        //return  interestRateCurveServiceimpl.findAll(page,5);
//        GeneralResponse generalResponse = new GeneralResponse();
//        ResponseData<Page<InterestRateDTO>> responseData = new ResponseData<>();
//        responseData.setResult(interestRateCurveServiceimpl.findAll(page,5));
//        generalResponse.setData(responseData);
//        return generalResponse;
//    }
//
//    //查询贴现因子表中所有信息
//    @GetMapping("/queryFactor")
//    public GeneralResponse queryFactor(Integer page, HttpServletResponse response){
//
//        //异步请求使用
//        response.setHeader("Access-Control-Allow-Origin","*");
//        if(page==null || page<=0){
//            page = 0;
//        }else{
//            page -= 1;
//        }
//        //return  factorCurveServiceImpl.findAll(page,5);
//        GeneralResponse generalResponse = new GeneralResponse();
//        ResponseData<Page<FactorDTO>> responseData = new ResponseData<>();
//        responseData.setResult(factorCurveServiceImpl.findAll(page,5));
//        generalResponse.setData(responseData);
//        return generalResponse;
//    }




//    //根据userid删除该用户名下所有的零息利率曲线
//    @DeleteMapping("/delInterestRateByUserId/{userId}")
//    public String delInterestRateByUserId(@PathVariable String userId){
//        if(findDataService.findInterestRateData(userId).size()!=0) {
//
//
//            interestRateCurveServiceimpl.deleteInterestRateByUserId(userId);
//            return "yes";
//        }else{
//            return "empty list";
//        }
//            }
////根据userid删除该用户名下所有的贴现因子曲线
//    @DeleteMapping("/delFactorByUserId/{userId}")
//    public String delFactorByUserId(@PathVariable String userId){
//        if (findDataService.findFactorData(userId).size()!=0){
//            factorCurveServiceImpl.deleteFactorByUserId(userId);
//            return "yes";
//        }else{
//            return "empty list";
//        }
//
//    }
    //根据curveName删除某用户名下对应的零息利率曲线






