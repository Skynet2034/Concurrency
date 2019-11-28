package ru.hacker.concurrency.interrupt;

public class Start {

    public static void main(String[] args) throws InterruptedException {
        TestThread testThread = new TestThread();
        Thread thread = new Thread(testThread);
        thread.start();
        Thread.sleep(200);
        //thread.interrupt();
        testThread.setStop();
    }
}
