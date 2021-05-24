package com.sumscope.fe.curvecustomizing.entity;



import lombok.*;
import org.springframework.stereotype.Component;
import javax.persistence.*;
import java.io.Serializable;


@Data
@Entity
@Component
@Table(name="interestTable")
public class InterestRateDTO implements Serializable {
        private static final long serialVersionUID=12789L;

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Integer id;

//@OneToMany(fetch=FetchType.LAZY,targetEntity=InterestRateDTO.class,cascade=CascadeType.ALL,orphanRemoval=true)
//        @JoinColumn(name="interestRateId")


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

