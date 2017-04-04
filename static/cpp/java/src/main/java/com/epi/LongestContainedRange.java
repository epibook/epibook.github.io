package com.epi;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class LongestContainedRange {
  // Represents the set of numbers from begin to end, inclusive, i.e.,
  // [begin,end].
  private static class Range {
    public Integer begin;
    public Integer end;

    public Range(Integer begin, Integer end) {
      this.begin = begin;
      this.end = end;
    }

    // clang-format off
    @Override
    public String toString() { return "[" + begin + "," + end + "]"; }
    // clang-format on
  }

  private static int checkAns(List<Integer> A) {
    Collections.sort(A);
    int result = 1;
    int pre = A.get(0), len = 1;
    for (int i = 1; i < A.size(); ++i) {
      if (A.get(i) == pre + 1) {
        ++len;
      } else if (A.get(i) != pre) {
        result = Math.max(result, len);
        len = 1;
      }
      pre = A.get(i);
    }
    result = Math.max(result, len);
    System.out.println(result);
    return result;
  }

  private static int findLongestContainedRangeInt(List<Integer> A) {
    if (A.isEmpty()) {
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
        // Merges with the interval starting on A.get(i) + 1.
        if (L.containsKey(aA + 1)) {
          L.put(U.get(aA), L.get(aA + 1));
          U.put(L.get(aA + 1), U.get(aA));
          L.remove(aA + 1);
          U.remove(aA);
        }
        // Merges with the interval ending on A.get(i) - 1.
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
            return Integer.compare(o1.getValue() - o1.getKey(),
                                   o2.getValue() - o2.getKey());
          }
        });
    return m.getValue() - m.getKey() + 1;
  }

  public static Range findLongestContainedRange(List<Integer> A) {
    // S records the existence of each entry in A.
    Set<Integer> S = new HashSet<>();
    for (int a : A) {
      S.add(a);
    }

    int maxLen = 0;
    Range maxRange = new Range(0, -1);
    // L stores the longest length ending at each A.get(i).
    Map<Integer, Integer> L = new HashMap<>();
    for (int a : A) {
      int len = longestRangeLen(a, S, L);
      if (len > maxLen) {
        maxLen = len;
        maxRange = new Range(a - len + 1, a);
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
  public static int longestContainedRange(List<Integer> A) {
    // unprocessedEntries records the existence of each entry in A.
    Set<Integer> unprocessedEntries = new HashSet<>(A);

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
      List<Integer> A = new ArrayList<>(n);
      for (int i = 0; i < n; ++i) {
        A.add(r.nextInt(n + 1));
      }

      assert(findLongestContainedRangeInt(A) == checkAns(A));
      Range result = findLongestContainedRange(A);
      System.out.println(result);
      assert(result.end - result.begin + 1 == findLongestContainedRangeInt(A));
      assert(result.end - result.begin + 1 == longestContainedRange(A));
    }
  }
}
