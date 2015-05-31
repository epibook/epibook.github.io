package com.epi;

import java.util.*;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class StringInMatrix {
  private static void randMatrix(int[][] matrix) {
    Random r = new Random();
    for (int i = 0; i < matrix.length; ++i) {
      for (int j = 0; j < matrix[i].length; ++j) {
        matrix[i][j] = r.nextInt(matrix.length);
      }
    }
  }

  private static class Tuple<A, B, C> {
    public A a;
    public B b;
    public C c;

    public Tuple() {}

    public Tuple(A aVal, B bVal, C cVal) {
      this.a = aVal;
      this.b = bVal;
      this.c = cVal;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }

      Tuple tuple = (Tuple)o;

      if (a != null ? !a.equals(tuple.a) : tuple.a != null) {
        return false;
      }
      if (b != null ? !b.equals(tuple.b) : tuple.b != null) {
        return false;
      }
      if (c != null ? !c.equals(tuple.c) : tuple.c != null) {
        return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      int result = a != null ? a.hashCode() : 0;
      result = 31 * result + (b != null ? b.hashCode() : 0);
      result = 31 * result + (c != null ? c.hashCode() : 0);
      return result;
    }
  }

  // @include
  public static boolean match(int[][] A, List<Integer> S) {
    Set<Tuple<Integer, Integer, Integer>> cache = new HashSet<>();
    for (int i = 0; i < A.length; ++i) {
      for (int j = 0; j < A[i].length; ++j) {
        if (matchHelper(A, S, cache, i, j, 0)) {
          return true;
        }
      }
    }
    return false;
  }

  private static boolean matchHelper(int[][] A, List<Integer> S,
                                     Set<Tuple<Integer, Integer, Integer>> cache,
                                     int i, int j, int len) {
    if (S.size() == len) {
      return true;
    }

    if (i < 0 || i >= A.length || j < 0 || j >= A[i].length ||
        cache.contains(new Tuple<>(i, j, len))) {
      return false;
    }

    if (A[i][j] == S.get(len) && (matchHelper(A, S, cache, i - 1, j, len + 1) ||
                                  matchHelper(A, S, cache, i + 1, j, len + 1) ||
                                  matchHelper(A, S, cache, i, j - 1, len + 1) ||
                                  matchHelper(A, S, cache, i, j + 1, len + 1))) {
      return true;
    }
    cache.add(new Tuple<>(i, j, len));
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
    int[][] A = new int[n][n];
    randMatrix(A);
    for (int[] element : A) {
      for (int anElement : element) {
        System.out.print(anElement + " ");
      }
      System.out.println();
    }
    System.out.println("\nS = ");
    int size = r.nextInt(n * n / 2) + 1;
    List<Integer> S = new ArrayList<>();
    for (int i = 0; i < size; ++i) {
      S.add(r.nextInt(n));
    }
    System.out.println(S);
    System.out.println(match(A, S));
  }
}
