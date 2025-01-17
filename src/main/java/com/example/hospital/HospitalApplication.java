package com.example.hospital;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Disbaled Security
@SpringBootApplication()
public class HospitalApplication {

	public static void main(String[] args) {
		System.setProperty("java.rmi.server.codebase", "file:../../../../../../../../proxy/src");

		SpringApplication.run(HospitalApplication.class, args);
	}

}
