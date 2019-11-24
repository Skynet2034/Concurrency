package ru.hacker.concurrency.sync;

public class CountThread implements Runnable {

    private final CommonResource commonResource;

    public CountThread(CommonResource commonResource) {
        this.commonResource = commonResource;
    }

    @Override
    public void run() {
        synchronized (commonResource) {
            commonResource.x = 1;
            for (int i = 1; i < 5 && !Thread.currentThread().isInterrupted(); i++) {
                System.out.printf("%s %d %n", Thread.currentThread().getName(), commonResource.x);
                commonResource.x++;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
