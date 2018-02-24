package com.ticketmgmt.services;

import com.ticketmgmt.domain.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;


/**
 * Service for User mgmt features.
 *
 * @author vision8
 */
public interface UserService {
	
	Flux<User> getAllUsers();
	
	Mono<User> getUser(Integer userid);
	
	Mono<Void> createUser(Integer userid, String username);
	
	Mono<Void> updateUser(Integer userid, String username);
	
	Mono<Void> deleteUser(Integer userid);
	
}
