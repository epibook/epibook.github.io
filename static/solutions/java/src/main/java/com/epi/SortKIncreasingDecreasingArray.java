package com.epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class SortKIncreasingDecreasingArray {
  // @include
  public static List<Integer> sortKIncreasingDecreasingArray(List<Integer> A) {
    // Decomposes A into a set of sorted arrays.
    List<List<Integer>> sortedSubarrays = new ArrayList<>();
    SubarrayType subarrayType = SubarrayType.INCREASING;
    int startIdx = 0;
    for (int i = 1; i <= A.size(); ++i) {
      if (i == A.size() // A is ended. Adds the last subarray
          || (A.get(i - 1) < A.get(i)
              && subarrayType == SubarrayType.DECREASING)
          || (A.get(i - 1) >= A.get(i)
              && subarrayType == SubarrayType.INCREASING)) {
        List<Integer> subList = A.subList(startIdx, i);
        if (subarrayType == SubarrayType.DECREASING) {
          Collections.reverse(subList);
        }
        sortedSubarrays.add(subList);
        startIdx = i;
        subarrayType = (subarrayType == SubarrayType.INCREASING
                            ? SubarrayType.DECREASING
                            : SubarrayType.INCREASING);
      }
    }
    return MergeSortedArrays.mergeSortedArrays(sortedSubarrays);
  }

  private static enum SubarrayType { INCREASING, DECREASING }
  // @exclude

  private static void simpleTest() {
    List<Integer> A = Arrays.asList(1, 2, 3, 2, 1, 4, 5, 10, 9, 4, 4, 1, -1);
    List<Integer> Adup = new ArrayList<>(A);
    List<Integer> ans = sortKIncreasingDecreasingArray(A);
    assert(Adup.size() == ans.size());
    Collections.sort(Adup);
    assert(Adup.equals(ans));

    A = Arrays.asList(Integer.MIN_VALUE, -1, 0, 1, 2, 4, 8, Integer.MAX_VALUE);
    Adup = new ArrayList<>(A);
    ans = sortKIncreasingDecreasingArray(A);
    assert(Adup.size() == ans.size());
    assert(Adup.equals(ans));

    A = Arrays.asList(Integer.MIN_VALUE, -1, 0, 1, 2, 4, 8, Integer.MAX_VALUE);
    Adup = new ArrayList<>(A);
    Collections.reverse(A);
    ans = sortKIncreasingDecreasingArray(A);
    assert(Adup.size() == ans.size());
    assert(Adup.equals(ans));
  }

  public static void main(String[] args) {
    simpleTest();
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

      List<Integer> Adup = new ArrayList<>(A);
      List<Integer> ans = sortKIncreasingDecreasingArray(A);
      assert(A.size() == ans.size());
      Collections.sort(Adup);
      assert(Adup.equals(ans));
    }
  }
}
