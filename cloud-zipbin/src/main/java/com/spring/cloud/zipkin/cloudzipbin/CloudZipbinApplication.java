package com.spring.cloud.zipkin.cloudzipbin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin.server.EnableZipkinServer;


@SpringBootApplication
@EnableZipkinServer
public class CloudZipbinApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudZipbinApplication.class, args);
	}

}
