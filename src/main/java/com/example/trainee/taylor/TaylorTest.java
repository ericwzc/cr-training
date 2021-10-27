package com.example.trainee.taylor;

public class TaylorTest {
    public static void main(String[] args){
        final int THREAD_COUNT = 5;
        for(int i = 0; i< THREAD_COUNT; i++){
            Thread thread = new Thread(new MainTask());
            thread.setName("thread" + i);
            thread.start();
        }
    }
}
