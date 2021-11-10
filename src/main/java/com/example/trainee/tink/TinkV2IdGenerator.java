package com.example.trainee.tink;

import com.example.demo.id.IdGenerator;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

public class TinkV2IdGenerator implements IdGenerator {
    private Map<String, AtomicInteger> numMap = new ConcurrentHashMap();
    private AtomicLong latestMapVersion;

    public TinkV2IdGenerator() {
        latestMapVersion = new AtomicLong(getTimestamp());
    }

    private String transformToString(int number, int maxLength) {
        NumberFormat formatter = NumberFormat.getNumberInstance();
        formatter.setMinimumIntegerDigits(maxLength);
        formatter.setGroupingUsed(false);
        return formatter.format(number);
    }

    private Long getTimestamp() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmm");
        return Long.parseLong(dateFormat.format(date));
    }

    private Integer getNumber(String prefix, long timestamp) {
        if (latestMapVersion.get() != timestamp) {
            numMap.clear();
            latestMapVersion.set(timestamp);
        }
        AtomicReference<AtomicInteger> result = new AtomicReference<>();
        numMap.computeIfAbsent(prefix, k -> {
            AtomicInteger v = new AtomicInteger(0);
            result.set(v);
            return v;
        });
        if (result.get() == null) {
            result.set(numMap.get(prefix));
        }
        return result.get().incrementAndGet();
    }

    @Override
    public String getId(String prefix) {
        Long timestamp = getTimestamp();
        return prefix + "-" + timestamp.toString() + "-" + transformToString(getNumber(prefix, timestamp), 5);
    }
}
