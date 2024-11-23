package com.example.salecampaign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example.salecampaign")
public class SalecampaignApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalecampaignApplication.class, args);
		System.out.println("API is running...");
	}

}
