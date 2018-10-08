package com.ticketmgmt.api.reactive;

import com.ticketmgmt.domain.User;
import com.ticketmgmt.repo.UserRepository;
import com.ticketmgmt.services.UserService;
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
	
	//private final UserRepository userRepository;
	
	private final UserService userService;


//	public UserHandler(UserRepository userRepository) {
//		this.userRepository = userRepository;
//	}
	
	public UserHandler(UserService userService) {
		this.userService = userService;
	}
	
	
	public Mono<ServerResponse> getAllUsers(ServerRequest request) {
		
		return ServerResponse
				.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(this.userService.getAllUsers(), User.class);
		
	}
	
	
	public Mono<ServerResponse> getUser(ServerRequest request) {
		
		int id = Integer.valueOf(request.pathVariable("id"));
		Mono<User> userMono = this.userService.getUser(id);
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
		
		User user = request.bodyToMono(User.class).block();
		return ServerResponse.ok()
				.build(this.userService.createUser(user.getUserid(), user.getUsername()));
		
	}
	
	
	public Mono<ServerResponse> updateUser(ServerRequest request) {
		
		Integer id = Integer.valueOf(request.pathVariable("id"));
		User user = request.bodyToMono(User.class).block();
		return ServerResponse.ok()
				.build(this.userService.updateUser(id, user.getUsername()));
		
	}
	
	
	public Mono<ServerResponse> deleteUser(ServerRequest request) {
		
		int id = Integer.valueOf(request.pathVariable("id"));
		return ServerResponse.ok()
				.build(this.userService.deleteUser(id));
		
	}
	
	
}
