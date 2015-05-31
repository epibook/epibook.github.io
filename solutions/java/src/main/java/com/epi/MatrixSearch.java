package com.epi;

import java.util.Random;

public class MatrixSearch {
  // @include
  public static boolean matrixSearch(int[][] A, int x) {
    int row = 0, col = A[0].length - 1; // Starting from the top right corner.
    // Keeps searching if there are unclassified rows and columns.
    while (row < A.length && col >= 0) {
      if (A[row][col] == x) {
        return true;
      } else if (A[row][col] < x) {
        ++row; // Eliminate this row.
      } else { // A[row][col] > x.
        --col; // Eliminate this column.
      }
    }
    return false;
  }
  // @exclude

  // O(n^2) solution for verifying answer.
  private static boolean bruteForceSearch(int[][] A, int x) {
    for (int[] aA : A) {
      for (int anAA : aA) {
        if (anAA == x) {
          return true;
        }
      }
    }
    return false;
  }

  public static void main(String[] args) {
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
      int[][] A = new int[n][];
      for (int i = 0; i < n; i++) {
        A[i] = new int[m];
        for (int j = 0; j < m; j++) {
          A[i][j] = 0;
        }
      }
      A[0][0] = r.nextInt(100);
      for (int i = 0; i < n; ++i) {
        for (int j = 0; j < m; ++j) {
          int up = (i == 0) ? 0 : A[i - 1][j];
          int left = (j == 0) ? 0 : A[i][j - 1];
          A[i][j] = Math.max(up, left) + r.nextInt(20) + 1;
        }
      }
      int x = r.nextInt(1000);
      assert(bruteForceSearch(A, x) == matrixSearch(A, x));
    }
  }
}
