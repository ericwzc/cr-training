package com.example.trainee.taylor;

import com.example.demo.id.IdGenerator;

import java.util.Random;

public class TaylorIDGenerator implements IdGenerator {
    static int suffixNum = 1;
    @Override
    public String getId(String prefix) {
        return prefix+":"+ (++suffixNum);
    }
}
