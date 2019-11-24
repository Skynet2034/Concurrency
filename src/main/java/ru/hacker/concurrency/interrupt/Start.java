package ru.hacker.concurrency.interrupt;

public class Start {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new TestThread());
        thread.start();
        Thread.sleep(200);
        thread.interrupt();
    }
}
