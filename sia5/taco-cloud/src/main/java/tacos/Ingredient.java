package tacos;

import lombok.Data;
import lombok.RequiredArgsConstructor;


/**
 * @author vision8
 */
@Data
@RequiredArgsConstructor
public class Ingredient {
	
	private final String id;
	
	private final String name;
	
	private final Type type;
	
	public static enum Type {
		WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
	}
	
}
