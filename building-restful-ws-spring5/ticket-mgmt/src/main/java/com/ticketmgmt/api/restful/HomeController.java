package com.ticketmgmt.api.restful;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 *
 * @author vision8
 */
@RestController
@RequestMapping("/")
public class HomeController {

	
	@ResponseBody
	@RequestMapping("")
	public Map<String, Object> test() {
		
		Map<String, Object> map = new LinkedHashMap<>();
		map.put("result", "Aloha");
		return map;
		
	}
	
	
}
