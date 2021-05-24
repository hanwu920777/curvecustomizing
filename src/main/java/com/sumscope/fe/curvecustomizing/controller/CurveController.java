package com.sumscope.fe.curvecustomizing.controller;

import com.alibaba.fastjson.JSON;
import com.sumscope.fe.curvecustomizing.entity.*;

import com.sumscope.fe.curvecustomizing.service.FindData;
import com.sumscope.fe.curvecustomizing.service.impl.FactorCurveServiceImpl;
import com.sumscope.fe.curvecustomizing.service.impl.InterestRateCurveServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/irs")
public class CurveController {
    private InterestRateCurveServiceImpl interestRateCurveServiceimpl;
    @Autowired
    private FindData findData;
    @Autowired
    private InterestRateDTO interestRateDTO;
    @Autowired
    private FactorDTO factorDTO;
    @Autowired
    private FactorCurveServiceImpl factorCurveServiceImpl;

    @Autowired
    public CurveController(InterestRateCurveServiceImpl interestRateCurveServiceimpl) {
        this.interestRateCurveServiceimpl = interestRateCurveServiceimpl;
    }



    //插入一条新的零息利率曲线
    @PostMapping("/insertInterestRate")
    public InterestRateDTO insertInterestRate(@RequestBody CurveDataModel curveDataModel) throws Exception {


        boolean flag = true;
        for (int i = 0; i < findData.findInterestRateData(curveDataModel.getUserId()).size(); i++) {
            if (curveDataModel.getCurveName().equals(findData.findInterestRateData(curveDataModel.getUserId()).get(i).getCurveName())) {
                flag = false;
                break;
            }
        }
                if (flag == false) {
                    throw new  Exception("Repeated curveName");
                }else{
                    interestRateDTO =new InterestRateDTO();
                    BeanUtils.copyProperties(curveDataModel, interestRateDTO);
                    interestRateDTO.setInterestRateAndFactorList(JSON.toJSONString(curveDataModel.getInterestRateAndFactorList()));
                    return interestRateCurveServiceimpl.insertCurve(interestRateDTO);

                }
    }
    //插入一条新的贴现因子曲线
    @PostMapping("/insertFactor")
    public FactorDTO insertFactor(@RequestBody CurveDataModel curveDataModel) throws Exception {


        boolean flag = true;
        for (int i = 0; i < findData.findFactorData(curveDataModel.getUserId()).size(); i++) {
            if (curveDataModel.getCurveName().equals(findData.findFactorData(curveDataModel.getUserId()).get(i).getCurveName())) {
                flag = false;
                break;
            }
        }
        if (flag == false) {
            throw new  Exception("Repeated curveName");
        }else{
            for(int i=0;i<curveDataModel.getInterestRateAndFactorList().size();i++){
                if(curveDataModel.getInterestRateAndFactorList().get(i).getInterestRateOrFactor()<0||curveDataModel.getInterestRateAndFactorList().get(i).getInterestRateOrFactor()>1){
                    throw new Exception("Wrong Typing");
                }
            }

            factorDTO =new FactorDTO();
            BeanUtils.copyProperties(curveDataModel, factorDTO);
            factorDTO.setInterestRateAndFactorList(JSON.toJSONString(curveDataModel.getInterestRateAndFactorList()));
            return factorCurveServiceImpl.insertCurve(factorDTO);

        }
    }
    //查询零息利率表中所有信息
    @GetMapping("/queryinterestRate")
    public Page<InterestRateDTO> queryinterestRate(Integer page, HttpServletResponse response){

        //异步请求使用
        response.setHeader("Access-Control-Allow-Origin","*");
        if(page==null || page<=0){
            page = 0;
        }else{
            page -= 1;
        }
        return  interestRateCurveServiceimpl.findAll(page,5);
    }

    //查询贴现因子表中所有信息
    @GetMapping("/queryFactor")
    public Page<FactorDTO> queryFactor(Integer page, HttpServletResponse response){

        //异步请求使用
        response.setHeader("Access-Control-Allow-Origin","*");
        if(page==null || page<=0){
            page = 0;
        }else{
            page -= 1;
        }
        return  factorCurveServiceImpl.findAll(page,5);
    }

    //根据userid列出该用户名下所有零息利率曲线名称
    @GetMapping("/findAllInterestRateCurveName/{userId}")
    public List<String> findAllInterestRateCurveName(@PathVariable String userId){
        List<String> curveName=new ArrayList<>();
        for(int i=0;i<findData.findInterestRateData(userId).size();i++){
            String curveName1 = findData.findInterestRateData(userId).get(i).getCurveName();
            curveName.add(curveName1);
        }
    return curveName;

}

    //根据userid列出该用户名下所有贴现因子曲线名称
    @GetMapping("/findAllFactorCurveName/{userId}")
    public List<String> findAllFactorCurveName(@PathVariable String userId){
        List<String> curveName=new ArrayList<>();
        for(int i=0;i<findData.findFactorData(userId).size();i++){
            String curveName1 = findData.findFactorData(userId).get(i).getCurveName();
            curveName.add(curveName1);
        }
        return curveName;

    }
    //根据userid获取该用户名下所有零息利率曲线详细信息
    @GetMapping("/findAllInterestRate/{userId}")
    public List<CurveDataModelTo> findAllInterestRate(@PathVariable String userId){

        return findData.findInterestRateData(userId);

    }
    //根据userid获取该用户名下所有贴现因子曲线详细信息
    @GetMapping("/findAllFactor/{userId}")
    public List<CurveDataModelTo> findAllFactor(@PathVariable String userId){

        return findData.findFactorData(userId);

    }

