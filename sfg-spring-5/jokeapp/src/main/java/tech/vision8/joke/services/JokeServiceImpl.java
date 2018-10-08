package tech.vision8.joke.services;

import guru.springframework.norris.chuck.ChuckNorrisQuotes;
import org.springframework.stereotype.Service;


/**
 * @author vision8
 */
@Service
public class JokeServiceImpl implements JokeService {
	
	private final ChuckNorrisQuotes chuckNorrisQuotes;
	
	public JokeServiceImpl() {
		this.chuckNorrisQuotes = new ChuckNorrisQuotes();
	}
	
	@Override
	public String getJoke() {
		return chuckNorrisQuotes.getRandomQuote();
	}

}
