package com.github.subhadeepbis.currencyexchangeservice.exception;

import lombok.AllArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Setter
@AllArgsConstructor
public class ErrorResponse {
    private Date timeStampDate;
    private String Message;
    private String details;
}
