package ru.hacker.concurrency;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class Start {

    private static int numberProcessor = Runtime.getRuntime().availableProcessors();

    public static void main(String[] args) throws InterruptedException {

        System.out.println(String.format("Число процессоров %d", numberProcessor));

        int max = 100_000;

        int count = numberProcessor;

        ArrayBubble[] arrayBubbles = new ArrayBubble[count];
        for (int i = 0; i < count; i++) {
            arrayBubbles[i] = new ArrayBubble(max / count);
            IntStream.generate(() -> ThreadLocalRandom.current().nextInt(max))
                    .limit(max / count)
                    .forEach(arrayBubbles[i]::into);
        }

        //Дописать логику таким образом, чтобы у вас создавалось столько потоков, сколько
        //доступно в вашей системе, см. numberProcessor. Исходя из него в массиве создайте numberProcessor потоков,
        //и запустите их.
        //В отдельном цикле делайте join на каждый поток!!!
        Thread thread = new Thread(arrayBubbles[0]::bubbleSorter);
        Thread thread2 = new Thread(arrayBubbles[1]::bubbleSorter);

        long currentTime = System.currentTimeMillis();
        thread.start();
        thread2.start();
        thread.join();
        thread2.join();
        System.out.println(String.format("Время работы %d", System.currentTimeMillis() - currentTime));

        ArrayBubble arrayBubble = new ArrayBubble(count);
        for (int i = 0; i < count; i++) {
            arrayBubble.into(arrayBubbles[i].getMin());
        }

        arrayBubble.bubbleSorter();

        arrayBubble.printer();
        System.out.println("Минимум:");
        System.out.println(arrayBubble.getMin());

        //array.printer();
    }
}
