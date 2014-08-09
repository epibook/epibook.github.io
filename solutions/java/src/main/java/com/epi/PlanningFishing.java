package com.epi;

import java.util.Arrays;
import java.util.Random;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class PlanningFishing {
  // @include
  public static int maximizeFishing(int[][] A) {
    for (int i = 0; i < A.length; ++i) {
      for (int j = 0; j < A[i].length; ++j) {
        A[i][j] += Math.max(i < 1 ? 0 : A[i - 1][j], j < 1 ? 0 : A[i][j - 1]);
      }
    }
    return A[A.length - 1][A[0].length - 1];
  }
  // @exclude

  public static void main(String[] args) {
    Random r = new Random();
    int n, m;
    if (args.length == 2) {
      n = Integer.parseInt(args[0]);
      m = Integer.parseInt(args[1]);
    } else {
      n = r.nextInt(100) + 1;
      m = r.nextInt(100) + 1;
    }
    int[][] A = new int[n][m];
    for (int i = 0; i < n; ++i) {
      for (int j = 0; j < m; ++j) {
        A[i][j] = r.nextInt(1000);
      }
    }
    System.out.println(Arrays.deepToString(A));
    System.out.println(maximizeFishing(A));
  }
}
