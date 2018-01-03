package tech.vision8.oauth2.sample1.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author vision8
 */
@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		// configuring web-based security for specific http requests.
		
		http
				.formLogin().disable()  // disable form-based authentication
				.anonymous().disable()  // disable anonymous user
				.authorizeRequests().anyRequest().denyAll();  // denying all access
		
	}
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		// here we configure the default authenticationManager @Bean
		
		auth.inMemoryAuthentication()  // creating user in memory
				.withUser("user")	.password("password").roles("USER")
				.and()
				.withUser("admin").password("password").roles("ADMIN");
		
	}
	
	
	@Override
	@Bean  // exposing the default AuthenticationManager as a bean, used in AuthorizationConfig
	public AuthenticationManager authenticationManagerBean() throws Exception {
		
		return super.authenticationManagerBean();
		
	}
	
	
}
