package com.epi;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.TreeSet;

import com.epi.utils.Utils;

public class ApproximateSort {
  // Used only for testing.
  private static List<Integer> result = null;

  // @include
  public static void sortApproximatelySortedData(InputStream sequence, int k)
      throws ClassNotFoundException, IOException {
    // @exclude
    result = new ArrayList<>();
    // @include
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
        Integer smallest = minHeap.remove();
        System.out.println(smallest);
        // @exclude
        result.add(smallest);
        // @include
      }
    } catch (EOFException e) {
      // Do nothing since the end of the stream has been reached.
    }

    // sequence is exhausted, iteratively extracts the remaining elements.
    while (!minHeap.isEmpty()) {
      Integer smallest = minHeap.remove();
      System.out.println(smallest);
      // @exclude
      result.add(smallest);
      // @include
    }
  }
  // @exclude

  // It should return 1, 2, 3, 4, 5, 6, 7, 8, 9.
  private static void simpleTest() throws ClassNotFoundException {
    List<Integer> A = Arrays.asList(2, 1, 5, 4, 3, 9, 8, 7, 6);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    try {
      ObjectOutputStream oos = new ObjectOutputStream(baos);
      for (Integer a : A) {
        oos.writeObject(a);
      }
      ByteArrayInputStream sequence
          = new ByteArrayInputStream(baos.toByteArray());
      sortApproximatelySortedData(sequence, 3);
    } catch (IOException e) {
      System.out.println("IOException: " + e.getMessage());
    }
    assert(result.equals(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9)));
  }

  public static void main(String[] args) throws ClassNotFoundException {
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
    List<Integer> stream = new ArrayList<>();
    try {
      ObjectOutputStream oos = new ObjectOutputStream(baos);
      for (Integer a : A) {
        oos.writeObject(a);
        stream.add(a);
      }
      ByteArrayInputStream sequence
          = new ByteArrayInputStream(baos.toByteArray());
      sortApproximatelySortedData(sequence, n - 1);
      // Check result is sorted.
      List<Integer> tmp = new ArrayList<>(result);
      Collections.sort(tmp);
      assert(result.equals(tmp));
      // Check result contains stream entries.
      assert(new TreeSet<>(result).equals(new TreeSet<>(stream)));
    } catch (IOException e) {
      System.out.println("IOException: " + e.getMessage());
    }
  }
}
