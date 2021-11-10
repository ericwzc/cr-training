package com.example.trainee.taylor;

import com.example.demo.id.IdGenerator;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLongArray;

public class TaylorIDGenerator implements IdGenerator {
    private ConcurrentHashMap<String, AtomicLongArray> idMap;

    private static final int LAST_MINUTE_INDEX = 0;
    private static final int CURRENT_SEQ_INDEX = 1;

    public TaylorIDGenerator(){
        idMap = new ConcurrentHashMap<>();
    }
    @Override
    public String getId(String prefix) {
        return sequenceGen(prefix);
    }

    private String sequenceGen(String prefix) {
        idMap.putIfAbsent(prefix, new AtomicLongArray(new long[]{dateFormat(Calendar.getInstance().getTime()), 0}));
        AtomicLongArray atomicArray = idMap.get(prefix);

        //Reset sequence based on every minute
        Calendar calendar = Calendar.getInstance();
        long currentMinute =  dateFormat(calendar.getTime());
        long lastMinute = atomicArray.get(LAST_MINUTE_INDEX);
        if(currentMinute > lastMinute) {
            atomicArray.set(LAST_MINUTE_INDEX, currentMinute);
            atomicArray.set(CURRENT_SEQ_INDEX, 0);
        }

        long currentTimeStamp = dateFormat(calendar.getTime());
        long currentSeq = atomicArray.incrementAndGet(CURRENT_SEQ_INDEX);
        return String.format("%s-%s-%s",prefix,currentTimeStamp,currentSeq);
    }

    private long dateFormat(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmm");
        return Long.parseLong(formatter.format(date));
    }
}
