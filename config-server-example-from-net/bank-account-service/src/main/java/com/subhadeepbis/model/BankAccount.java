package com.subhadeepbis.model;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankAccount {

	@Getter
	@Setter
	private Double accountBalance;

	@Getter
	@Setter
	private String accountId;
}
