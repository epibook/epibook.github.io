package com.epi;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.PriorityQueue;

public class MergeSortedArrays {
  // @include
  private static class ArrayEntry {
    public Integer value;
    public Integer arrayId;

    public ArrayEntry(Integer value, Integer arrayId) {
      this.value = value;
      this.arrayId = arrayId;
    }
  }

  private static final int DEFAULT_INITIAL_CAPACITY = 16;

  public static List<Integer> mergeSortedArrays(
      List<List<Integer>> sortedArrays) {
    PriorityQueue<ArrayEntry> minHeap = new PriorityQueue<>(
        DEFAULT_INITIAL_CAPACITY, new Comparator<ArrayEntry>() {
          @Override
          public int compare(ArrayEntry o1, ArrayEntry o2) {
            return o1.value.compareTo(o2.value);
          }
        });
    List<Integer> heads = new ArrayList<>(sortedArrays.size());

    // Puts each sortedArrays' first element in minHeap.
    for (int i = 0; i < sortedArrays.size(); ++i) {
      if (sortedArrays.get(i).size() > 0) {
        minHeap.add(new ArrayEntry(sortedArrays.get(i).get(0), i));
        heads.add(1);
      } else {
        heads.add(0);
      }
    }

    List<Integer> result = new ArrayList<>();
    while (!minHeap.isEmpty()) {
      int smallestEntry = minHeap.peek().value;
      List<Integer> smallestArray = sortedArrays.get(minHeap.peek().arrayId);
      int smallestArrayHead = heads.get(minHeap.peek().arrayId);
      result.add(smallestEntry);
      if (smallestArrayHead < smallestArray.size()) {
        // Add the next entry of smallestArray into minHeap.
        minHeap.add(new ArrayEntry(smallestArray.get(smallestArrayHead),
                                   minHeap.peek().arrayId));
        heads.set(minHeap.peek().arrayId, heads.get(minHeap.peek().arrayId) + 1);
      }
      minHeap.remove();
    }
    return result;
  }
  // @exclude

  private static void simpleTest() {
    List<List<Integer>> S = new ArrayList<>();
    S.add(Arrays.asList(1, 5, 10));
    S.add(Arrays.asList(2, 3, 100));
    S.add(Arrays.asList(2, 12, Integer.MAX_VALUE));

    List<Integer> ans = mergeSortedArrays(S);
    assert(9 == ans.size());
    assert(1 == ans.get(0));
    assert(2 == ans.get(1));
    assert(2 == ans.get(2));
    assert(3 == ans.get(3));
    assert(5 == ans.get(4));
    assert(10 == ans.get(5));
    assert(12 == ans.get(6));
    assert(100 == ans.get(7));
    assert(Integer.MAX_VALUE == ans.get(8));

    S = new ArrayList<>();
    S.add(Arrays.asList(1));
    ans = mergeSortedArrays(S);
    assert(1 == ans.size());
    assert(1 == ans.get(0));
  }

  public static void main(String[] args) {
    simpleTest();
    Random rnd = new Random();
    for (int times = 0; times < 100; ++times) {
      int n;
      if (args.length == 2) {
        n = Integer.parseInt(args[0]);
      } else {
        n = rnd.nextInt(100);
      }

      List<List<Integer>> S = new ArrayList<>();
      for (int i = 0; i < n; ++i) {
        int m = rnd.nextInt(500);
        List<Integer> last = new ArrayList<>(m);
        S.add(last);
        for (int j = 0; j < m; ++j) {
          last.add(rnd.nextInt(500));
        }
        Collections.sort(last);
      }

      List<Integer> ans = mergeSortedArrays(S);
      for (int i = 1; i < ans.size(); ++i) {
        assert(ans.get(i - 1) <= ans.get(i));
      }
    }
  }
}
