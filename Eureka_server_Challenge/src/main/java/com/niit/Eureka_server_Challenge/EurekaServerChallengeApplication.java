package com.niit.Eureka_server_Challenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServerChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServerChallengeApplication.class, args);
	}

}
