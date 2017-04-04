// @include
public class Semaphore {
  private final int MAX_AVAILABLE;
  private int taken;

  public Semaphore(int maxAvailable) {
    this.MAX_AVAILABLE = maxAvailable;
    this.taken = 0;
  }

  public synchronized void acquire() throws InterruptedException {
    while (this.taken == MAX_AVAILABLE) {
      wait();
    }
    this.taken++;
  }

  public synchronized void release() throws InterruptedException {
    this.taken--;
    this.notifyAll();
  }
  // @exclude
  public int numTaken() { return taken; }
  // @include
}
// @exclude

class SemaphoreTest {
  static int sharedData = 0;

  public static void main(String[] args) {
    Semaphore semaphore = new Semaphore(1);
    System.out.println("Semaphore with 1 permit has been created");

    IncrementThread incrementThread1 = new IncrementThread(semaphore);
    new Thread(incrementThread1).start();

    DecrementThread decrementThread1 = new DecrementThread(semaphore);
    new Thread(decrementThread1).start();

    IncrementThread incrementThread2 = new IncrementThread(semaphore);
    new Thread(incrementThread2).start();

    DecrementThread decrementThread2 = new DecrementThread(semaphore);
    new Thread(decrementThread2).start();
  }
}

class IncrementThread implements Runnable {
  Semaphore semaphore;

  public IncrementThread(Semaphore semaphore) { this.semaphore = semaphore; }

  public void run() {
    while (true) {
      System.out.println(Thread.currentThread().getName()
                         + " is waiting for permit");
      try {
        semaphore.acquire();
        System.out.println(Thread.currentThread().getName()
                           + " has got permit, new count is "
                           + semaphore.numTaken());

        for (int i = 0; i < 3; i++) {
          Thread.sleep(200);
          System.out.println(Thread.currentThread().getName()
                             + " increments shared data: "
                             + SemaphoreTest.sharedData++);
        }

      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      System.out.println(Thread.currentThread().getName()
                         + " has released permit");
      try {
        semaphore.release();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}

class DecrementThread implements Runnable {
  Semaphore semaphore;

  public DecrementThread(Semaphore semaphore) { this.semaphore = semaphore; }

  public void run() {
    while (true) {
      System.out.println(Thread.currentThread().getName()
                         + " is waiting for permit");

      try {
        semaphore.acquire();
        System.out.println(Thread.currentThread().getName()
                           + " has got permit, new count is "
                           + semaphore.numTaken());

        for (int i = 0; i < 4; i++) {
          Thread.sleep(300);
          System.out.println(Thread.currentThread().getName()
                             + " is decrementing sharedData: "
                             + SemaphoreTest.sharedData--);
        }

      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      System.out.println(Thread.currentThread().getName()
                         + " has released permit");
      try {
        semaphore.release();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
