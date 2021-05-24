package com.sumscope.fe.curvecustomizing.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class InterestRateAndFactorModelTo {
    private LocalDate date;
    private float InterestRateOrFactor;
}
