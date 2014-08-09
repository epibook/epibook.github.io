package com.epi;

import java.io.*;
import java.util.*;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class ApproximateSort {
  // @include
  public static void approximateSort(InputStream sin, int k) {
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    try {
      ObjectInputStream osin = new ObjectInputStream(sin);
      // Firstly pushes k elements into minHeap.
      for (int i = 0; i < k; ++i) {
        minHeap.add((Integer) osin.readObject());
      }

      // Extracts the minimum one for every incoming element.
      while (true) {
        minHeap.add((Integer) osin.readObject());
        System.out.println(minHeap.remove());
      }
    } catch (IOException e) {
      // Do nothing, was read last element in stream
    } catch (ClassNotFoundException e) {
      System.out.println("ClassNotFoundException: " + e.getMessage());
    }

    // Extracts the remaining elements in minHeap.
    while (!minHeap.isEmpty()) {
      System.out.println(minHeap.remove());
    }
  }
  // @exclude

  // It should print 1, 2, 3, 4, 5, 6, 7, 8, 9.
  private static void simpleTest() {
    List<Integer> A = Arrays.asList(2, 1, 5, 4, 3, 9, 8, 7, 6);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    try {
      ObjectOutputStream oos = new ObjectOutputStream(baos);
      for (Integer a : A) {
        oos.writeObject(a);
      }
      ByteArrayInputStream sin = new ByteArrayInputStream(baos.toByteArray());
      approximateSort(sin, 3);
    } catch (IOException e) {
      System.out.println("IOException: " + e.getMessage());
    }
  }

  public static void main(String[] args) {
    simpleTest();
    Random r = new Random();
    int n, k;
    if (args.length == 1) {
      n = Integer.parseInt(args[0]);
      k = r.nextInt(n) + 1;
    } else if (args.length == 2) {
      n = Integer.parseInt(args[0]);
      k = Integer.parseInt(args[1]);
    } else {
      n = r.nextInt(100000) + 1;
      k = r.nextInt(n) + 1;
    }
    System.out.println("n = " + n + " k = " + k);
    List<Integer> A = new ArrayList<>();
    for (int i = 0; i < n; ++i) {
      A.add(r.nextInt(999999) + 1);
    }
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    try {
      ObjectOutputStream oos = new ObjectOutputStream(baos);
      for (Integer a : A) {
        oos.writeObject(a);
      }
      ByteArrayInputStream sin = new ByteArrayInputStream(baos.toByteArray());
      approximateSort(sin, n - 1);
    } catch (IOException e) {
      System.out.println("IOException: " + e.getMessage());
    }
  }
}
