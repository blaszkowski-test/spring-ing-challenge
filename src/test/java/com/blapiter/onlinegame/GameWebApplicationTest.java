package com.blapiter.onlinegame;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.hamcrest.Matchers.containsString;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;

import com.blapiter.Utilities;

@SpringBootTest
@AutoConfigureMockMvc
public class GameWebApplicationTest {

	private final static String gameUrl = "/onlinegame/calculate";

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void checkGameCalculateSuccess() throws Exception {

		Map<String, String> filesMap = Map.of(
				"gameRequest1.json",
				"gameResponse1.json");

		for (Map.Entry<String, String> files : filesMap.entrySet()) {
			this.mockMvc.perform(post(gameUrl)
					.contentType(MediaType.APPLICATION_JSON)
					.content(Utilities.getJsonStringWithoutSpacesFromFile(files.getKey())))
					.andDo(print())
					.andExpect(status().isOk())
					.andExpect(content()
							.string(containsString(Utilities.getJsonStringWithoutSpacesFromFile(files.getValue()))));
		}
	}

	@Test
	public void checkGameCalculateValidationError() throws Exception {
		this.mockMvc.perform(post(gameUrl)
				.contentType(MediaType.APPLICATION_JSON)
				.content(Utilities.getJsonStringWithoutSpacesFromFile("gameRequestError1.json")))
				.andExpect(status().isBadRequest());
	}
}