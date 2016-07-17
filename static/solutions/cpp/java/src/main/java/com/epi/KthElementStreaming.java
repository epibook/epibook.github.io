package com.epi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Scanner;

public class KthElementStreaming {
  // @include
  public static void findKthLargestStream(Iterator<Integer> sequence, int k) {
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    // The first k elements, output the minimum element.
    for (int i = 0; i < k && sequence.hasNext(); ++i) {
      int x = sequence.next();
      minHeap.add(x);
      System.out.println(minHeap.peek());
    }

    // After the first k elements, output the k-th largest one.
    while (sequence.hasNext()) {
      int x = sequence.next();
      if (minHeap.peek() < x) {
        minHeap.remove();
        minHeap.add(x);
      }
      System.out.println(minHeap.peek());
    }
  }
  // @exclude

  public static void main(String[] args) {
    Random r = new Random();
    int num, k;
    if (args.length == 1) {
      num = Integer.parseInt(args[0]);
      k = r.nextInt(num) + 1;
    } else if (args.length == 2) {
      num = Integer.parseInt(args[0]);
      k = Integer.parseInt(args[1]);
    } else {
      num = r.nextInt(101);
      k = r.nextInt(num) + 1;
    }
    List<Integer> stream = new ArrayList<>();
    for (int i = 0; i < num; ++i) {
      stream.add(r.nextInt(10000000));
    }
    Collections.sort(stream);
    findKthLargestStream(stream.iterator(), k);
    System.out.println("n = " + num + ", k = " + k);
  }
}
