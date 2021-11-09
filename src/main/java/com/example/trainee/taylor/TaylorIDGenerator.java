package com.example.trainee.taylor;

import com.example.demo.id.IdGenerator;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class TaylorIDGenerator implements IdGenerator {
    private ConcurrentHashMap<String, AtomicInteger> idMap;
    private int lastMinute;

    public TaylorIDGenerator(){
        idMap = new ConcurrentHashMap<>();
        lastMinute = Calendar.getInstance().get(Calendar.MINUTE);
    }
    @Override
    public String getId(String prefix) {
        return sequenceGen(prefix);
    }

    private String sequenceGen(String prefix) {
        idMap.putIfAbsent(prefix, new AtomicInteger(0));
        AtomicInteger currentVal = idMap.get(prefix);
        Calendar calendar = Calendar.getInstance();
        int currentMinute =  calendar.get(Calendar.MINUTE);
        String currentTimeStamp = dateFormat(calendar.getTime());

        //Reset Init Sequence
        if(currentMinute != lastMinute) {
            currentVal.set(0);
            lastMinute = currentMinute;
        }

        return String.format("%s-%s-%s",prefix,currentTimeStamp,currentVal.incrementAndGet());
    }

    private String dateFormat(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmm");
        return formatter.format(date);
    }
}
