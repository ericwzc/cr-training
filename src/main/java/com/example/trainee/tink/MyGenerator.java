package com.example.trainee.tink;

import com.example.demo.id.IdGenerator;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

public class MyGenerator implements IdGenerator {
    private Map<String, Integer> numMap;
    MyGenerator() {
        numMap = new HashMap<>();
    }

    private String transformToString(int number, int maxLength) {
        NumberFormat formatter = NumberFormat.getNumberInstance();
        formatter.setMinimumIntegerDigits(maxLength);
        formatter.setGroupingUsed(false);
        return formatter.format(number);
    }

    private Integer getNumberByKey(String key) {
        return numMap.getOrDefault(key, 0);
    }

    @Override
    public String getId(String prefix) {
        Integer currentNumber = getNumberByKey(prefix);
        numMap.put(prefix, ++currentNumber);
        return prefix + transformToString(currentNumber, 10);
    }
}

