import java.util.LinkedList;
import java.util.Random;

class Monitor {
	static LinkedList<Integer> buffer = new LinkedList<Integer>();
	static int count = 0;
	static int bufferSize = 10;
	static Object emptyCount = new Object(), fillCount = new Object();

//@include
	public static void addToBuffer(int next, String producerThread)
			throws InterruptedException {
		synchronized (fillCount) {
			while (count == bufferSize) {
				System.out.println(producerThread + " going to sleep");
				fillCount.wait();
				System.out.println(producerThread + " waking up");
			}
		}
		synchronized (buffer) {
			buffer.addLast(next);
			System.out.println(producerThread + " added " + next);
			count++;
		}
		synchronized (emptyCount) {
			if (count == 1) {
				emptyCount.notify();
			}
		}
	}

	public static int consumeFromBuffer(String consumerThread)
			throws InterruptedException {
		synchronized (emptyCount) {
			while (count == 0) {
				System.out.println(consumerThread + " going to sleep");
				emptyCount.wait();
				System.out.println(consumerThread + " waking up");
			}
		}
		int removed = 0;
		synchronized (buffer) {
			removed = buffer.removeLast();
			System.out.println(consumerThread + " removed " + removed);
			count--;
		}
		synchronized (fillCount) {
			if (count == bufferSize - 1) {
				fillCount.notify();
			}
		}
		return removed;
	}
//@exclude
}

class Producer implements Runnable {
	private String name;
	private Random rand;

	public Producer(String name) {
		this.name = name;
		rand = new Random();
	}

	@Override
	public void run() {
		while (true) {
			int next = rand.nextInt(10);
			try {
				Monitor.addToBuffer(next, name);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}

class Consumer implements Runnable {
	private String name;

	public Consumer(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		while (true) {
			try {
				Monitor.consumeFromBuffer(name);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}

public class ProducerConsumer {

	public static void main(String[] args) throws InterruptedException {
		Thread producer1 = new Thread(new Producer("producer 1"));
		Thread producer2 = new Thread(new Producer("producer 2"));
		Thread consumer = new Thread(new Consumer("consumer"));
		producer1.start();
		producer2.start();
		consumer.start();
		producer1.join();
		producer2.join();
		consumer.join();

	}
}
