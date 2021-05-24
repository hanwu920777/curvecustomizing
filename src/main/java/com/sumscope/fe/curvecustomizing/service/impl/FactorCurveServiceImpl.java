package com.sumscope.fe.curvecustomizing.service.impl;

import com.sumscope.fe.curvecustomizing.dao.FactorDAO;
import com.sumscope.fe.curvecustomizing.entity.FactorDTO;
import com.sumscope.fe.curvecustomizing.service.FactorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

    @Service
    public class FactorCurveServiceImpl implements FactorService {


        @Autowired
        private FactorDAO factorDAO;
        @Autowired
        private FactorDTO factorDTO;


        public FactorDTO insertCurve(FactorDTO factorDTO) {
            return factorDAO.save(factorDTO);

        }
        @Override
        public List<FactorDTO> findCurByUserId(String userId){

            return  factorDAO.findByuserId(userId);
        }
        @Override
        public void deleteFactorByUserId(String userId){

            factorDAO.deleteFactorByUserId(userId);
        }
        @Override
        public void editFactorCurve(FactorDTO factorDTO){

            factorDAO.save(factorDTO);
        }
        @Override
        public void deleteAll() {
            factorDAO.deleteAll();

        }
        @Override
        public void deleteByCurveId(int id) {
            factorDAO.deleteById(Math.toIntExact(id));

        }

        @Override
        public List<FactorDTO> findAll() {

            return factorDAO.findAll();
        }

        public FactorDTO findCurById(int id) {
            return factorDAO.findCurveById(id);

        }



        public Page<FactorDTO> findAll(int page, int pageSize) {
            Pageable pageable = PageRequest.of(page,pageSize);
            return factorDAO.findAll(pageable);
        }
}