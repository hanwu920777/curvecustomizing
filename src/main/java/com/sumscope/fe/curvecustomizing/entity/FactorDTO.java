package com.sumscope.fe.curvecustomizing.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;

@Data
    @Entity
    @Component
    @Table(name="factorTable")
    public class FactorDTO implements Serializable {
        private static final long serialVersionUID=1278569L;

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Integer id;

        @Column
        private String userId;

        @Column
        private String interestRateAndFactorList;

        @Column
        private String curveName;

        @Column
        private String curveModel;

        @Column
        private String curveDate;

        @Column
        private String insertType;


    }

