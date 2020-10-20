package com.pdcmb.covid19stats;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.sql.Date;
import java.util.List;

import com.pdcmb.covid19stats.domain.entities.Data;

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
