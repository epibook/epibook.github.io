package com.epi;

import com.epi.utils.Pair;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class MergeSortedArrays {
  // @include
  public static ArrayList<Integer> mergeArrays(List<List<Integer>> S) {
    PriorityQueue<Pair<Integer, Integer>> minHeap = new PriorityQueue<>(
        11, new Comparator<Pair<Integer, Integer>>() {
      @Override
      public int compare(Pair<Integer, Integer> o1,
                         Pair<Integer, Integer> o2) {
        return o1.getFirst().compareTo(o2.getFirst());
      }
    }
    );
    ArrayList<Integer> sIdx = new ArrayList<>(S.size());

    // Every array in S puts its smallest element in heap.
    for (int i = 0; i < S.size(); ++i) {
      if (S.get(i).size() > 0) {
        minHeap.add(new Pair<>(S.get(i).get(0), i));
        sIdx.add(1);
      } else {
        sIdx.add(0);
      }
    }

    ArrayList<Integer> ret = new ArrayList<>();
    while (!minHeap.isEmpty()) {
      Pair<Integer, Integer> p = minHeap.remove();
      ret.add(p.getFirst());
      // Add the smallest element into heap if possible.
      if (sIdx.get(p.getSecond()) < S.get(p.getSecond()).size()) {
        minHeap.add(new Pair<>(S.get(p.getSecond()).get(
            sIdx.get(p.getSecond())), p.getSecond()));
        sIdx.set(p.getSecond(), sIdx.get(p.getSecond()) + 1);
      }
    }
    return ret;
  }
  // @exclude
}