    //根据曲线名称获取某用户对应的零息利率曲线详细信息
    @GetMapping("/findInterestRateDetails/{userId}/{curveName}")
    public CurveDataModelTo findInterestRateDetails(@PathVariable String userId,@PathVariable String curveName){

         boolean flag=false;
         int i=0;
         for( i=0;i< findData.findInterestRateData(userId).size();i++) {
             if (curveName.equals(findData.findInterestRateData(userId).get(i).getCurveName())) {
                 flag = true;
                 break;
             }
         }
             if(flag==true){
                 return findData.findInterestRateData(userId).get(i);
             }else{
                 return null;
             }
         }
    //根据曲线名称获取某用户对应的贴现因子曲线的详细信息
    @GetMapping("/findFactorDetails/{userId}/{curveName}")
    public CurveDataModelTo findFactorDetails(@PathVariable String userId,@PathVariable String curveName){

        boolean flag=false;
        int i=0;
        for( i=0;i< findData.findFactorData(userId).size();i++) {
            if (curveName.equals(findData.findFactorData(userId).get(i).getCurveName())) {
                flag = true;
                break;
            }
        }
        if(flag==true){
            return findData.findFactorData(userId).get(i);
        }else{
            return null;
        }
    }

    //编辑某用户某条零息利率曲线
    @PutMapping("/editInterestRateCurve")
    public String editInterestCurve(@RequestBody CurveDataModel curveDataModel){
        interestRateDTO= new InterestRateDTO();

        boolean flag=false;
        int i=0;
        for (i = 0; i < findData.findInterestRateData(curveDataModel.getUserId()).size(); i++) {
            if (findData.findInterestRateData(curveDataModel.getUserId()).get(i).getCurveName().equals(curveDataModel.getCurveName())) {
                flag=true;
                break;
            }
        }
        if(flag==true){

            int id = findData.findInterestRateData(curveDataModel.getUserId()).get(i).getId();
            BeanUtils.copyProperties(curveDataModel,interestRateDTO);
            interestRateDTO.setInterestRateAndFactorList(JSON.toJSONString(curveDataModel.getInterestRateAndFactorList()));
            interestRateDTO.setId(id);
            interestRateCurveServiceimpl.editInterestRateCurve(interestRateDTO);
            return "yes";
        }else {
            return null;
        }
    }
    //编辑某用户某条贴现因子曲线
    @PutMapping("/editFactorCurve")
    public String editFactorCurve(@RequestBody CurveDataModel curveDataModel){
        factorDTO=new FactorDTO();

        boolean flag=false;
        int i=0;
        for (i = 0; i < findData.findFactorData(curveDataModel.getUserId()).size(); i++) {
            if (findData.findFactorData(curveDataModel.getUserId()).get(i).getCurveName().equals(curveDataModel.getCurveName())) {
                flag=true;
                break;
            }
        }
        if(flag==true){

            int id = findData.findFactorData(curveDataModel.getUserId()).get(i).getId();
            BeanUtils.copyProperties(curveDataModel,factorDTO);
            factorDTO.setInterestRateAndFactorList(JSON.toJSONString(curveDataModel.getInterestRateAndFactorList()));
            factorDTO.setId(id);
            factorCurveServiceImpl.editFactorCurve(factorDTO);
            return "yes";
        }else {
            return null;
        }
    }
    //根据userid删除该用户名下所有的零息利率曲线
    @DeleteMapping("/delInterestRateByUserId/{userId}")
    public String delInterestRateByUserId(@PathVariable String userId){
        if(findData.findInterestRateData(userId).size()!=0) {


            interestRateCurveServiceimpl.deleteInterestRateByUserId(userId);
            return "yes";
        }else{
            return "empty list";
        }
            }
//根据userid删除该用户名下所有的贴现因子曲线
    @DeleteMapping("/delFactorByUserId/{userId}")
    public String delFactorByUserId(@PathVariable String userId){
        if (findData.findFactorData(userId).size()!=0){
            factorCurveServiceImpl.deleteFactorByUserId(userId);
            return "yes";
        }else{
            return "empty list";
        }

    }
    //根据curveName删除某用户名下对应的零息利率曲线
    @DeleteMapping("/delInterestRateByCurveName/{userId}/{curveName}")
    public String delInterestRateByCurveName(@PathVariable String userId,@PathVariable String curveName){
        boolean flag=false;
        int i=0;
        for (i = 0; i < findData.findInterestRateData(userId).size(); i++) {
            if (findData.findInterestRateData(userId).get(i).getCurveName().equals(curveName)) {
                flag=true;
                break;
            }
        }
        if(flag==true){

            int id = findData.findInterestRateData(userId).get(i).getId();
            interestRateCurveServiceimpl.deleteByCurveId(id);
            return "yes";
        }else {
            return null;
        }
    }

    //根据curveName删除该用户名下对应的贴现因子曲线
    @DeleteMapping("/delFactorByCurveName/{userId}/{curveName}")
    public String delFactorByCurveName(@PathVariable String userId,@PathVariable String curveName){
        boolean flag=false;
        int i=0;
        for (i = 0; i < findData.findFactorData(userId).size(); i++) {
            if (findData.findFactorData(userId).get(i).getCurveName().equals(curveName)) {
                flag=true;
                break;
            }
        }
        if(flag==true){

            int id = findData.findFactorData(userId).get(i).getId();
            factorCurveServiceImpl.deleteByCurveId(id);
            return "yes";
        }else {
            return null;
        }
    }




}
