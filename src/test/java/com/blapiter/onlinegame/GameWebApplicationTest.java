package com.blapiter.onlinegame;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.hamcrest.Matchers.containsString;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

@SpringBootTest
@AutoConfigureMockMvc
public class GameWebApplicationTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void checkTransactionsSuccess() throws Exception {

		Map<String, String> filesMap = Map.of(
				"gameRequest1.json",
				"gameResponse1.json");

		for (Map.Entry<String, String> files : filesMap.entrySet()) {
			this.mockMvc.perform(post("/onlinegame/calculate")
					.contentType(MediaType.APPLICATION_JSON)
					.content(readFromFileToString(files.getKey())))
					.andDo(print())
					.andExpect(status().isOk())
					.andExpect(content().string(containsString(readFromFileToString(files.getValue()))));
		}
	}

	// @Test
	// public void checkServiceTasksValidationError() throws Exception {
	// 	this.mockMvc.perform(post("/atms/calculateOrder")
	// 			.contentType(MediaType.APPLICATION_JSON)
	// 			.content(readFromFileToString("atmRequestError1.json")))
	// 			.andExpect(status().isBadRequest());
	// }

	public static String readFromFileToString(String filePath) throws IOException {
		return new String(Files.readAllBytes((new ClassPathResource(filePath).getFile()).toPath()))
				.replaceAll("\\s+", "");
	}

}