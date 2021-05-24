package com.sumscope.fe.curvecustomizing.dao;

import com.sumscope.fe.curvecustomizing.entity.FactorDTO;
import com.sumscope.fe.curvecustomizing.entity.InterestRateDTO;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Component
public interface InterestRateDAO extends JpaRepository<InterestRateDTO, Integer> {
    InterestRateDTO findCurveById(int id);
    @Query(value = "select * from interestTable where userId=?1")
    List<InterestRateDTO> findByuserId(String userId);

    @Modifying
    @Transactional
    @Query("delete * from interestTable where userId= ?1")
    void deleteInterestRateByUserId(String userId);
}
