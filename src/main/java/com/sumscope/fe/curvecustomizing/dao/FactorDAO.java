package com.sumscope.fe.curvecustomizing.dao;

import com.sumscope.fe.curvecustomizing.entity.FactorDTO;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface FactorDAO extends JpaRepository<FactorDTO, Integer> {
    FactorDTO findCurveById(int id);


    @Query(value = "select * from factorTable where userId=?1")
    List<FactorDTO> findByuserId(String userId);

    @Modifying
    @Transactional
    @Query("delete * from factorTable where userId= ?1")
    void deleteFactorByUserId(String userId);


}
