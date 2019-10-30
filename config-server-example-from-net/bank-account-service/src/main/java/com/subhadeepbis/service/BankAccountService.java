package com.subhadeepbis.service;

import com.subhadeepbis.configuration.Configuration;
import com.subhadeepbis.model.BankAccount;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class BankAccountService {

		private Configuration config;
		private List<BankAccount> bankAccounts;

		@Autowired
		public BankAccountService(Configuration config) {
			this.config = config;
			bankAccounts = new ArrayList<>(Arrays.asList(
					new BankAccount(568.50, "100"),
					new BankAccount(1586.45, "101"))
			);
		}
		
		public void createBankAccount(BankAccount account) {
			if(account.getAccountBalance().doubleValue() >= config.getMinBalance() &&
					account.getAccountBalance().doubleValue() <= config.getMaxBalance()) {
				log.info("Account Balance [{}] is greater than lower bound [{}] and less than upper bound [{}]",
						account.getAccountBalance(), config.getMinBalance(), config.getMaxBalance());
				bankAccounts.add(account);
			} else {
				log.info("Account Balance [{}] is outside of lower bound [{}] upper bound [{}]",
						account.getAccountBalance(), config.getMinBalance(), config.getMaxBalance());
			}
		}

		public BankAccount retrieveBankAccount(String id) {
			log.info("Searching Bank Account {}", id);
			for(BankAccount account : bankAccounts) {
				if(account.getAccountId().equals(id)) {
					return account;
				}
			}
			return null;
		}
}
