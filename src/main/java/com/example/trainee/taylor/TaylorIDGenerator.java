package com.example.trainee.taylor;

import com.example.demo.id.IdGenerator;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

public class TaylorIDGenerator implements IdGenerator {
    private ConcurrentHashMap<String, AtomicIntegerArray> idMap;
    private int lastMinute;

    private static final int LAST_MINUTE_INDEX = 0;
    private static final int CURRENT_SEQ_INDEX = 1;

    public TaylorIDGenerator(){
        idMap = new ConcurrentHashMap<>();
        lastMinute = Calendar.getInstance().get(Calendar.MINUTE);
    }
    @Override
    public String getId(String prefix) {
        return sequenceGen(prefix);
    }

    private String sequenceGen(String prefix) {
        idMap.putIfAbsent(prefix, new AtomicIntegerArray(new int[]{lastMinute, 0}));
        AtomicIntegerArray atomicIntArray = idMap.get(prefix);

        //Reset sequence based on every minute
        Calendar calendar = Calendar.getInstance();
        int currentMinute =  calendar.get(Calendar.MINUTE);
        int lastMinute = atomicIntArray.get(LAST_MINUTE_INDEX);
        if(currentMinute > lastMinute || (currentMinute == 0 && lastMinute == 59)) {
            atomicIntArray.set(LAST_MINUTE_INDEX, currentMinute);
            atomicIntArray.set(CURRENT_SEQ_INDEX, 0);
        }

        String currentTimeStamp = dateFormat(calendar.getTime());
        int currentSeq = atomicIntArray.incrementAndGet(CURRENT_SEQ_INDEX);
        return String.format("%s-%s-%s",prefix,currentTimeStamp,currentSeq);
    }

    private String dateFormat(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmm");
        return formatter.format(date);
    }
}
