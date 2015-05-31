// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

package com.epi;

import com.epi.utils.Pair;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class KLargestElementsBinaryHeap {
  // @include
  private static class Compare implements Comparator<Pair<Integer, Integer>> {
    @Override
    public int compare(Pair<Integer, Integer> o1, Pair<Integer, Integer> o2) {
      return o2.getSecond().compareTo(o1.getSecond());
    }
  }

  public static List<Integer> kLargestInBinaryHeap(int[] A, int k) {
    if (k <= 0) {
      return new ArrayList<>();
    }

    // Stores the (index, value)-pair in candidateMaxHeap. This heap is
    // ordered by value field.
    PriorityQueue<Pair<Integer, Integer>> candidateMaxHeap =
        new PriorityQueue<>(11, new Compare());
    candidateMaxHeap.add(new Pair<>(0, A[0]));
    List<Integer> result = new ArrayList<>();
    for (int i = 0; i < k; ++i) {
      Integer candidateIdx = candidateMaxHeap.peek().getFirst();
      result.add(candidateMaxHeap.remove().getSecond());

      Integer leftChildIdx = 2 * candidateIdx + 1;
      if (leftChildIdx < A.length) {
        candidateMaxHeap.add(new Pair<>(leftChildIdx, A[leftChildIdx]));
      }
      Integer rightChildIdx = 2 * candidateIdx + 2;
      if (rightChildIdx < A.length) {
        candidateMaxHeap.add(new Pair<>(rightChildIdx, A[rightChildIdx]));
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
  }
}
