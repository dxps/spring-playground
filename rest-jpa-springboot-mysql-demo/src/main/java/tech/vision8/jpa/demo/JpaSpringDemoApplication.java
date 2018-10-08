package tech.vision8.jpa.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableJpaAuditing
public class JpaSpringDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaSpringDemoApplication.class, args);
    }

}
