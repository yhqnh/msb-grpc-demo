package com.yhq.msb.grpc.demo;

public class Main {

    private static Object lock = new Object();

    public static void main(String[] args) {

        new Thread(() -> {
            synchronized (lock) {
                System.out.println("1");
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("2");
            }
        }).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (lock) {
            System.out.println("3");
            try {
                lock.notify();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("4");
        }
    }
}
