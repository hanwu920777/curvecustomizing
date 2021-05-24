package com.sumscope.fe.curvecustomizing.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;


@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class InterestRateAndFactorModel {
    private String date;
    private float InterestRateOrFactor;
}
