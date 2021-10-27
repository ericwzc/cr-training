package com.example.trainee.cathy;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import com.example.demo.id.IdGenerator;

public class IdGeneratorImpl implements IdGenerator {

	private Map<String, Integer> cacheMap = new HashMap<String, Integer>();
	
	@Override
	public String getId(String prefix) {
		return prefix + "-" + now() + "-" + this.getRunningNumber(prefix);
	}

	private String now() {
		return new SimpleDateFormat("yyyyMMddHHmm").format(Calendar.getInstance().getTime());
	}
	
	private String getRunningNumber(String prefix) {
		Integer value = cacheMap.get(prefix);
		
		//set init value 
		if(value == null) {
			value = 0;
		}
		
		//increment by 1 
		++value;
		
		//reset new value
		cacheMap.put(prefix, value);
		
		return String.format("%05d", value);
	}
}
