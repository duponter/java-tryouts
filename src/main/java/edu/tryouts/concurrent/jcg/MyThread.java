package edu.tryouts.concurrent.jcg;

/**
 * Class MyThread.
 *
 * @author Erwin Dupont
 * @since 2015-01-12
 */
public class MyThread extends Thread {

	public MyThread(String name) {
		super(name);
	}

	@Override
	public void run() {
		System.out.println("Executing thread " + Thread.currentThread().getName());
	}

	public static void main(String[] args) throws InterruptedException {
		Thread myThread = new MyThread("myThread");
		myThread.start();

		myThread = new Thread(() -> System.out.println("Executing thread " + Thread.currentThread().getName()), "myRunnable");
		myThread.start();
	}
}