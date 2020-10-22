package com.pdcmb.covid19stats;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@EnableCaching
@SpringBootApplication
public class Covid19statsApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Covid19statsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}

	@Bean
	public WebClient getWebClient(){
		return WebClient.create("https://api.covid19api.com");
	}	

}
