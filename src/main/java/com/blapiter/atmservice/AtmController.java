package com.blapiter.atmservice;

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
@RequestMapping("/atms")
public class AtmController {

	@Autowired
	@Qualifier("AtmServiceImpl")
	protected AtmService atmService;

	@Operation(summary = "Calculates ATMs order for service team")
	@PostMapping(value = "/calculateOrder", consumes = "application/json", produces = "application/json")
	public Order calculate(@RequestBody @Valid ServiceTasks ServiceTasks,
			BindingResult bindingResult) {
		return atmService.getSortedOrders(ServiceTasks);
	}
}