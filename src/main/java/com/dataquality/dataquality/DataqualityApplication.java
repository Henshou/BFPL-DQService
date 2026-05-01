package com.dataquality.dataquality;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
public class DataqualityApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataqualityApplication.class, args);
	}

}
