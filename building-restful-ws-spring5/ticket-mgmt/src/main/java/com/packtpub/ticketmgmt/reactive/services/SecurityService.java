package com.packtpub.ticketmgmt.reactive.services;


/**
 * @author vision8
 */
public interface SecurityService {
	
	String createToken(String subject, long ttlMillis);
	
	String getSubject(String token);

}
