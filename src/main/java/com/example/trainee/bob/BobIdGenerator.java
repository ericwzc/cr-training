package com.example.trainee.bob;

import com.example.demo.id.IdGenerator;

import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class BobIdGenerator implements IdGenerator {
    private Map<String, AtomicInteger> integerHashMap = new ConcurrentHashMap<>();

    public String generateId(String prefix) {
        AtomicInteger value = integerHashMap.getOrDefault(prefix,new AtomicInteger(0));
        Integer result = value.getAndAdd(1);
        integerHashMap.putIfAbsent(prefix,value);
        return prefix+result;
    }

    @Override
    public String getId(String prefix) {
        return generateId(prefix + Instant.now().toString().replaceAll("\\D", ""));
    }
}
