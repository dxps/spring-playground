package com.ticketmgmt;


import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


/**
 * MockMVC based User related tests.
 *
 * @author vision8
 */
//@SpringBootTest
@WebMvcTest
@RunWith(SpringRunner.class)
public class UserMockMVCTests {
	
	@Autowired
	private WebApplicationContext webappCtx;
	
	private MockMvc mockMvc;
	
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webappCtx).build();
	}
	
	
	@Test
	public void testBasicMVC() throws Exception {
		
		MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.get("/"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("result", Matchers.is("Aloha")))
				.andReturn();
		
		// skipped for now as in Java 9 (9.0.4), the error is:
		// cannot access javax.servlet.http.HttpServletResponse
		// System.out.println("[testBasicMVC] response="
		// 		+ mvcResult.getResponse().getContentAsString());
		
	}
	
	
	@Test
	public void testSingleUser() throws Exception {
		
		MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.get("/users/1"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("username", Matchers.is("David")))
				.andReturn();
		
		// skipped for now as in Java 9 (9.0.4), the error is:
		// cannot access javax.servlet.http.HttpServletResponse
		// System.out.println("[testSingleUser] response="
		// 		+ mvcResult.getResponse().getContentAsString());
		
	}
	
	
}
