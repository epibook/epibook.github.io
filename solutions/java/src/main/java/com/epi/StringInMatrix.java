package com.epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class StringInMatrix {
  private static List<List<Integer>> randMatrix(int n) {
    Random r = new Random();
    List<List<Integer>> matrix = new ArrayList<>(n);
    for (int i = 0; i < n; ++i) {
      matrix.add(new ArrayList(n));
      for (int j = 0; j < n; ++j) {
        matrix.get(i).add(r.nextInt(n));
      }
    }
    return matrix;
  }

  private static class CacheEntry {
    public Integer x;
    public Integer y;
    public Integer suffixIndex;

    public CacheEntry() {}

    public CacheEntry(Integer x, Integer y, Integer suffixIndex) {
      this.x = x;
      this.y = y;
      this.suffixIndex = suffixIndex;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }

      CacheEntry cacheEntry = (CacheEntry)o;

      if (x != null ? !x.equals(cacheEntry.x) : cacheEntry.x != null) {
        return false;
      }
      if (y != null ? !y.equals(cacheEntry.y) : cacheEntry.y != null) {
        return false;
      }
      if (suffixIndex != null ? !suffixIndex.equals(cacheEntry.suffixIndex)
                              : cacheEntry.suffixIndex != null) {
        return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      int result = x != null ? x.hashCode() : 0;
      result = 31 * result + (y != null ? y.hashCode() : 0);
      result = 31 * result + (suffixIndex != null ? suffixIndex.hashCode() : 0);
      return result;
    }
  }

  // @include
  public static boolean match(List<List<Integer>> A, List<Integer> S) {
    Set<CacheEntry> failedAttemptsCache = new HashSet<>();
    for (int i = 0; i < A.size(); ++i) {
      for (int j = 0; j < A.get(i).size(); ++j) {
        if (matchHelper(A, S, failedAttemptsCache, i, j, 0)) {
          return true;
        }
      }
    }
    return false;
  }

  private static boolean matchHelper(List<List<Integer>> A, List<Integer> S,
                                     Set<CacheEntry> failedAttemptsCache, int i,
                                     int j, int len) {
    if (S.size() == len) {
      return true;
    }

    if (i < 0 || i >= A.size() || j < 0 || j >= A.get(i).size() ||
        failedAttemptsCache.contains(new CacheEntry(i, j, len))) {
      return false;
    }

    if (A.get(i).get(j) == S.get(len) &&
        (matchHelper(A, S, failedAttemptsCache, i - 1, j, len + 1) ||
         matchHelper(A, S, failedAttemptsCache, i + 1, j, len + 1) ||
         matchHelper(A, S, failedAttemptsCache, i, j - 1, len + 1) ||
         matchHelper(A, S, failedAttemptsCache, i, j + 1, len + 1))) {
      return true;
    }
    failedAttemptsCache.add(new CacheEntry(i, j, len));
    return false;
  }
  // @exclude

  public static void main(String[] args) {
    Random r = new Random();
    int n;
    if (args.length == 1) {
      n = Integer.parseInt(args[0]);
    } else {
      n = r.nextInt(9) + 2;
    }
    List<List<Integer>> A = randMatrix(n);
    System.out.println("A = " + A);
    int size = r.nextInt(n * n / 2) + 1;
    List<Integer> S = new ArrayList<>();
    for (int i = 0; i < size; ++i) {
      S.add(r.nextInt(n));
    }
    System.out.println("S = " + S);
    System.out.println(match(A, S));
  }
}
