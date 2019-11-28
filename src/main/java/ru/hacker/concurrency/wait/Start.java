package ru.hacker.concurrency.wait;

public class Start {

    public static void main(String[] args) {
        Store<String> store = new Store<>(10);
        Thread thread = new Thread(new Producer(store));
        Thread thread2 = new Thread(new Consumer(store));

        thread.start();
        thread2.start();
    }
}
