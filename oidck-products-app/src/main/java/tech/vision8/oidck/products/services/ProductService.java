package tech.vision8.oidck.products.services;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;


/**
 * @author vision8
 */
@Service
public class ProductService {
	
	public List<String> getProducts() {
		
		return Arrays.asList("iPad", "iPod", "iPhone");
		
	}
	
}
