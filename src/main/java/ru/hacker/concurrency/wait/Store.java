package ru.hacker.concurrency.wait;

import java.util.LinkedList;
import java.util.Queue;

public class Store<T> {

    private final Queue<T> queue = new LinkedList<>();

    private final int max;

    private int counter = 0;

    public Store(int max) {
        this.max = max;
    }

    public synchronized void add(T element) throws InterruptedException {
        try {
            while (counter >= max) {
                wait();
            }
            counter++;
            queue.add(element);
            System.out.println(String.format("Add %s", element));
        } finally {
            notifyAll();
        }
    }

    public synchronized T poll() throws InterruptedException {
        try {
            while (counter <= 0) {
                wait();
            }
            counter--;
            System.out.print("Poll ");
            return queue.poll();
        } finally {
            notifyAll();
        }
    }
}
