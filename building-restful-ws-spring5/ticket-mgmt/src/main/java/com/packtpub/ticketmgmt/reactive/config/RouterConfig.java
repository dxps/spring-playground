package com.packtpub.ticketmgmt.reactive.config;

import com.packtpub.ticketmgmt.reactive.api.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * @author vision8
 */
@Configuration
public class RouterConfig {
	
	
	@Bean
	public RouterFunction<ServerResponse> route(UserHandler handler) {
		
		return RouterFunctions
				.route(
						RequestPredicates.GET("/users")
								.and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
						handler::getAllUsers
				);
		
	}
	
	
}
