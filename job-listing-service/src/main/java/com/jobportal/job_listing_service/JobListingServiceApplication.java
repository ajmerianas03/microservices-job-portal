package com.jobportal.job_listing_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication

public class JobListingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobListingServiceApplication.class, args);
	}

}
