package com.blapiter.onlinegame;

import java.util.List;
import java.util.ArrayList;

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
@RequestMapping("/onlinegame")
public class GameController {

	@Autowired
	@Qualifier("GameServiceImpl")
	protected GameService gameService;

	@Operation(summary = "Calculate order")
	@PostMapping(value = "/calculate", consumes = "application/json", produces = "application/json")
	public List<ArrayList<Clan>> calculate(@RequestBody @Valid Players players,
			BindingResult bindingResult) {
		return gameService.getPrioritizeClans(players);
	}
}