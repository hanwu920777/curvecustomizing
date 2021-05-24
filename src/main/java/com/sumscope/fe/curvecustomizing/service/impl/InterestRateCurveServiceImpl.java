package com.sumscope.fe.curvecustomizing.service.impl;

import com.sumscope.fe.curvecustomizing.dao.InterestRateDAO;
import com.sumscope.fe.curvecustomizing.entity.FactorDTO;
import com.sumscope.fe.curvecustomizing.entity.InterestRateDTO;

import com.sumscope.fe.curvecustomizing.service.InterestRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterestRateCurveServiceImpl implements InterestRateService {


    @Autowired
    private InterestRateDAO interestRateDAO;
    @Override
    public void deleteInterestRateByUserId(String userId){

        interestRateDAO.deleteInterestRateByUserId(userId);
    }

    @Override
    public void editInterestRateCurve(InterestRateDTO interestRateDTO){

        interestRateDAO.save(interestRateDTO);
    }
    @Override
    public InterestRateDTO insertCurve(InterestRateDTO interestRateDTO) {
        return interestRateDAO.save(interestRateDTO);

    }
    @Override
    public List<InterestRateDTO> findCurByUserId(String userId){

        return  interestRateDAO.findByuserId(userId);
    }

    @Override
    public void deleteAll() {
        interestRateDAO.flush();
        interestRateDAO.deleteAll();

    }
    @Override
    public void deleteByCurveId(int id) {
         interestRateDAO.deleteById(Math.toIntExact(id));

    }
    @Override
    public List<InterestRateDTO> findAll() {

        return interestRateDAO.findAll();
    }

    @Override
    public InterestRateDTO findCurById(int id) {
        return interestRateDAO.findCurveById(Math.toIntExact(id));

    }



    @Override
    public Page<InterestRateDTO> findAll(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page,pageSize);
        return interestRateDAO.findAll(pageable);
    }
}
