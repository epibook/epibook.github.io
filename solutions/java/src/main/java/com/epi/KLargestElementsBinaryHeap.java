// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

package com.epi;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class KLargestElementsBinaryHeap {
  // @include
  private static class HeapEntry {
    public Integer index;
    public Integer value;

    public HeapEntry(Integer index, Integer value) {
      this.index = index;
      this.value = value;
    }
  }

  private static class Compare implements Comparator<HeapEntry> {
    @Override
    public int compare(HeapEntry o1, HeapEntry o2) {
      return o2.value.compareTo(o1.value);
    }
    public static final Compare COMPARE_HEAP_ENTRYS = new Compare();
  }

  public static List<Integer> kLargestInBinaryHeap(int[] A, int k) {
    if (k <= 0) {
      return new ArrayList<>();
    }

    // Stores the (index, value)-pair in candidateMaxHeap. This heap is
    // ordered by the value field.
    PriorityQueue<HeapEntry> candidateMaxHeap =
        new PriorityQueue<>(11, Compare.COMPARE_HEAP_ENTRYS);
    candidateMaxHeap.add(new HeapEntry(0, A[0]));
    List<Integer> result = new ArrayList<>();
    for (int i = 0; i < k; ++i) {
      Integer candidateIdx = candidateMaxHeap.peek().index;
      result.add(candidateMaxHeap.remove().value);

      Integer leftChildIdx = 2 * candidateIdx + 1;
      if (leftChildIdx < A.length) {
        candidateMaxHeap.add(new HeapEntry(leftChildIdx, A[leftChildIdx]));
      }
      Integer rightChildIdx = 2 * candidateIdx + 2;
      if (rightChildIdx < A.length) {
        candidateMaxHeap.add(new HeapEntry(rightChildIdx, A[rightChildIdx]));
      }
    }
    return result;
  }
  // @exclude

  public static void main(String[] args) {
    int[] maxHeap = new int[] {10, 2, 9, 2, 2, 8, 8, 2, 2, 2, 2, 7, 7, 7, 7};
    List<Integer> result = kLargestInBinaryHeap(maxHeap, 3);
    List<Integer> expectedResult = Arrays.asList(10, 9, 8);
    assert(result.equals(expectedResult));
    result = kLargestInBinaryHeap(maxHeap, 5);
    expectedResult = Arrays.asList(10, 9, 8, 8, 7);
    assert(result.equals(expectedResult));

    maxHeap = new int[] {97, 84, 93, 83, 81, 90, 79, 83, 55, 42, 21, 73};
    result = kLargestInBinaryHeap(maxHeap, 3);
    expectedResult = Arrays.asList(97, 93, 90);
    assert(result.equals(expectedResult));

    maxHeap = new int[] {100, 1, 5, 0, 0};
    result = kLargestInBinaryHeap(maxHeap, 1);
    expectedResult = Arrays.asList(100);
    assert(result.equals(expectedResult));
    result = kLargestInBinaryHeap(maxHeap, 2);
    assert(result.equals(Arrays.asList(100, 5)));
    result = kLargestInBinaryHeap(maxHeap, 3);
    assert(result.equals(Arrays.asList(100, 5, 1)));
  }
}
