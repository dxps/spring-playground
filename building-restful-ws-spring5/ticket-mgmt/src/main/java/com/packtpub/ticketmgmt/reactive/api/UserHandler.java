package com.packtpub.ticketmgmt.reactive.api;

import com.packtpub.ticketmgmt.reactive.domain.User;
import com.packtpub.ticketmgmt.reactive.repo.UserRepository;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
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
	
	
	public Mono<ServerResponse> getUser(ServerRequest request) {
		
		int id = Integer.valueOf(request.pathVariable("id"));
		Mono<User> userMono = this.userRepository.getUser(id);
		return userMono
				.flatMap(
						user -> ServerResponse.ok()
								.contentType(MediaType.APPLICATION_JSON)
								.body(BodyInserters.fromObject(user))
				)
				.switchIfEmpty(
						ServerResponse.notFound().build()
				);
		
	}
	
	
	public Mono<ServerResponse> createUser(ServerRequest request) {
		
		Mono<User> user = request.bodyToMono(User.class);
		return ServerResponse.ok().build(this.userRepository.saveUser(user));
		
	}
	
	
	public Mono<ServerResponse> updateUser(ServerRequest request) {
		
		Integer id = Integer.valueOf(request.pathVariable("id"));
		Mono<User> user = request.bodyToMono(User.class);
		return ServerResponse.ok().build(this.userRepository.updateUser(id, user));
		
	}
	
	
	public Mono<ServerResponse> deleteUser(ServerRequest request) {
		
		int id = Integer.valueOf(request.pathVariable("id"));
		return ServerResponse.ok().build(this.userRepository.deleteUser(id));
		
	}
	
	
}
