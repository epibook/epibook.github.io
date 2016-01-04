package com.epi;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class CompareKthLargestInHeap {
  // @include
  private static class Counts {
    public int largerX = 0;
    public int equalX = 0;
  }

  public static enum Ordering { SMALLER, EQUAL, LARGER }

  public static Ordering compareKthLargestHeap(List<Integer> maxHeap, int k,
                                               int x) {
    Counts counts = new Counts();
    compareKthLargestHeapHelper(maxHeap, k, x, 0, counts);
    return counts.largerX >= k
        ? Ordering.LARGER
        : (counts.largerX + counts.equalX >= k ? Ordering.EQUAL
                                               : Ordering.SMALLER);
  }

  private static void compareKthLargestHeapHelper(List<Integer> maxHeap, int k,
                                                  int x, int idx,
                                                  Counts counts) {
    // Check early termination.  Note that we cannot early terminate for
    // counts.equalX >= k because counts.largerX (which is currently smaller
    // than k) may still become >= k.
    if (counts.largerX >= k || idx >= maxHeap.size() || maxHeap.get(idx) < x) {
      return;
    } else if (maxHeap.get(idx) == x) {
      if (++counts.equalX >= k) {
        return;
      }
    } else { // maxHeap.get(idx) > x.
      ++counts.largerX;
    }
    compareKthLargestHeapHelper(maxHeap, k, x, 2 * idx + 1, counts);
    compareKthLargestHeapHelper(maxHeap, k, x, 2 * idx + 2, counts);
  }
  // @exclude

  public static void main(String[] args) {
    // 5
    // 4 5
    // 4 4 4 3
    // 4
    List<Integer> maxHeap = Arrays.asList(5, 4, 5, 4, 4, 4, 3, 4);
    int k, x;
    if (args.length == 2) {
      k = Integer.parseInt(args[0]);
      x = Integer.parseInt(args[1]);
      Ordering res = compareKthLargestHeap(maxHeap, k, x);
      System.out.println(res);
    } else {
      assert(Ordering.SMALLER
             == compareKthLargestHeap(maxHeap, 1, 6)); // expect smaller
      assert(Ordering.EQUAL
             == compareKthLargestHeap(maxHeap, 1, 5)); // expect equal
      assert(Ordering.EQUAL
             == compareKthLargestHeap(maxHeap, 6, 4)); // expect equal
      assert(Ordering.EQUAL
             == compareKthLargestHeap(maxHeap, 3, 4)); // expect equal
      assert(Ordering.SMALLER
             == compareKthLargestHeap(maxHeap, 8, 4)); // expect smaller
      assert(Ordering.LARGER
             == compareKthLargestHeap(maxHeap, 2, 4)); // expect larger
      assert(Ordering.LARGER
             == compareKthLargestHeap(maxHeap, 2, 3)); // expect larger
    }
  }
}
