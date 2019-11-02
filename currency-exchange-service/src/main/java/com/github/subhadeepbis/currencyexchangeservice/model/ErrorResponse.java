package com.github.subhadeepbis.currencyexchangeservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class ErrorResponse {
    private Date timeStampDate;
    private String Message;
    private String details;
}
