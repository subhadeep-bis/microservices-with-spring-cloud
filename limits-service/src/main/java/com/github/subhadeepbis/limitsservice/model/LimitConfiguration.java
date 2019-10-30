package com.github.subhadeepbis.limitsservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LimitConfiguration {
    private int maximum;
    private int minimum;
    private int secondMaximum;
    private int secondMinimum;
}
