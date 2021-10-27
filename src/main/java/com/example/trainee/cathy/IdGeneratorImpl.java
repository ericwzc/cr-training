package com.example.trainee.cathy;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.example.demo.id.IdGenerator;

public class IdGeneratorImpl implements IdGenerator {

	private int runnningNumber = 0;
	
	@Override
	public String getId(String prefix) {
		return prefix+ now() + String.format("%05d", ++runnningNumber);
	}

	private String now() {
		return new SimpleDateFormat("yyyyMMddHHmm").format(Calendar.getInstance().getTime());
	}
}
