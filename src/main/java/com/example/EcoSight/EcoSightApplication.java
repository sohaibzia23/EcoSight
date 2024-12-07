package com.example.EcoSight;

import com.example.EcoSight.fileUpload.StorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class EcoSightApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcoSightApplication.class, args);
	}


}
