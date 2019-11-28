package ru.hacker.concurrency.wait;

import java.util.Random;

public class Producer implements Runnable {
    private Random rand=new Random();
    private final Store store;

    public Producer(Store store) {
        this.store = store;
    }

    @Override
    public void run() {
        for (int i = 0; i < 15 && !Thread.currentThread().isInterrupted(); i++) {
            try {
                store.add(rand.nextInt(3000));
                System.out.println("Add operation "+i);
                Thread.currentThread().sleep(20);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

