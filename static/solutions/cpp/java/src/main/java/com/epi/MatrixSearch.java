package com.epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MatrixSearch {
  // @include
  public static boolean matrixSearch(List<List<Integer>> A, int x) {
    int row = 0, col = A.get(0).size() - 1; // Start from the top-right corner.
    // Keeps searching while there are unclassified rows and columns.
    while (row < A.size() && col >= 0) {
      if (A.get(row).get(col).equals(x)) {
        return true;
      } else if (A.get(row).get(col) < x) {
        ++row; // Eliminate this row.
      } else { // A.get(row).get(col) > x.
        --col; // Eliminate this column.
      }
    }
    return false;
  }
  // @exclude

  // O(n^2) solution for verifying answer.
  private static boolean bruteForceSearch(List<List<Integer>> A, int x) {
    for (List<Integer> aA : A) {
      for (int anAA : aA) {
        if (anAA == x) {
          return true;
        }
      }
    }
    return false;
  }

  private static void simpleTest() {
    List<List<Integer>> A = Arrays.asList(Arrays.asList(1));
    assert(!matrixSearch(A, 0));
    assert(matrixSearch(A, 1));

    A = Arrays.asList(Arrays.asList(1, 5), Arrays.asList(2, 6));
    assert(!matrixSearch(A, 0));
    assert(matrixSearch(A, 1));
    assert(matrixSearch(A, 2));
    assert(matrixSearch(A, 5));
    assert(matrixSearch(A, 6));
    assert(!matrixSearch(A, 3));
    assert(!matrixSearch(A, Integer.MAX_VALUE));

    A = Arrays.asList(Arrays.asList(2, 5), Arrays.asList(2, 6));
    assert(!matrixSearch(A, 1));
    assert(matrixSearch(A, 2));

    A = Arrays.asList(Arrays.asList(1, 5, 7), Arrays.asList(3, 10, 100),
                      Arrays.asList(3, 12, Integer.MAX_VALUE));
    assert(matrixSearch(A, 1));
    assert(!matrixSearch(A, 2));
    assert(!matrixSearch(A, 4));
    assert(matrixSearch(A, 3));
    assert(matrixSearch(A, 10));
    assert(matrixSearch(A, Integer.MAX_VALUE));
    assert(!matrixSearch(A, Integer.MAX_VALUE - 1));
    assert(matrixSearch(A, 12));
  }

  public static void main(String[] args) {
    simpleTest();
    Random r = new Random();
    for (int times = 0; times < 10000; ++times) {
      int n, m;
      if (args.length == 2) {
        n = Integer.parseInt(args[0]);
        m = Integer.parseInt(args[1]);
      } else {
        n = r.nextInt(100) + 1;
        m = r.nextInt(100) + 1;
      }
      List<List<Integer>> A = new ArrayList<>(n);
      for (int i = 0; i < n; i++) {
        A.add(new ArrayList(m));
        for (int j = 0; j < m; j++) {
          A.get(i).add(0);
        }
      }
      A.get(0).set(0, r.nextInt(100));
      for (int i = 0; i < n; ++i) {
        for (int j = 0; j < m; ++j) {
          int up = (i == 0) ? 0 : A.get(i - 1).get(j);
          int left = (j == 0) ? 0 : A.get(i).get(j - 1);
          A.get(i).set(j, Math.max(up, left) + r.nextInt(20) + 1);
        }
      }
      int x = r.nextInt(1000);
      assert(bruteForceSearch(A, x) == matrixSearch(A, x));
    }
  }
}
