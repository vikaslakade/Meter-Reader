package com.hardcoder.meterreader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class MeterReaderApplication {

	public static void main(String[] args) {
		SpringApplication.run(MeterReaderApplication.class, args);
	}

}
