package com.ticketmgmt.api.restful;


import com.ticketmgmt.services.SecurityService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author vision8
 */
@RestController
@RequestMapping("/security")
public class SecurityController {
	
	private SecurityService securityService;
	

	public SecurityController(SecurityService securityService) {
		this.securityService = securityService;
	}
	
	
	@ResponseBody
	@RequestMapping("/generate/token")
	public Map<String, Object> generateToken(@RequestParam(value = "subject") String subject) {
		
		String token = securityService.createToken(subject, (15 * 1000 * 60));
		
		Map<String, Object> map = new LinkedHashMap<>();
		map.put("result", token);
		
		return map;
		
	}
	
	@ResponseBody
	@RequestMapping("/get/subject")
	public Map<String, Object> getSubject(@RequestParam(value = "token") String token) {
		
		String subject = securityService.getSubject(token);
		
		Map<String, Object> map = new LinkedHashMap<>();
		map.put("result", subject);
		
		return map;
		
	}
	
	
}
