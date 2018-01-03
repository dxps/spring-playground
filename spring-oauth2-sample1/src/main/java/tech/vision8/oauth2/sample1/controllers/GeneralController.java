package tech.vision8.oauth2.sample1.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author vision8
 */
@RestController
public class GeneralController {
	
	@RequestMapping("/")
	public String home() {
		return "Hello World!";
	}
	
}
