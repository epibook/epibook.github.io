package com.epi;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class LongestSubarrayWithDistinctEntries {
  // @include
  public static int longestSubarrayWithDistinctEntries(List<Integer> A) {
    // Records the most recent occurrences of each entry.
    Map<Integer, Integer> mostRecentOccurrence = new HashMap<>();
    int longestDupFreeSubarrayStartIdx = 0, result = 0;
    for (int i = 0; i < A.size(); ++i) {
      Integer dupIdx = mostRecentOccurrence.put(A.get(i), i);
      // A.get(i) appeared before. Did it appear in the longest current
      // subarray?
      if (dupIdx != null) {
        if (dupIdx >= longestDupFreeSubarrayStartIdx) {
          result = Math.max(result, i - longestDupFreeSubarrayStartIdx);
          longestDupFreeSubarrayStartIdx = dupIdx + 1;
        }
      }
    }
    result = Math.max(result, A.size() - longestDupFreeSubarrayStartIdx);
    return result;
  }
  // @exclude

  // O(n^2) checking solution.
  private static int checkAns(List<Integer> A) {
    int len = 0;
    for (int i = 0; i < A.size(); ++i) {
      Set<Integer> table = new HashSet<>();
      int j;
      for (j = i; A.size() - i > len && j < A.size(); ++j) {
        if (!table.add(A.get(j))) {
          break;
        }
      }
      len = Math.max(len, j - i);
    }
    return len;
  }

  private static void simpleTest() {
    assert(1 == longestSubarrayWithDistinctEntries(Arrays.asList(1, 1, 1)));
    assert(2 == longestSubarrayWithDistinctEntries(Arrays.asList(1, 2, 1)));
    assert(3 == longestSubarrayWithDistinctEntries(
                    Arrays.asList(1, 2, 1, 3, 1, 2, 1)));
    assert(2 == longestSubarrayWithDistinctEntries(
                    Arrays.asList(1, 2, 2, 3, 3, 1, 1, 2, 1)));
  }

  public static void main(String[] args) {
    simpleTest();
    Random r = new Random();
    int n;
    if (args.length == 1) {
      n = Integer.parseInt(args[0]);
    } else {
      n = r.nextInt(1001);
    }
    System.out.println("n = " + n);
    for (int times = 0; times < 1000; ++times) {
      List<Integer> A = new ArrayList<>(n);
      for (int i = 0; i < n; i++) {
        A.add(r.nextInt(1001));
      }
      // System.out.println(A);
      int ans = longestSubarrayWithDistinctEntries(A);
      int goldenAns = checkAns(A);
      System.out.println(ans + " " + goldenAns);
      assert(ans == goldenAns);
    }
  }
}
