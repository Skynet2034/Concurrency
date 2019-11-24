package ru.hacker.concurrency;

import java.util.concurrent.atomic.AtomicInteger;

public class TestIncrement {

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    private int value = 0;

    public int getValue() {
        return atomicInteger.getAndIncrement();
    }
}
