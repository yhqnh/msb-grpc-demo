package com.yhq.msb.grpc.demo.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    private static ReentrantLock reentrantLock = new ReentrantLock();

    static Condition condition1 = reentrantLock.newCondition();

    static Condition condition2 = reentrantLock.newCondition();

    public static void main(String[] args) {
        new Thread(() -> {
            reentrantLock.lock();
            try {
                System.out.println("1");
                condition1.await();
                System.out.println("2");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                reentrantLock.unlock();
            }
        }).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        reentrantLock.lock();
        try {
            System.out.println("3");
            condition1.signal();
            System.out.println("4");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }
    }
}
