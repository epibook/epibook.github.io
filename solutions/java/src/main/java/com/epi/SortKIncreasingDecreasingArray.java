package com.epi;

import com.epi.utils.Pair;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.PriorityQueue;

public class SortKIncreasingDecreasingArray {
  // @include
  public static List<Integer> sortKIncreasingDecreasingArray(List<Integer> A) {
    // Decomposes A into a set of sorted arrays.
    List<List<Integer>> sortedSubarrays = new ArrayList<>();
    SubarrayType subarrayType = SubarrayType.INCREASING;
    int startIdx = 0;
    for (int i = 1; i <= A.size(); ++i) {
      if (i == A.size() // A is ended. Adds the last subarray
          ||
          (A.get(i - 1) < A.get(i) && subarrayType == SubarrayType.DECREASING) ||
          (A.get(i - 1) >= A.get(i) && subarrayType == SubarrayType.INCREASING)) {
        List<Integer> subList = A.subList(startIdx, i);
        if (subarrayType == SubarrayType.DECREASING) {
          Collections.reverse(subList);
        }
        sortedSubarrays.add(subList);
        startIdx = i;
        subarrayType =
            (subarrayType == SubarrayType.INCREASING ? SubarrayType.DECREASING
                                                     : SubarrayType.INCREASING);
      }
    }

    return MergeSortedArrays.mergeSortedArrays(sortedSubarrays);
  }

  private static enum SubarrayType { INCREASING, DECREASING }
  // @exclude

  public static <T extends Comparable<T>> boolean isSorted(Iterable<T> iterable) {
    Iterator<T> iter = iterable.iterator();
    if (!iter.hasNext()) {
      return true;
    }
    T t = iter.next();
    while (iter.hasNext()) {
      T t2 = iter.next();
      if (t.compareTo(t2) > 0) {
        return false;
      }
      t = t2;
    }
    return true;
  }

  public static void main(String[] args) {
    Random rnd = new Random();
    for (int times = 0; times < 100; ++times) {
      int n;
      if (args.length == 2) {
        n = Integer.parseInt(args[0]);
      } else {
        n = 1 + rnd.nextInt(10);
      }

      List<Integer> A = new ArrayList<>();
      for (int i = 0; i < n; ++i) {
        A.add(rnd.nextInt(999999));
      }

      List<Integer> ans = sortKIncreasingDecreasingArray(A);
      assert(A.size() == ans.size());
      assert(isSorted(ans));
    }
  }
}
