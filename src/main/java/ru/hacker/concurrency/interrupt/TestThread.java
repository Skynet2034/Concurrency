package ru.hacker.concurrency.interrupt;

public class TestThread implements Runnable {

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println("Start");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println(String.format("Было вызвано прерывание: %s", e.getMessage()));
            }
        }
    }
}
