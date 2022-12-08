package com.niit.jdp.BEJ_C2_S3_REST_API_MONGODB_MC_1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BejC2S3RestApiMongodbMc1Application {

	public static void main(String[] args) {
		SpringApplication.run(BejC2S3RestApiMongodbMc1Application.class, args);
	}

}
