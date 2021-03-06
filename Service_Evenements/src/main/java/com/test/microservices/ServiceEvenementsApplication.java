package com.test.microservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.test.microservices.services.PalmaresChampionService;

@SpringBootApplication
@EnableMongoRepositories
@EnableEurekaClient
public class ServiceEvenementsApplication implements CommandLineRunner {
@Autowired
PalmaresChampionService pcs;
	public static void main(String[] args) {
		SpringApplication.run(ServiceEvenementsApplication.class, args);
	}
	public void run(String... args) throws Exception{
		
		System.out.println("Tous les rest controllers ont étés exposés ...");
		
		
		
		
	    }

}
