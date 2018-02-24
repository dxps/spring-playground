package com.ticketmgmt.api.reactive;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;


/**
 * @author vision8
 */
@Configuration
public class RouterConfig {
	
	
	@Bean
	public RouterFunction<ServerResponse> route(UserHandler handler) {
		
		return RouterFunctions
				.route(
						GET("/users").and(accept(MediaType.ALL)),
						handler::getAllUsers
				)
				.andRoute(
						GET("/users/{id}").and(accept(MediaType.APPLICATION_JSON)),
						handler::getUser
				)
				.andRoute(
						POST("/users").and(accept(MediaType.APPLICATION_JSON)),
						handler::createUser
				)
				.andRoute(
						PUT("/users/{id}").and(accept(MediaType.APPLICATION_JSON)),
						handler::updateUser
				)
				.andRoute(
						DELETE("/users/{id}"),
						handler::deleteUser
				);
		
	}
	
	
}
