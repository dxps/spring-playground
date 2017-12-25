package tacos;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author vision8
 */
@Controller
public class HomeController {
	
	@GetMapping("/")
	public String home() {
		return "home"; // return the view name
	}
	
}
