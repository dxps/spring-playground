package com.packtpub.yummy;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.Assert.assertNotEquals;


/**
 * @author vision8
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DemoControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	
	@Test
	public void sayTheTime() throws Exception {
		
		String result1 = mockMvc.perform(MockMvcRequestBuilders.get("/"))
				.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
				.andReturn().getResponse().getContentAsString();
		
		String result2 = mockMvc.perform(MockMvcRequestBuilders.get("/"))
				.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
				.andReturn().getResponse().getContentAsString();
		
		assertNotEquals(result1, result2);
		
	}
	
	
	@Test
	public void sayTheTimeMany() throws Exception {
		
		mockMvc.perform(
				MockMvcRequestBuilders.get("/many")
						.param("name", "Sansa")
						.param("repetitions", "3")
		)
				.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
				.andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("Sansa")))
				.andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("3. ")))
				.andExpect(MockMvcResultMatchers.content().string(Matchers.not(Matchers.containsString("4 . "))));
		
	}
	
	
	@Test
	public void sayTheTimeManyParams() throws Exception {
		
		mockMvc.perform(
				MockMvcRequestBuilders.get("/manyParams")
						.param("name", "Sansa")
						.param("repetitions", "3")
		)
				.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
				.andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("Sansa")))
				.andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("3. ")))
				.andExpect(MockMvcResultMatchers.content().string(Matchers.not(Matchers.containsString("4 . "))));
		
	}
	
	
	@Test
	public void sayTheTimeManyParamsPath() throws Exception {
		
		// The URI params (those {name} and {repetitions}) included in this case will be ignored. See the warning below and in the text output.
		// "org.springframework.validation.DataBinder: Skipping URI variable 'name' since the request contains a bind value with the same name."
		
		mockMvc.perform(
				MockMvcRequestBuilders.get("/many/{name}/{repetitions}", "John", "5")
		)
				.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
				.andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("John")))
				.andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("5. ")))
				.andExpect(MockMvcResultMatchers.content().string(Matchers.not(Matchers.containsString("6 . "))));
		
	}
	
	
	@Test
	public void sayTheTimeManyParamsPathWithQueryParams() throws Exception {
		
		// The URI (path) params ({name} and {repetitions}) included in this case will be ignored. See the warning below and in the text output.
		// "org.springframework.validation.DataBinder: Skipping URI variable 'name' since the request contains a bind value with the same name."
		
		mockMvc.perform(
				MockMvcRequestBuilders.get("/many/{name}/{repetitions}", "John", "5")
						.param("name", "Sansa")
						.param("repetitions", "3")
		)
				.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
				.andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("Sansa")))
				.andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("3. ")))
				.andExpect(MockMvcResultMatchers.content().string(Matchers.not(Matchers.containsString("4 . "))));
		
	}
	
	
	@Autowired
	ObjectMapper mapper;
	
	
	@Test
	public void sayTheTimeManyParamsPost() throws Exception {
		
		mockMvc.perform(
				MockMvcRequestBuilders.post("/manyParams")
//						.param("name", "Sansa")
//						.param("repetitions", "3")
						.content(mapper.writeValueAsBytes(new DemoController.Params("John", 5)))
						.contentType(MediaType.APPLICATION_JSON_UTF8)
		)
				.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
				.andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("John")))
				.andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("5. ")))
				.andExpect(MockMvcResultMatchers.content().string(Matchers.not(Matchers.containsString("6. "))));
		
	}
	
	
}
