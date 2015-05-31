package com.epi;

import java.io.*;
import java.util.*;

public class ApproximateSort {
  // @include
  public static void sortApproximatelySortedArray(InputStream sequence, int k) {
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    try {
      ObjectInputStream osin = new ObjectInputStream(sequence);
      // Adds the first k elements into minHeap. Stop if there are fewer than
      // k elements.
      for (int i = 0; i < k; ++i) {
        minHeap.add((Integer)osin.readObject());
      }

      // For every new element, add it to minHeap and extract the smallest.
      while (true) {
        minHeap.add((Integer)osin.readObject());
        System.out.println(minHeap.remove());
      }
    } catch (IOException e) {
      // Do nothing, was read last element in stream
    } catch (ClassNotFoundException e) {
      System.out.println("ClassNotFoundException: " + e.getMessage());
    }

    // sequence is exhausted, iteratively extracts the remaining elements.
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
      ByteArrayInputStream sequence =
          new ByteArrayInputStream(baos.toByteArray());
      sortApproximatelySortedArray(sequence, 3);
    } catch (IOException e) {
      System.out.println("IOException: " + e.getMessage());
    }
  }

  public static void main(String[] args) {
    simpleTest();
    Random r = new Random();
    int n;
    if (args.length == 1) {
      n = Integer.parseInt(args[0]);
    } else {
      n = r.nextInt(100000) + 1;
    }
    System.out.println("n = " + n);
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
      ByteArrayInputStream sequence =
          new ByteArrayInputStream(baos.toByteArray());
      sortApproximatelySortedArray(sequence, n - 1);
    } catch (IOException e) {
      System.out.println("IOException: " + e.getMessage());
    }
  }
}
