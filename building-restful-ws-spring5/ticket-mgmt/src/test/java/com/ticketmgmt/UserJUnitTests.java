package com.ticketmgmt;

import com.ticketmgmt.domain.User;
import com.ticketmgmt.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Collectors;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserJUnitTests {

	@Autowired
	UserService userService;
	
	
	@Test
	public void testAllUsers() {
		
		long numberOfUsers = userService.getAllUsers()
				.collect(Collectors.counting())
				.block();
		// initially, there are 3 entries in the repository
		assertEquals(numberOfUsers, 3);
		
	}
	
	
	@Test
	public void testSingleUser() {
		
		User user = userService.getUser(1).block();
		assertTrue(user.getUsername().contains("David"));
		
	}

	
}
