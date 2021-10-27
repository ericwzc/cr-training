package com.example.trainee.cathy;

import com.example.demo.id.IdGenerator;

public class Test {

	public static void main(String[] args) {
		
		IdGenerator generator = new IdGeneratorImpl();
		
		System.out.println(generator.getId("SIN"));
	}

}
