package com.blapiter.transactions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@Validated
@RequestMapping("/transactions")
public class TransactionController {

	@Autowired
	@Qualifier("TransactionsImpl")
	protected TransactionService transactionService;

	@Operation(summary = "Execute report")
	@PostMapping(value = "/report", consumes = "application/json", produces = "application/json")
	public List<Account> report(@RequestBody ArrayList<@Valid Transaction> transactios,
			BindingResult bindingResult) {
		return transactionService.getSortedAccounts(transactios);
	}
}