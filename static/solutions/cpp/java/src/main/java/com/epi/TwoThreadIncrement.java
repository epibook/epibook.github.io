package com.epi;

public class TwoThreadIncrement {
  //@include
  public static class IncrementThread implements Runnable {
    public void run() {
      for (int i = 0; i < TwoThreadIncrementDriver.N; i++) {
        TwoThreadIncrementDriver.counter++;
      }
    }
  }

  public static class TwoThreadIncrementDriver {
    public static int counter;
    public static int N;

    public static void main(String[] args) throws Exception {
      N = (args.length > 0) ? new Integer(args[0]) : 100;

      Thread t1 = new Thread(new IncrementThread());
      Thread t2 = new Thread(new IncrementThread());

      t1.start();
      t2.start();
      t1.join();
      t2.join();

      System.out.println(counter);
    }
  }
  //@exclude
}
