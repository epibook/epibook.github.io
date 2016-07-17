package com.epi;

import java.math.BigInteger;
import java.util.concurrent.TimeUnit;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Collatz {
  //@include
  // Performs basic unit of work, i.e., checking CH for an interval
  public static class MyRunnable implements Runnable {
    public int lower;
    public int upper;

    MyRunnable(int lower, int upper) {
      this.lower = lower;
      this.upper = upper;
    }

    @Override
    public void run() {
      for (int i = lower; i <= upper; ++i) {
        Collatz.CollatzCheck(i, new HashSet<BigInteger>());
      }
    }
  }

  // Checks an individual number
  public static boolean CollatzCheck(BigInteger aNum, Set<BigInteger> visited) {
    if (aNum.equals(BigInteger.ONE)) {
      return true;
    } else if (visited.contains(aNum)) {
      return false;
    }
    visited.add(aNum);
    if (aNum.getLowestSetBit() == 1) { // Odd number.
      return CollatzCheck(
          new BigInteger("3").multiply(aNum).add(BigInteger.ONE), visited);
    } else { // Even number.
      return CollatzCheck(aNum.shiftRight(1), visited); // Divide by 2.
    }
  }

  public static boolean CollatzCheck(int aNum, Set<BigInteger> visited) {
    BigInteger b = new BigInteger(new Integer(aNum).toString());
    return CollatzCheck(b, visited);
  }

  // @exclude
  static int N = 10000000;
  static int RANGESIZE = 1000000;
  static int NTHREADS = 4;

  static void parseArgs(String[] args) {
    if (args.length >= 1) {
      N = Integer.parseInt(args[0]);
    }
    if (args.length >= 2) {
      RANGESIZE = Integer.parseInt(args[1]);
    }
    if (args.length >= 3) {
      NTHREADS = Integer.parseInt(args[2]);
    }
  }

  public static void maintest(String[] args) {
    System.out.println(
        "CollatzCheck(1): "
        + CollatzCheck(new BigInteger("1"), new HashSet<BigInteger>()));
    System.out.println(
        "CollatzCheck(3): "
        + CollatzCheck(new BigInteger("3"), new HashSet<BigInteger>()));
    System.out.println(
        "CollatzCheck(8): "
        + CollatzCheck(new BigInteger("8"), new HashSet<BigInteger>()));
    parseArgs(args);
  }

  //@include
  public static ExecutorService execute() {
    // Uses the Executor framework for task assignment and load balancing
    List<Thread> threads = new ArrayList<Thread>();
    ExecutorService executor = Executors.newFixedThreadPool(NTHREADS);
    for (int i = 0; i < (N / RANGESIZE); ++i) {
      Runnable worker = new MyRunnable(i * RANGESIZE + 1, (i + 1) * RANGESIZE);
      executor.execute(worker);
    }
    executor.shutdown();
    return executor;
  }
  //@exclude

  public static void main(String[] args) {
    long lDateTime = new Date().getTime();
    parseArgs(args);

    ExecutorService executor = execute();

    // while (!executor.isTerminated() ) {
    // }
    try {
      while (!executor.awaitTermination(1, TimeUnit.SECONDS)) {
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    System.out.println("Finished all threads");
    long fDateTime = new Date().getTime();
    System.out.println("time in milliseconds for checking to " + N + " is "
                       + (fDateTime - lDateTime) + " ("
                       + N / (fDateTime - lDateTime) + " per ms)");
  }
}
