package com.packtpub.ticketmgmt.reactive.repo;

import com.packtpub.ticketmgmt.reactive.domain.User;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
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
		
		this.users = new HashMap<>();
		this.users.putAll(Map.of(
				1, new User(1, "David"),
				2, new User(2, "John"),
				3, new User(3, "Kevin")
				)
		);
		
	}
	
	
	@Override
	public Flux<User> getAllUsers() {
		
		return Flux.fromIterable(this.users.values());
		
	}
	
	
	@Override
	public Mono<User> getUser(Integer id) {
		
		return Mono.justOrEmpty(this.users.get(id));
		
	}
	
	
	@Override
	public Mono<Void> saveUser(Mono<User> userMono) {
		
		return userMono
				.doOnNext(user -> {
							users.put(user.getUserid(), user);
							System.out.format(">>> Saved %s with id %d%n", user, user.getUserid());
						}
				).thenEmpty(Mono.empty());
		
	}
	
	
	@Override
	public Mono<Void> updateUser(Integer id, Mono<User> userMono) {
		
		return userMono
				.doOnNext(user -> {
							if (users.containsKey(id)) {
								user.setUserid(id);
								users.put(user.getUserid(), user);
								System.out.format(">>> Updated %s with id %d%n", user, user.getUserid());
							} else {
								System.out.format(">>> No update exec'd since there is no user with id %d%n", id);
							}
						}
				).thenEmpty(Mono.empty());
		
	}
	
	
	@Override
	public Mono<Void> deleteUser(Integer id) {
		
		if (users.containsKey(id)) {
			users.remove(id);
			System.out.format(">>> Deleted user with id %d%n", id);
		} else {
			System.out.format(">>> No delete exec'd since there is no user with id %d%n", id);
		}
		return Mono.empty();
		
	}
	
	
}
