package com.example.trainee.tink;

import com.example.demo.id.IdGenerator;
import java.text.NumberFormat;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class TinkIdGenerator implements IdGenerator {
    private Map<String, AtomicInteger> numMap = new ConcurrentHashMap();
    public TinkIdGenerator() {
    }

    private void generateMap() {

    }

    private String transformToString(int number, int maxLength) {
        NumberFormat formatter = NumberFormat.getNumberInstance();
        formatter.setMinimumIntegerDigits(maxLength);
        formatter.setGroupingUsed(false);
        return formatter.format(number);
    }

    private int getNumberByKey(String key) {
        AtomicInteger result = new AtomicInteger();
        numMap.putIfAbsent(key, new AtomicInteger(0));
        numMap.computeIfPresent(key, (k, v) -> {
            result.set(v.incrementAndGet());
            return v;
        });
        return result.get();
    }

    private String getTimestamp() {
        return "";
    }

    @Override
    public String getId(String prefix) {
        int currentNumber = getNumberByKey(prefix);
        return prefix + transformToString(currentNumber, 5);
    }
}

