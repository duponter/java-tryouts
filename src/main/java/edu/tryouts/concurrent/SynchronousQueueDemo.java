package edu.tryouts.concurrent;

import java.util.concurrent.SynchronousQueue;

/**
 * Java Program to solve Producer Consumer problem using SynchronousQueue. A
 * call to put() will block until there is a corresponding thread to take() that
 * element.
 *
 * SynchronousQueue blocks until another thread is ready to take the element, one thread is trying to put.
 * SynchronousQueue has zero capacity.
 * SynchronousQueue is used to implement queuing strategy of  direct hand-off, where thread hands-off to waiting thread, else creates new one if allowed, else task rejected.
 * This queue does not permit null elements, adding null elements will result in NullPointerException.
 * For purposes of other Collection methods (for example contains), a SynchronousQueue acts as an empty collection.
 * You cannot peek at a synchronous queue because an element is only present when you try to remove it;
 *      Similarly you cannot insert an element (using any method) unless another thread is trying to remove it.
 * You cannot iterate over SynchronousQueue as there is nothing to iterate.
 * A SynchronousQueue constructed with fairness policy set to true grants threads access in FIFO order.
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