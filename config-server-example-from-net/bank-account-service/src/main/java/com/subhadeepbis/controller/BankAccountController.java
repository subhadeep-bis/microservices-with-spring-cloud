package com.subhadeepbis.controller;

import com.subhadeepbis.model.BankAccount;
import com.subhadeepbis.service.BankAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@Slf4j
public class BankAccountController {
	
	private BankAccountService bankAccountService;
	
	@Autowired
	public BankAccountController(BankAccountService bankAccountService) {
		this.bankAccountService = bankAccountService;
	}

	/**
	 * createBankAccount method is used to create new Bank Accounts
	 * @author Subhadeep Biswas
	 * @param bankAccount - new Bank Account sent as a requestBody to the api
	 * @return ResponseEntity - returns the uri of the created resource */
	@RequestMapping(value = "/bank-account", method = RequestMethod.POST)
	public ResponseEntity<?> createBankAccount(@RequestBody BankAccount bankAccount, HttpServletRequest request)
			throws URISyntaxException {
		bankAccountService.createBankAccount(bankAccount);
		log.info("created bank account {}", bankAccount);
		URI uri = new URI(request.getRequestURL() + "bank-account/" + bankAccount.getAccountId());
		return ResponseEntity.created(uri).build();
	}

	/**
	 * getBankAccount searches if the account already exists
	 * @author  Subhadeep Biswas
	 * @param accountId
	 * @return ResponseEntity - returns the found resource
	 */
	@RequestMapping(value="/bank-account/{accountId}", method = RequestMethod.GET)
	public ResponseEntity<BankAccount> getBankAccount(@PathVariable("accountId") String accountId) {
		BankAccount bankAccount = bankAccountService.retrieveBankAccount(accountId);
		if(bankAccount!=null) {
			log.info("Bank Account found {}", bankAccount);
			return new ResponseEntity<>(bankAccount, HttpStatus.FOUND);
		} else {
			log.info("Bank Account not found");
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
}
