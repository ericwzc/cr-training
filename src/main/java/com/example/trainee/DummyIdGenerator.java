package com.example.trainee;

import com.example.demo.id.IdGenerator;

public class DummyIdGenerator implements IdGenerator {
    @Override
    public String getId(String prefix) {
        return prefix+":"+"dummy";
    }
}
