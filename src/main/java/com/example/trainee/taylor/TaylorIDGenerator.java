package com.example.trainee.taylor;

import com.example.demo.id.IdGenerator;

import java.util.*;

public class TaylorIDGenerator implements IdGenerator {
    private HashMap<String, Integer> idMap = new HashMap<>();
    private int suffixNum = 1;
    @Override
    public String getId(String prefix) {
        if(idMap.containsKey(prefix)) {
            suffixNum = idMap.get(prefix) + 1;
        }
        idMap.put(prefix,suffixNum);
        return prefix+":"+ suffixNum;
    }
}
