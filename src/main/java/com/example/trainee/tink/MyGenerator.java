package com.example.trainee.tink;

import com.example.demo.id.IdGenerator;
import java.text.NumberFormat;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MyGenerator implements IdGenerator {
    private Map<String, AtomicInteger> numMap;
    MyGenerator() {
        numMap = new ConcurrentHashMap<String, AtomicInteger>();
    }

    private String transformToString(int number, int maxLength) {
        NumberFormat formatter = NumberFormat.getNumberInstance();
        formatter.setMinimumIntegerDigits(maxLength);
        formatter.setGroupingUsed(false);
        return formatter.format(number);
    }

    private AtomicInteger getNumberByKey(String key) {
        numMap.putIfAbsent(key, new AtomicInteger(0));
        return numMap.get(key);
    }

    private String getTimestamp() {
        return "";
    }

    @Override
    public String getId(String prefix) {
        AtomicInteger currentNumberObject = getNumberByKey(prefix);
        int currentNumber = currentNumberObject.incrementAndGet();
        return prefix + transformToString(currentNumber, 10);
    }
}

