package com.packtpub.ticketmgmt.reactive.repo;

import com.packtpub.ticketmgmt.reactive.domain.User;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.Map;


/**
 * Implementation of user repository.
 *
 * @author vision8
 */
@Repository
public class UserRepositoryImpl implements UserRepository {
	
	private Map<Integer, User> users;
	
	
	public UserRepositoryImpl() {
		
		this.users = Map.of(
				1, new User(1, "David"),
				2, new User(1, "John"),
				3, new User(1, "Kevin")
		);
		
	}
	
	
	@Override
	public Flux<User> getAllUsers() {
		
		return Flux.fromIterable(this.users.values());
		
	}
	
	
}
