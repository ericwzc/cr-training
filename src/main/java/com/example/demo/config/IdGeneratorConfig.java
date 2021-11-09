package com.example.demo.config;

import com.example.trainee.DummyIdGenerator;
import com.example.trainee.taylor.TaylorIDGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.id.IdGenerator;

@Configuration
public class IdGeneratorConfig {

	@Bean
	public IdGenerator idGenerator() {
		return new TaylorIDGenerator();
	}

}
