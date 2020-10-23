package com.pdcmb.covid19stats;

import com.pdcmb.covid19stats.domain.usecases.GetRegionData;
import com.pdcmb.covid19stats.domain.usecases.FilterData.Response;
import com.pdcmb.covid19stats.domain.usecases.GetRegionData.Request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@EnableCaching
@SpringBootApplication
public class Covid19statsApplication implements CommandLineRunner {

	@Autowired
	private GetRegionData getRegionData;

	public static void main(String[] args) {
		SpringApplication.run(Covid19statsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		getRegionData.execute(new Request(null))
						.subscribe(
							response -> System.out.println(response.getRegion().getName() + " loaded"),
							 ex ->  System.out.println(ex.getMessage()),
							  () -> System.out.println("All data loaded")
						);
	}

	@Bean
	public WebClient getWebClient(){
		return WebClient.create("https://api.covid19api.com");
	}	

}
