package com.packtpub.yummy;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;


/**
 * @author vision8
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DemoControllerRestTemplateTest {
	
	@Autowired
	TestRestTemplate restTemplate;
	
	
	@Test
	public void sayTheTime() throws Exception {
		
		String result1 = restTemplate.getForObject("/", String.class);
		String result2 = restTemplate.getForObject("/", String.class);
		
		assertNotEquals(result1, result2);
		
	}
	
	
	@Test
	public void sayTheTimeMany() throws Exception {
		
		String result = restTemplate.getForObject(
				"/many?name=Sansa&repetitions=3",
				String.class);
		
		assertThat(result, Matchers.containsString("Sansa"));
		assertThat(result, Matchers.containsString("3. "));
		assertThat(result, Matchers.not(Matchers.containsString("4. ")));
		
	}
	
	
	@Test
	public void sayTheTimeManyParams() throws Exception {
		
		String result = restTemplate.getForObject(
				"/manyParams?name=Sansa&repetitions=3",
				String.class);
		
		assertThat(result, Matchers.containsString("Sansa"));
		assertThat(result, Matchers.containsString("3. "));
		assertThat(result, Matchers.not(Matchers.containsString("4. ")));
		
	}
	
	
	@Test
	public void sayTheTimeManyParamsPath() throws Exception {
		
		// The URI params (those {name} and {repetitions}) included in this case will be ignored. See the warning below and in the text output.
		// "org.springframework.validation.DataBinder: Skipping URI variable 'name' since the request contains a bind value with the same name."
		
		String result = restTemplate.getForObject(
				"/many/{name}/{repetitions}",
				String.class,
				"John", 5);
		
		assertThat(result, Matchers.containsString("John"));
		assertThat(result, Matchers.containsString("5. "));
		assertThat(result, Matchers.not(Matchers.containsString("6. ")));
		
	}
	
	
	@Test
	public void sayTheTimeManyParamsPathWithQueryParams() throws Exception {
		
		// The URI (path) params ({name} and {repetitions}) included in this case will be ignored. See the warning below and in the text output.
		// "org.springframework.validation.DataBinder: Skipping URI variable 'name' since the request contains a bind value with the same name."
		
		String result = restTemplate.getForObject(
				"/many/John/5?name=Sansa&repetitions=3",
				String.class);
		
		assertThat(result, Matchers.containsString("Sansa"));
		assertThat(result, Matchers.containsString("3. "));
		assertThat(result, Matchers.not(Matchers.containsString("4. ")));
		
	}
	
	
	@Autowired
	ObjectMapper mapper;
	
	
	@Test
	public void sayTheTimeManyParamsPost() throws Exception {
		
		String result = restTemplate.postForObject(
				"/manyParams",
				new DemoController.Params("John", 5),
				String.class);
		
		assertThat(result, Matchers.containsString("John"));
		assertThat(result, Matchers.containsString("5. "));
		assertThat(result, Matchers.not(Matchers.containsString("6. ")));
		
	}
	
	
}
