package com.blapiter.transactions;

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
public class TransactionsWebApplicationTest {

	private final static String transactionUrl = "/transactions/report";

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void checkTransactionsReportSuccess() throws Exception {

		Map<String, String> filesMap = Map.of(
				"transactionRequest1.json",
				"transactionResponse1.json");

		for (Map.Entry<String, String> files : filesMap.entrySet()) {
			this.mockMvc.perform(post(transactionUrl)
					.contentType(MediaType.APPLICATION_JSON)
					.content(Utilities.getJsonStringWithoutSpacesFromFile(files.getKey())))
					.andDo(print())
					.andExpect(status().isOk())
					.andExpect(content()
							.string(containsString(Utilities.getJsonStringWithoutSpacesFromFile(files.getValue()))));
		}
	}

	@Test
	public void checkTransactionsReportValidationError() throws Exception {
		this.mockMvc.perform(post(transactionUrl)
				.contentType(MediaType.APPLICATION_JSON)
				.content(Utilities.getJsonStringWithoutSpacesFromFile("transactionRequestError1.json")))
				.andExpect(status().isBadRequest());
	}
}