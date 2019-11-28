package ru.hacker.concurrency.wait;

import java.util.Random;

public class Consumer implements Runnable {

    private final Store store;
    private Random rand=new Random();

    public Consumer(Store store) {
        this.store = store;
    }

    @Override
    public void run() {
        for (int i = 0; i < 15 && !Thread.currentThread().isInterrupted(); i++) {
            try {
                store.withdraw(rand.nextInt(3000));
                System.out.println("Withdrawal operation "+i);
                Thread.currentThread().sleep(40);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

