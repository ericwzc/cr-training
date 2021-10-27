package com.example.trainee.bob;

import com.example.demo.id.IdGenerator;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;
@Component
public class BobIdGenerator implements IdGenerator {
    @Override
    public String getId(String prefix) {
        Instant instant = new Date().toInstant();
        return prefix + instant.toString();
    }
}
