package com.example.trainee.bob;

import com.example.demo.id.IdGenerator;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BobIdGenerator implements IdGenerator {
    private Map<String, Integer> integerHashMap = new ConcurrentHashMap<>();

    @Override
    public String getId(String prefix) {
        Integer value = integerHashMap.getOrDefault(prefix,0);
        integerHashMap.put(prefix, value++);
        return prefix+value;
    }





}
