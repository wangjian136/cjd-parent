package com.cjd;
 
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import de.codecentric.boot.admin.server.config.EnableAdminServer;
 
@EnableAdminServer
@SpringBootApplication
public class SpringBootAdminApp {
 
	public static void main(String[] args) {
		SpringApplication.run(SpringBootAdminApp.class, args);
	}
	
}