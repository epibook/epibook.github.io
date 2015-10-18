package com.epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

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
            return Integer.compare(o1.value, o2.value);
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
    ArrayEntry headEntry;
    while ((headEntry = minHeap.poll()) != null) {
      result.add(headEntry.value);
      List<Integer> smallestArray = sortedArrays.get(headEntry.arrayId);
      int smallestArrayHead = heads.get(headEntry.arrayId);
      if (smallestArrayHead < smallestArray.size()) {
        // Add the next entry of smallestArray into minHeap.
        minHeap.add(new ArrayEntry(smallestArray.get(smallestArrayHead),
                                   headEntry.arrayId));
        heads.set(headEntry.arrayId, heads.get(headEntry.arrayId) + 1);
      }
    }
    return result;
  }
  // @exclude

  private static void simpleTest() {
    List<List<Integer>> S
        = Arrays.asList(Arrays.asList(1, 5, 10), Arrays.asList(2, 3, 100),
                        Arrays.asList(2, 12, Integer.MAX_VALUE));
    List<Integer> ans = mergeSortedArrays(S);
    List<Integer> golden
        = Arrays.asList(1, 2, 2, 3, 5, 10, 12, 100, Integer.MAX_VALUE);
    assert(9 == ans.size() && ans.equals(golden));

    S = Arrays.asList(Arrays.asList(1));
    ans = mergeSortedArrays(S);
    assert(1 == ans.size() && 1 == ans.get(0));

    S = Arrays.asList(new ArrayList<Integer>(), Arrays.asList(1),
                      Arrays.asList(2));
    ans = mergeSortedArrays(S);
    assert(2 == ans.size() && ans.equals(Arrays.asList(1, 2)));
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
