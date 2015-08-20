package com.epi;

import java.util.Random;

public class RookAttack {
  // @include
  public static void rookAttack(int[][] A) {
    int m = A.length, n = A[0].length;
    boolean hasFirstRowZero = false;
    for (int j = 0; j < n; ++j) {
      if (A[0][j] == 0) {
        hasFirstRowZero = true;
        break;
      }
    }
    boolean hasFirstColumnZero = false;
    for (int[] aA : A) {
      if (aA[0] == 0) {
        hasFirstColumnZero = true;
        break;
      }
    }

    for (int i = 1; i < m; ++i) {
      for (int j = 1; j < n; ++j) {
        if (A[i][j] == 0) {
          A[i][0] = A[0][j] = 0;
        }
      }
    }

    for (int i = 1; i < m; ++i) {
      if (A[i][0] == 0) {
        for (int j = 1; j < n; ++j) {
          A[i][j] = 0;
        }
      }
    }

    for (int j = 1; j < n; ++j) {
      if (A[0][j] == 0) {
        for (int i = 1; i < m; ++i) {
          A[i][j] = 0;
        }
      }
    }

    if (hasFirstRowZero) {
      for (int j = 0; j < n; ++j) {
        A[0][j] = 0;
      }
    }
    if (hasFirstColumnZero) {
      for (int i = 0; i < m; ++i) {
        A[i][0] = 0;
      }
    }
  }
  // @exclude

  private static void checkAns(int[][] A, int[][] ans) {
    for (int i = 0; i < A.length; ++i) {
      for (int j = 0; j < A[i].length; ++j) {
        if (A[i][j] == 0) {
          for (int[] an : ans) {
            assert(an[j] == 0);
          }
          for (int k = 0; k < ans[i].length; ++k) {
            assert(ans[i][k] == 0);
          }
        }
      }
    }
  }

  public static void main(String[] args) {
    Random r = new Random();
    for (int times = 0; times < 1000; ++times) {
      int m, n;
      if (args.length == 2) {
        m = Integer.parseInt(args[0]);
        n = Integer.parseInt(args[1]);
      } else {
        m = r.nextInt(50) + 1;
        n = r.nextInt(50) + 1;
      }
      int A[][] = new int[m][n];
      int copyA[][] = new int[m][n];
      for (int i = 0; i < m; ++i) {
        for (int j = 0; j < n; ++j) {
          copyA[i][j] = A[i][j] = r.nextInt(2);
        }
      }
      System.out.println("m = " + m + ", n = " + n);
      rookAttack(A);
      checkAns(copyA, A);
    }
  }
}
