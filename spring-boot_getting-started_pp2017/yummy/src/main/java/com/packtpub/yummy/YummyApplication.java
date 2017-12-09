package com.packtpub.yummy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

@SpringBootApplication
public class YummyApplication {

	public static void main(String[] args) {
		
		ConfigurableApplicationContext context = SpringApplication.run(YummyApplication.class, args);
		for (String name : context.getBeanDefinitionNames()) {
			System.out.println(">>> bean: " + name);
		}
		
		System.out.println(">>> App Started Complete.");
		
	}
	
}

// Let's get our own bean into the Spring context.
// @Component tells Spring: "This is a bean, please instantiate it."

@Component
class DatePrinter {
	
	// using the TimeFactory bean, so we can use it in printDate()
	@Autowired
	private TimeFactory timeFactory;

	public String printDate() {
		return "Now it is " + timeFactory.now();
	}

	@PostConstruct
	public void runAfterDepsAreFullfilled() {
		System.out.println(">>> DatePrinter: " + printDate());
	}
}


class TimeFactory {
	
	public LocalDateTime now() {
		return LocalDateTime.now();
	}
	
}


@Configuration
class MyConfiguration {
	
	// instead of adding "@Component" annotation to TimeFactory class definition,
	// we specify it as being a bean into this JavaConfig class.
	@Bean
	public TimeFactory timeFactory() {
		return new TimeFactory();
	}
	
	@PostConstruct
	public void post() {
		System.out.println("\n>>> MyConfiguration > timeFactory()=" + timeFactory());
		System.out.println(">>> MyConfiguration > timeFactory()=" + timeFactory() + "\n");
	}
	
}

