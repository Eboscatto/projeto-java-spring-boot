package com.eboscatto.projetoJava;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProjetoJavaApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void deveCriarPost() throws Exception {
		mockMvc.perform(post("/api/posts")
						.contentType("application/json")
						.content("{\"userId\":1,\"title\":\"Teste\",\"body\":\"Corpo\"}"))
				.andExpect(status().isOk());
	}
}