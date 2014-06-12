package edu.tryouts.concurrent;

import java.util.concurrent.SynchronousQueue;

/**
 * Java Program to solve Producer Consumer problem using SynchronousQueue. A
 * call to put() will block until there is a corresponding thread to take() that
 * element.
 *
 * @author Javin Paul
 */
public class SynchronousQueueDemo {

    public static void main(String args[]) {

        final SynchronousQueue<String> queue = new SynchronousQueue<>(true);
// If you have send the output carefully then you would have noticed that order of events are reversed.
// Seems[CONSUMER] thread is consuming data even before[ PRODUCER]thread has produced it.
// This happens because by default SynchronousQueue doesn’t guarantee any order, but it has a fairness policy, which if set to true allows access to threads in FIFO order.
// You can enable this fairness policy by passing true to overloaded constructor of SynchronousQueue i.e.new SynchronousQueue( boolean fair).

        Thread producer = new Thread("PRODUCER") {
            public void run() {
                String event = "FOUR";
                try {
                    queue.put(event); // thread will block here
                    System.out.printf("[%s] published event : %s %n", Thread
                            .currentThread().getName(), event);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };

        producer.start(); // starting publisher thread

        Thread consumer = new Thread("CONSUMER") {
            public void run() {
                try {
                    String event = queue.take(); // thread will block here
                    System.out.printf("[%s] consumed event : %s %n", Thread
                            .currentThread().getName(), event);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };

        consumer.start(); // starting consumer thread

    }

}