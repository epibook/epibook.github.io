package com.epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Scanner;

public class OnlineMedian {
  private static List<Double> globalResult = new ArrayList<>();

  // @include
  private static final int DEFAULT_INITIAL_CAPACITY = 16;

  public static void onlineMedian(Iterator<Integer> sequence) {
    // minHeap stores the larger half seen so far.
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    // maxHeap stores the smaller half seen so far.
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(
        DEFAULT_INITIAL_CAPACITY, Collections.reverseOrder());

    while (sequence.hasNext()) {
      int x = sequence.next();
      if (minHeap.isEmpty()) {
        // This is the very first element.
        minHeap.add(x);
      } else {
        if (x >= minHeap.peek()) {
          minHeap.add(x);
        } else {
          maxHeap.add(x);
        }
      }
      // Ensure minHeap and maxHeap have equal number of elements if
      // an even number of elements is read; otherwise, minHeap must have
      // one more element than maxHeap.
      if (minHeap.size() > maxHeap.size() + 1) {
        maxHeap.add(minHeap.remove());
      } else if (maxHeap.size() > minHeap.size()) {
        minHeap.add(maxHeap.remove());
      }

      // @exclude
      globalResult.add((minHeap.size() == maxHeap.size()
                            ? 0.5 * (minHeap.peek() + maxHeap.peek())
                            : minHeap.peek()));
      // @include
      System.out.println(minHeap.size() == maxHeap.size()
                             ? 0.5 * (minHeap.peek() + maxHeap.peek())
                             : minHeap.peek());
    }
  }
  // @exclude

  private static void smallTest() {
    List<Integer> stream = Arrays.asList(5, 4, 3, 2, 1);
    onlineMedian(stream.iterator());
    List<Double> golden = Arrays.asList(5.0, 4.5, 4.0, 3.5, 3.0);
    assert golden.equals(globalResult);

    globalResult.clear();
    stream = Arrays.asList(1, 2, 3, 4, 5);
    onlineMedian(stream.iterator());
    golden = Arrays.asList(1.0, 1.5, 2.0, 2.5, 3.0);
    assert golden.equals(globalResult);

    globalResult.clear();
    stream = Arrays.asList(1, 0, 3, 5, 2, 0, 1);
    onlineMedian(stream.iterator());
    golden = Arrays.asList(1.0, 0.5, 1.0, 2.0, 2.0, 1.5, 1.0);
    assert golden.equals(globalResult);

    globalResult.clear();
    stream = Arrays.asList(-1);
    onlineMedian(stream.iterator());
    golden = Arrays.asList(-1.0);
    assert golden.equals(globalResult);
  }

  public static void main(String[] args) {
    smallTest();
    Random r = new Random();
    int num;
    if (args.length == 1) {
      num = Integer.parseInt(args[0]);
    } else {
      num = r.nextInt(100000) + 1;
    }
    List<Integer> stream = new ArrayList<>();
    for (int i = 0; i < num; ++i) {
      stream.add(r.nextInt(10000) + 1);
    }
    onlineMedian(stream.iterator());
  }
}
