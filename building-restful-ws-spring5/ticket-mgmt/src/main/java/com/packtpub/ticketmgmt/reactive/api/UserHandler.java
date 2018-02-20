package com.packtpub.ticketmgmt.reactive.api;

import com.packtpub.ticketmgmt.reactive.domain.User;
import com.packtpub.ticketmgmt.reactive.repo.UserRepository;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;


/**
 * Handler for user api requests.
 *
 * @author vision8
 */
@Component
public class UserHandler {
	
	private final UserRepository userRepository;
	
	
	public UserHandler(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	
	public Mono<ServerResponse> getAllUsers(ServerRequest request) {
		
		return ServerResponse
				.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(this.userRepository.getAllUsers(), User.class);
		
	}
	
	
}
