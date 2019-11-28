package ru.hacker.concurrency.wait;

public class Consumer implements Runnable {

    private final Store<String> store;

    public Consumer(Store<String> store) {
        this.store = store;
    }

    @Override
    public void run() {
        for (int i = 0; i < 15 && !Thread.currentThread().isInterrupted(); i++) {
            try {
                System.out.println(store.poll());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
