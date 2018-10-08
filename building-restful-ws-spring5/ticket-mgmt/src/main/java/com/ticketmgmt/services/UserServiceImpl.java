package com.ticketmgmt.services;

import com.ticketmgmt.domain.User;
import com.ticketmgmt.repo.UserRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


/**
 * Implementation of UserService.
 *
 * @author vision8
 */
@Service
public class UserServiceImpl implements UserService {
	
	private UserRepository userRepository;
	
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	
	@Override
	public Flux<User> getAllUsers() {
		return userRepository.getAllUsers();
	}
	
	@Override
	public Mono<User> getUser(Integer id) {
		
		return userRepository.getUser(id);
		
	}
	
	@Override
	public Mono<Void> createUser(Integer userid, String username) {
		
		return userRepository.saveUser(Mono.just(new User(userid, username)));
		
	}
	
	@Override
	public Mono<Void> updateUser(Integer userid, String username) {
		
		Mono<User> monoUser = userRepository.getUser(userid)
				.map(user -> {
					user.setUsername(username);
					return user;
				});
		return userRepository.updateUser(userid, monoUser);
		
	}
	
	@Override
	public Mono<Void> deleteUser(Integer userid) {
		
		return userRepository.deleteUser(userid);
		
	}
	
	
}
