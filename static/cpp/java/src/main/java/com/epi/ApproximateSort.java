package com.epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.TreeSet;

import com.epi.utils.Utils;

public class ApproximateSort {
  // Used only for testing.
  private static List<Integer> result = null;

  // @include
  public static void sortApproximatelySortedData(Iterator<Integer> sequence,
                                                 int k) {
    // @exclude
    result = new ArrayList<>();
    // @include
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    // Adds the first k elements into minHeap. Stop if there are fewer than k
    // elements.
    for (int i = 0; i < k && sequence.hasNext(); ++i) {
      minHeap.add(sequence.next());
    }

    // For every new element, add it to minHeap and extract the smallest.
    while (sequence.hasNext()) {
      minHeap.add(sequence.next());
      Integer smallest = minHeap.remove();
      System.out.println(smallest);
      // @exclude
      result.add(smallest);
      // @include
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
  private static void simpleTest() {
    List<Integer> A = Arrays.asList(2, 1, 5, 4, 3, 9, 8, 7, 6);
    sortApproximatelySortedData(A.iterator(), 3);
    assert(result.equals(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9)));
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
    sortApproximatelySortedData(A.iterator(), n - 1);
    // Check result is sorted.
    List<Integer> tmp = new ArrayList<>(result);
    Collections.sort(tmp);
    assert(result.equals(tmp));
    // Check result contains stream entries.
    assert(new TreeSet<>(result).equals(new TreeSet<>(A)));
  }
}
