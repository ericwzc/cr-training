package com.example.trainee.taylor;

public class MainTask implements Runnable{
    @Override
    public void run() {
        TaylorIDGenerator idGenerator = new TaylorIDGenerator();
        for (int i = 0; i < 10; i++){
            System.out.println(idGenerator.getId("aaa"));
            System.out.println(idGenerator.getId("bbb"));
        }
    }
}
