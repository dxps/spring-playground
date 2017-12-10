package com.packtpub.yummy;


import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


/**
 * @author vision8
 */
@RestController
@RequestMapping
public class DemoController {
	
	@RequestMapping
	public LocalDateTime sayTheTime() {
		
		LocalDateTime response = LocalDateTime.now();
		System.out.println(">>> sayTheTime > Now is " + response.toString());
		return response;
		
	}
	
	
	@RequestMapping(path = "many")
	public String sayTheTimeMany(@RequestParam String name, @RequestParam(defaultValue = "7") int repetitions) {
		
		return IntStream
				.rangeClosed(1,repetitions)
				.mapToObj(i -> i + ". " + name + "! Now it is " + LocalDateTime.now())
				.collect(Collectors.joining("\n"));
		
	}
	
	
	@RequestMapping(path = "manyParams")
	public String sayTheTimeManyParams(Params params) {
		
		return IntStream
				.rangeClosed(1,params.getRepetitions())
				.mapToObj(i -> i + ". " + params.getName() + "! Now it is " + LocalDateTime.now())
				.collect(Collectors.joining("\n"));
		
	}
	
	
	@RequestMapping(path = "many/{name}/{repetitions}")
	public String sayTheTimeManyParamsPath(Params params) {
		
		return IntStream
				.rangeClosed(1,params.getRepetitions())
				.mapToObj(i -> i + ". " + params.getName() + "! Now it is " + LocalDateTime.now())
				.collect(Collectors.joining("\n"));
		
	}
	
	
	@RequestMapping(path = "many2/{name}/{repetitions}")
	public String sayTheTimeManyPatchExplicit(@PathVariable String name, @PathVariable int repetitions) {
		
		return IntStream
				.rangeClosed(1,repetitions)
				.mapToObj(i -> i + ". " + name + "! Now it is " + LocalDateTime.now())
				.collect(Collectors.joining("\n"));
		
	}
	
	
	@PostMapping(path = "manyParams")
	public String sayTheTimeManyParamsPost(@RequestBody Params params) {
		
		return IntStream
				.rangeClosed(1,params.getRepetitions())
				.mapToObj(i -> i + ". " + params.getName() + "! Now it is " + LocalDateTime.now())
				.collect(Collectors.joining("\n"));
		
	}
	
	
	// Using this class instead of listing the params in sayTheTimeManyParams() method.
	static class Params {
		
		String name;
		int repetitions = 7;
		
		public Params() {
			// needed also
		}
		
		public Params(String name, int repetitions) {
			this.name = name;
			this.repetitions = repetitions;
		}
		
		public String getName() {
			return name;
		}
		
		public void setName(String name) {
			this.name = name;
		}
		
		public int getRepetitions() {
			return repetitions;
		}
		
		public void setRepetitions(int repetitions) {
			this.repetitions = repetitions;
		}
		
	}
	
	
}
