package com.github.subhadeepbis.currencyexchangeservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@JsonIgnoreProperties(value = {"id", "port"}, allowGetters = true)
public class ExchangeValue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "currency_from")
    @Size(min = 3, max = 3, message = "from length should be of length 3")
    @NotEmpty(message = "from is a required field")
    private String from;

    @Column(name = "currency_to")
    @Size(min = 3, max = 3, message = "to length should be of length 3")
    @NotEmpty(message = "to is a required field")
    private String to;

    @NotNull(message = "conversionMultiple is a required field")
    private BigDecimal conversionMultiple;
    private Integer port;
}
