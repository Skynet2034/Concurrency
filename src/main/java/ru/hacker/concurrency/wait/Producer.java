package ru.hacker.concurrency.wait;

public class Producer implements Runnable {

    private final Store<String> store;

    public Producer(Store<String> store) {
        this.store = store;
    }

    @Override
    public void run() {
        for (int i = 0; i < 15 && !Thread.currentThread().isInterrupted(); i++) {
            try {
                store.add(String.format("Element %s", i));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
