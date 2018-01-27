package tech.vision8.joke.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import tech.vision8.joke.services.JokeService;

/**
 * @author vision8
 */
@Controller
public class JokeController {

	private JokeService jokeService;
	
	@Autowired
	public JokeController(JokeService jokeService) {
		this.jokeService = jokeService;
	}
	
	@RequestMapping({ "", "/" })
	public String showJoke(Model model) {
		
		model.addAttribute("joke", jokeService.getJoke());
		return "chucknorris"; // view name
		
	}
	
}
