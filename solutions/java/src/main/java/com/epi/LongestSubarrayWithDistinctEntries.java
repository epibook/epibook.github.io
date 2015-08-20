package com.epi;

import java.util.*;

public class LongestSubarrayWithDistinctEntries {
  // @include
  public static int longestSubarrayWithDistinctEntries(int[] A) {
    // Records the most recent occurrences of each entry.
    Map<Integer, Integer> mostRecentOccurrence = new HashMap<>();
    int longestDupFreeSubarrayStartIdx = 0, result = 0;
    for (int i = 0; i < A.length; ++i) {
      Integer dupIdx = mostRecentOccurrence.put(A[i], i);
      // A[i] appeared before. Did it appear in the longest current subarray?
      if (dupIdx != null) {
        if (dupIdx >= longestDupFreeSubarrayStartIdx) {
          result = Math.max(result, i - longestDupFreeSubarrayStartIdx);
          longestDupFreeSubarrayStartIdx = dupIdx + 1;
        }
      }
    }
    result = Math.max(result, A.length - longestDupFreeSubarrayStartIdx);
    return result;
  }
  // @exclude

  // O(n^2) checking solution.
  private static int checkAns(int[] A) {
    int len = 0;
    for (int i = 0; i < A.length; ++i) {
      Set<Integer> table = new HashSet<>();
      int j;
      for (j = i; A.length - i > len && j < A.length; ++j) {
        if (!table.add(A[j])) {
          break;
        }
      }
      len = Math.max(len, j - i);
    }
    return len;
  }

  private static void SimpleTest() {
    assert(1 == longestSubarrayWithDistinctEntries(new int[]{1,1,1}));
    assert(2 == longestSubarrayWithDistinctEntries(new int[]{1,2,1}));
    assert(3 == longestSubarrayWithDistinctEntries(new int[]{1,2,1,3,1,2,1}));
    assert(2 == longestSubarrayWithDistinctEntries(new int[]{1,2,2,3,3,1,1,2,1}));
  }

  public static void main(String[] args) {
    SimpleTest();
    Random r = new Random();
    int n;
    if (args.length == 1) {
      n = Integer.parseInt(args[0]);
    } else {
      n = r.nextInt(1001);
    }
    System.out.println("n = " + n);
    for (int times = 0; times < 1000; ++times) {
      int[] A = new int[n];
      for (int i = 0; i < n; i++) {
        A[i] = r.nextInt(1001);
      }
      // System.out.println(A);
      int ans = longestSubarrayWithDistinctEntries(A);
      int goldenAns = checkAns(A);
      System.out.println(ans + " " + goldenAns);
      assert(ans == goldenAns);
    }
  }
}
