package com.example.trainee.taylor;

import com.example.demo.id.IdGenerator;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class TaylorIDGenerator implements IdGenerator {
    private ConcurrentHashMap<String, AtomicInteger> idMap = new ConcurrentHashMap<>();

    @Override
    public String getId(String prefix) {
        if(idMap.containsKey(prefix)){
            idMap.computeIfPresent(prefix, (key , val)  -> idMap.put(key, new AtomicInteger(val.getAndIncrement())));
        } else {
            idMap.computeIfAbsent(prefix, k-> new AtomicInteger(1));
        }

        return prefix+":"+ idMap.get(prefix);
    }
}
