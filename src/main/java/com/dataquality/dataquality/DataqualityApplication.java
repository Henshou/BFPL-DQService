package com.dataquality.dataquality;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableKafka
@SpringBootApplication
@EnableScheduling
public class DataqualityApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataqualityApplication.class, args);
	}

}
