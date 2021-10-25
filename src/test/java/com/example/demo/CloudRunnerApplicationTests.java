package com.example.demo;

import com.example.demo.id.IdGenerator;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CloudRunnerApplicationTests {

	@Autowired
	IdGenerator idGenerator;

	@Test
	void test4Exercise1() {
		String[] prefixes = { "aaa", "bbb" };
		Random random = new Random();

		for (int i = 0; i < 30; i++) {
			int r = random.nextInt(2);
			System.out.println(idGenerator.getId(prefixes[r]));
			try {
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException ignore) {
			}
		}
	}

	@Test
	void test4Exercise2() throws InterruptedException {
		String[] prefixes = { "aaa", "bbb", "ccc" };
		int threadSize = 3;
		
		CountDownLatch latch = new CountDownLatch(threadSize);

		for (int j = 0; j < threadSize; j++) {
			new Thread(() -> {
				Random random = new Random();

				for (int i = 0; i < 30; i++) {
					int r = random.nextInt(3);
					System.out.println(Thread.currentThread() + ":" + idGenerator.getId(prefixes[r]));
					try {
						TimeUnit.SECONDS.sleep(3);
					} catch (InterruptedException ignore) {
					}
				}
				latch.countDown();
			}).start();
		}
		
		latch.await();
	}

}
