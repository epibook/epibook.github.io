package com.epi;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Iterator;
import java.util.Random;

public class KthLargestElementLargeN {
  // @include
  public static int findKthLargestUnknownLength(Iterator<Integer> sequence,
                                                int k) {
    ArrayList<Integer> candidates = new ArrayList<>(2 * k - 1);
    int idx = 0;
    while (sequence.hasNext()) {
      int x = sequence.next();
      idx++;
      candidates.add(x);
      if (idx == 2 * k - 1) {
        // Reorders elements about median with larger elements appearing before
        // the median.
        OrderStatistic.findKthLargest(candidates, k);
        // Reset idx to keep just the k largest elements seen so far.
        idx = k;
      }
    }
    // Finds the k-th largest element in candidates.
    OrderStatistic.findKthLargest(candidates, k);
    return candidates.get(k - 1);
  }
  // @exclude

  private static void simpleTestArray(List<Integer> a) {
    for (int i = 0; i < a.size(); i++) {
      System.out.println("i = " + i);
      int result = findKthLargestUnknownLength(a.iterator(), i + 1);
      List<Integer> aSort = new ArrayList<Integer>(a);
      Collections.sort(aSort);
      assert(result == aSort.get((aSort.size() - 1) - i));
    }
  }

  private static void simpleTest() {
    List<Integer> A = Arrays.asList(5, 6, 2, 1, 3, 0, 4);
    simpleTestArray(A);
    A = Arrays.asList(5, -1, 2, 1, 3, 1, 4, Integer.MAX_VALUE, 5);
    simpleTestArray(A);
    int N = 1000;
    A = new ArrayList<>(N);
    Random r = new Random();
    for (int i = 0; i < A.size(); ++i) {
      A.add(r.nextInt(10));
    }
    simpleTestArray(A);
    for (int i = 0; i < A.size(); ++i) {
      A.set(i, r.nextInt(100000000));
    }
    simpleTestArray(A);
  }

  public static void main(String[] args) {
    simpleTest();
    Random r = new Random();
    for (int times = 0; times < 1000; ++times) {
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
      List<Integer> a = new ArrayList<>();
      for (int i = 0; i < n; ++i) {
        a.add(r.nextInt(10000000));
      }
      int result = findKthLargestUnknownLength(a.iterator(), k);

      ArrayList<Integer> aCopy = new ArrayList<>(a);

      OrderStatistic.findKthLargest(aCopy, k);
      System.out.println(result);
      System.out.println(aCopy.get(k - 1));
      assert(aCopy.get(k - 1) == result);
    }
  }
}
