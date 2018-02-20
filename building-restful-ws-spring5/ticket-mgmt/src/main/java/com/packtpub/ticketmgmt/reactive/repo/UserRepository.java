package com.packtpub.ticketmgmt.reactive.repo;

import com.packtpub.ticketmgmt.reactive.domain.User;
import reactor.core.publisher.Flux;

/**
 * Repository of users.
 *
 * @author vision8
 */
public interface UserRepository {
	
	Flux<User> getAllUsers();
	
}
