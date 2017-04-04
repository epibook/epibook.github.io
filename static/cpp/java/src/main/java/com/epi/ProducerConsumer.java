/* from wikipedia, solution for multiple producers and consumers

semaphore mutex = 1;
semaphore fillCount = 0;
semaphore emptyCount = BUFFER_SIZE;

procedure producer() {
    while (true) {
        item = produceItem();
        down(emptyCount);
            down(mutex);
                putItemIntoBuffer(item);
            up(mutex);
        up(fillCount);
    }
}

procedure consumer() {
    while (true) {
        down(fillCount);
            down(mutex);
                item = removeItemFromBuffer();
            up(mutex);
        up(emptyCount);
        consumeItem(item);
    }
}
*/

// package com.epi;

import java.util.concurrent.Semaphore;
import java.util.Date;
import java.util.Deque;
import java.util.LinkedList;

public class ProducerConsumer {
  static final int BUFFER_SIZE = 10;
  static Semaphore emptyCount = new Semaphore(BUFFER_SIZE);
  static Semaphore fillCount = new Semaphore(0);
  static Semaphore mutex = new Semaphore(1);
  static Deque<String> buffer = new LinkedList<>();
  static int globalId = 0;

  static class Producer {
    String name;
    Producer(String name) { this.name = name; }
    void producer() throws InterruptedException {
      while (true) {
        String item = name + ":" + globalId++;
        emptyCount.acquire();
        mutex.acquire();
        buffer.addLast(item + " " + buffer.size());
        System.out.println(name + " wrote " + item + " buffer size now "
                           + buffer.size());
        mutex.release();
        fillCount.release();
      }
    }
  }

  static class Consumer {
    String name;
    Consumer(String name) { this.name = name; }
    void consumer() throws InterruptedException {
      while (true) {
        fillCount.acquire();
        mutex.acquire();
        String item = buffer.removeFirst();
        mutex.release();
        emptyCount.release();
        System.out.println(name + " read " + item);
      }
    }
  }

  static class ProducerRunnable implements Runnable {
    Producer p;

    ProducerRunnable(Producer p) { this.p = p; }

    public void run() {
      try {
        p.producer();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  static class ConsumerRunnable implements Runnable {
    Consumer c;

    ConsumerRunnable(Consumer c) { this.c = c; }

    public void run() {
      try {
        c.consumer();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    };
  }

  public static void main(String[] args) {
    Producer p1 = new Producer("p1");
    Producer p2 = new Producer("p2");
    Consumer c1 = new Consumer("c1");
    Consumer c2 = new Consumer("c2");

    Thread tp1 = new Thread(new ProducerRunnable(p1));
    Thread tp2 = new Thread(new ProducerRunnable(p2));
    Thread tc1 = new Thread(new ConsumerRunnable(c1));
    Thread tc2 = new Thread(new ConsumerRunnable(c2));

    tp1.start();
    tp2.start();
    tc1.start();
    tc2.start();
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
