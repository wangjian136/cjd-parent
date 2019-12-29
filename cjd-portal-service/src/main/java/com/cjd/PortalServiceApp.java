package com.cjd;
 
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
 
 
@EnableEurekaClient
@SpringBootApplication
public class PortalServiceApp {
 
	public static void main(String[] args) {
		SpringApplication.run(PortalServiceApp.class, args);
	}
	
}