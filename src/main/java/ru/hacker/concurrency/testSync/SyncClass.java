package ru.hacker.concurrency.testSync;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

public class SyncClass {

    private Queue<String> queue = new LinkedList();

    private final Object input = new Object();

    private final Object output = new Object();

    private final AtomicInteger count = new AtomicInteger(0);

    public void put(String element) {
        synchronized (input) {
            queue.add(element);
            count.incrementAndGet();
            System.out.println("Put");
        }
    }

    public String poll() {
        synchronized (output) {
            if (count.get() <= 1) {
                return "Empty";
            }
            System.out.println("Poll");
            String elem = queue.poll();
            count.decrementAndGet();
            return elem;
        }
    }
}

class Start {

    public static void main(String[] args) throws InterruptedException {
        SyncClass syncClass = new SyncClass();

        Thread thread = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                syncClass.put(String.format("Element - %d", i));
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println(syncClass.poll());
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
        thread2.start();
    }
}
