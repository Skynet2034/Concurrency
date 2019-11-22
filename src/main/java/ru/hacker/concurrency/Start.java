package ru.hacker.concurrency;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class Start {

    public static void main(String[] args) {
        int max = 10;
        ArrayBubble array = new ArrayBubble(max);

        IntStream.generate(() -> ThreadLocalRandom.current().nextInt(max)).limit(max).forEach(array::into);

        array.bubbleSorter();

        array.printer();
    }
}
