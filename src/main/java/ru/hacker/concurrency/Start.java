package ru.hacker.concurrency;

import ru.hacker.concurrency.interrupt.TestThread;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class Start {

    private static int numberProcessor = Runtime.getRuntime().availableProcessors();

    public static void main(String[] args) throws InterruptedException {

        TestIncrement testIncrement = new TestIncrement();

        for (int i = 0; i < 6; i++) {
            System.out.println("Вывод " + i);
            Thread t = new Thread(() -> {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(testIncrement.getValue());
            });
            t.start();
        }

/*
        System.out.println(String.format("Число процессоров %d", numberProcessor));

        int max = 1_000_000;

        int count = numberProcessor;

        ArrayBubble[] arrayBubbles = new ArrayBubble[count];
        for (int i = 0; i < count; i++) {
            arrayBubbles[i] = new ArrayBubble(max / count, String.format("Побочный поток %s", i));
            IntStream.generate(() -> ThreadLocalRandom.current().nextInt(max))
                    .limit(max / count)
                    .forEach(arrayBubbles[i]::into);
        }

        System.out.println("Запустили задачу");
        long currentTime = System.currentTimeMillis();

        Thread[] threads = new Thread[count];
        for (int i = 0; i < count; i++) {
            threads[i] = new Thread(arrayBubbles[i]::bubbleSorter);
            threads[i].start();
        }

        for (int i = 0; i < count; i++) {
            if (threads[i].isAlive()) {
                threads[i].join();
            }
        }

        System.out.println(String.format("Время работы %d", System.currentTimeMillis() - currentTime));

        ArrayBubble arrayBubble = new ArrayBubble(count, Thread.currentThread().getName());
        for (int i = 0; i < count; i++) {
            arrayBubble.into(arrayBubbles[i].getMin());
        }

        arrayBubble.bubbleSorter();

        arrayBubble.printer();
        System.out.print("Минимум:");
        System.out.println(arrayBubble.getMin());*/

        //array.printer();
    }
}
