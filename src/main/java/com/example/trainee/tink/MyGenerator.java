package com.example.trainee.tink;

import com.example.demo.id.IdGenerator;

import java.text.NumberFormat;

public class MyGenerator implements IdGenerator {
    private int currentNumber;
    MyGenerator() {
        currentNumber = 1;
    }

    private String transformToString(int number, int maxLength) {
        NumberFormat formatter = NumberFormat.getNumberInstance();
        formatter.setMinimumIntegerDigits(maxLength);
        formatter.setGroupingUsed(false);
        return formatter.format(number);
    }

    @Override
    public String getId(String prefix) {
        StringBuilder sb = new StringBuilder();
        sb.append(prefix);
        sb.append(transformToString(currentNumber++, 10));
        return sb.toString();
    }
}

