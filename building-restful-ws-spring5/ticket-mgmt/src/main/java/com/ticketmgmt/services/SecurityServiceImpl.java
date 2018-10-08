package com.ticketmgmt.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;


/**
 * Implementation of the simple SecurityService.
 *
 * @author vision8
 */
@Service
public class SecurityServiceImpl implements SecurityService {
	
	private static final String secretKey= "4C8kum4LxyKWYLM78sKdXrzbBjDCFyfX";
	
	private static final byte[] secretKeyBytes = DatatypeConverter.parseBase64Binary(secretKey);
	
	
	@Override
	public String createToken(String subject, long ttlMillis) {
		
		if (ttlMillis <= 0) {
			throw new RuntimeException("Expiry time must be greater than Zero :["+ttlMillis+"] ");
		}
		
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		
		// The JWT signature algorithm used for signing the token
		long nowMillis = System.currentTimeMillis();
		
		Key signingKey = new SecretKeySpec(secretKeyBytes, signatureAlgorithm.getJcaName());
		
		JwtBuilder builder = Jwts.builder()
				.setSubject(subject)
				.signWith(signatureAlgorithm, signingKey);
		
		builder.setExpiration(new Date(nowMillis + ttlMillis));
		
		return builder.compact();
	}
	
	
	@Override
	public String getSubject(String token) {
		
		Claims claims = Jwts.parser()
				.setSigningKey(secretKeyBytes)
				.parseClaimsJws(token).getBody();
		
		return claims.getSubject();
	}
	
	
}
