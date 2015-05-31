package com.epi;

import com.epi.utils.Pair;

import java.util.*;

public class LongestContainedRange {
  private static int checkAns(int[] A) {
    Arrays.sort(A);
    int result = 1;
    int pre = A[0], len = 1;
    for (int i = 1; i < A.length; ++i) {
      if (A[i] == pre + 1) {
        ++len;
      } else if (A[i] != pre) {
        result = Math.max(result, len);
        len = 1;
      }
      pre = A[i];
    }
    result = Math.max(result, len);
    System.out.println(result);
    return result;
  }

  private static int findLongestContainedRangeInt(int[] A) {
    if (A.length == 0) {
      return 0;
    }

    Set<Integer> t = new HashSet<>(); // records the unique appearance.
    // L[i] stores the upper range for i.
    Map<Integer, Integer> L = new HashMap<>();
    // U[i] stores the lower range for i.
    Map<Integer, Integer> U = new HashMap<>();
    for (Integer aA : A) {
      if (t.add(aA)) {
        L.put(aA, aA);
        U.put(aA, aA);
        // Merges with the interval starting on A[i] + 1.
        if (L.containsKey(aA + 1)) {
          L.put(U.get(aA), L.get(aA + 1));
          U.put(L.get(aA + 1), U.get(aA));
          L.remove(aA + 1);
          U.remove(aA);
        }
        // Merges with the interval ending on A[i] - 1.
        if (U.containsKey(aA - 1)) {
          U.put(L.get(aA), U.get(aA - 1));
          L.put(U.get(aA - 1), L.get(aA));
          U.remove(aA - 1);
          L.remove(aA);
        }
      }
    }

    Map.Entry<Integer, Integer> m = Collections.max(
        L.entrySet(), new Comparator<Map.Entry<Integer, Integer>>() {
          @Override
          public int compare(Map.Entry<Integer, Integer> o1,
                             Map.Entry<Integer, Integer> o2) {
            return Integer.valueOf(o1.getValue() - o1.getKey())
                .compareTo(o2.getValue() - o2.getKey());
          }
        });
    return m.getValue() - m.getKey() + 1;
  }

  public static Pair<Integer, Integer> findLongestContainedRange(int[] A) {
    // S records the existence of each entry in A.
    Set<Integer> S = new HashSet<>();
    for (int a : A) {
      S.add(a);
    }

    int maxLen = 0;
    Pair<Integer, Integer> maxRange = new Pair<>(0, -1);
    // L stores the longest length ending at each A[i].
    Map<Integer, Integer> L = new HashMap<>();
    for (int a : A) {
      int len = longestRangeLen(a, S, L);
      if (len > maxLen) {
        maxLen = len;
        maxRange = new Pair<>(a - len + 1, a);
      }
    }
    return maxRange;
  }

  private static int longestRangeLen(int a, Set<Integer> S,
                                     Map<Integer, Integer> L) {
    // Base case. If a does not exist.
    if (!S.contains(a)) {
      return 0;
    }

    if (!L.containsKey(a)) {
      L.put(a, longestRangeLen(a - 1, S, L) + 1);
    }
    return L.get(a);
  }

  // @include
  public static int longestContainedRange(int[] A) {
    // unprocessedEntries records the existence of each entry in A.
    Set<Integer> unprocessedEntries = new HashSet<>();
    for (int i = 0; i < A.length; i++) {
      unprocessedEntries.add(A[i]);
    }

    int maxIntervalSize = 0;
    while (!unprocessedEntries.isEmpty()) {
      int a = unprocessedEntries.iterator().next();
      unprocessedEntries.remove(a);

      // Finds the lower bound of the largest range containing a.
      int lowerBound = a - 1;
      while (unprocessedEntries.contains(lowerBound)) {
        unprocessedEntries.remove(lowerBound);
        --lowerBound;
      }

      // Finds the upper bound of the largest range containing a.
      int upperBound = a + 1;
      while (unprocessedEntries.contains(upperBound)) {
        unprocessedEntries.remove(upperBound);
        ++upperBound;
      }

      maxIntervalSize = Math.max(upperBound - lowerBound - 1, maxIntervalSize);
    }
    return maxIntervalSize;
  }
  // @exclude

  public static void main(String[] args) {
    Random r = new Random();
    for (int times = 0; times < 1000; ++times) {
      int n;
      if (args.length == 1) {
        n = Integer.parseInt(args[0]);
      } else {
        n = r.nextInt(10001);
      }
      int[] A = new int[n];
      for (int i = 0; i < n; ++i) {
        A[i] = r.nextInt(n + 1);
      }

      assert(findLongestContainedRangeInt(A) == checkAns(A));
      Pair<Integer, Integer> result = findLongestContainedRange(A);
      System.out.println(result);
      assert(result.getSecond() - result.getFirst() + 1 ==
             findLongestContainedRangeInt(A));
      assert(result.getSecond() - result.getFirst() + 1 ==
             longestContainedRange(A));
    }
  }
}
