package com.example.trainee.cathy;

import com.example.demo.id.IdGenerator;

public class Test {

	public static void main(String[] args) {
		
		IdGenerator generator = new IdGeneratorImpl();
		
		int i = 0;
		
		while (i <= 100) {
			System.out.println(generator.getId("SIN"));
			System.out.println(generator.getId("ICN"));
			++i;
		}
	}

}
