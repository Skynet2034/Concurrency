package ru.hacker.concurrency.wait;

import java.util.LinkedList;
import java.util.Queue;

public class Store {

    private int balance;
    private final int max;
    private final int min;


    public Store(int min, int max) {
        this.min= min;
        this.max = max;
        this.balance=min;
    }

    public synchronized void add(int amount) throws InterruptedException {
        try {
            while (balance+amount>max) {
                wait();
            }
            balance+=amount;
            System.out.println("Placed to deposit "+ amount);
            System.out.println("Current balance "+balance);
        } finally {
            notifyAll();
        }
    }

    public synchronized void withdraw(int amount) throws InterruptedException {
        try {
            while (balance-amount<min) {
                wait();
            }
            balance-=amount;
            System.out.println("Withdrawed from deposit "+ amount);
            System.out.println("Current balance "+balance);
        } finally {
            notifyAll();
        }
    }
}
