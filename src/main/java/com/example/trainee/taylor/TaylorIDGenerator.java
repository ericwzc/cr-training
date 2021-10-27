package com.example.trainee.taylor;

import com.example.demo.id.IdGenerator;

import java.util.*;
import java.util.concurrent.Semaphore;

public class TaylorIDGenerator implements IdGenerator {
    private HashMap<String, Integer> idMap = new HashMap<>();
    private int suffixNum = 1;
    private Semaphore mutex = new Semaphore(1);

    @Override
    public String getId(String prefix) {
        try {
            mutex.acquire();
            if (idMap.containsKey(prefix)) {
                suffixNum = idMap.get(prefix) + 1;
            }
            idMap.put(prefix, suffixNum);
            return prefix + ":" + suffixNum;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            mutex.release();
        }
        return "bad sequence!";
    }
}
