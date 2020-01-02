package com.cjd;
 
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
 
 
@EnableEurekaClient
@SpringBootApplication
public class ItemServiceApp {
 
	public static void main(String[] args) {
		SpringApplication.run(ItemServiceApp.class, args);
	}
	
}