package com.synchrony.challenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@ComponentScan(basePackages = "com.synchrony.challenge")
@SpringBootApplication
public class ChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChallengeApplication.class, args);
	}
	
	 @Bean
	   public RestTemplate getRestTemplate() {
	      return new RestTemplate();
	   }

}
