package com.epi;

import java.util.Random;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class NumberWays {
  // @include
  public static int numberOfWays(int n, int m) {
    if (n < m) {
      int temp = n;
      n = m;
      m = temp;
    }
    int[] A = new int[m];
    for (int j = 0; j < m; ++j) {
      A[j] = 1;
    }
    for (int i = 1; i < n; ++i) {
      int prev_res = 0;
      for (int j = 0; j < m; ++j) {
        A[j] = A[j] + prev_res;
        prev_res = A[j];
      }
    }
    return A[m - 1];
  }
  // @exclude

  private static int checkAns(int n, int k) {
    int table[][] = new int[n + 1][k + 1];
    // Basic case: C(i, 0) = 1.
    for (int i = 0; i <= n; ++i) {
      table[i][0] = 1;
    }
    // Basic case: C(i, i) = 1.
    for (int i = 1; i <= k; ++i) {
      table[i][i] = 1;
    }
    // C(i, j) = C(i - 1, j) + C(i - 1, j - 1).
    for (int i = 2; i <= n; ++i) {
      for (int j = 1; j < i && j <= k; ++j) {
        table[i][j] = table[i - 1][j] + table[i - 1][j - 1];
      }
    }
    return table[n][k];
  }

  public static void main(String[] args) {
    Random r = new Random();
    for (int times = 0; times < 1000; ++times) {
      int n, m;
      if (args.length == 2) {
        n = Integer.parseInt(args[0]);
        m = Integer.parseInt(args[1]);
      } else {
        n = r.nextInt(10) + 1;
        m = r.nextInt(10) + 1;
      }
      System.out.println("n = " + n + ", m = " + m + ", number of ways = " +
                         numberOfWays(n, m));
      assert(checkAns(n + m - 2, m - 1) == numberOfWays(n, m));
      if (args.length == 2) {
        break;
      }
    }
  }
}
