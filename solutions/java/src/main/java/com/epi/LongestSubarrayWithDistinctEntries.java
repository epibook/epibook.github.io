package com.epi;

import java.util.*;

public class LongestSubarrayWithDistinctEntries {
  // @include
  public static int longestSubarrayWithDistinctEntries(int[] A) {
    // Records the last occurrences of each entry.
    Map<Integer, Integer> lastOccurrence = new HashMap<>();
    int startingIdx = 0, ans = 0;
    for (int i = 0; i < A.length; ++i) {
      Integer result = lastOccurrence.put(A[i], i);
      if (result != null) { // A[i] appeared before. Check its validity.
        if (result >= startingIdx) {
          ans = Math.max(ans, i - startingIdx);
          startingIdx = result + 1;
        }
      }
    }
    ans = Math.max(ans, A.length - startingIdx);
    return ans;
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

  public static void main(String[] args) {
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
      assert (ans == goldenAns);
    }
  }
}
