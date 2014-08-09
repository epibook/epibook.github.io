package com.epi;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class NumberWaysObstacles {
  // @include
  // Given the dimensions of A, n and m, and B, return the number of ways
  // from A[0][0] to A[n - 1][m - 1] considering obstacles.
  public static int numberOfWaysWithObstacles(int n, int m,
                                              List<List<Boolean>> B) {
    int[][] A = new int[n][m];
    if (B.get(0).get(0)) { // No way to start from (0, 0) if B[0][0] == true.
      return 0;
    }

    A[0][0] = 1;
    for (int i = 0; i < n; ++i) {
      for (int j = 0; j < m; ++j) {
        if (!B.get(i).get(j)) {
          A[i][j] += (i < 1 ? 0 : A[i - 1][j]) + (j < 1 ? 0 : A[i][j - 1]);
        }
      }
    }
    return A[n - 1][m - 1];
  }
  // @exclude

  public static void main(String[] args) {
    Random r = new Random();
    int n, m;
    if (args.length == 2) {
      n = Integer.parseInt(args[0]);
      m = Integer.parseInt(args[1]);
    } else {
      n = r.nextInt(10) + 1;
      m = r.nextInt(10) + 1;
    }
    List<List<Boolean>> B = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      List<Boolean> last = new ArrayList<>(m);
      B.add(last);
      for (int j = 0; j < m; j++) {
        last.add(r.nextInt(10) < 2);
      }
    }
    System.out.println(B);
    System.out.println(numberOfWaysWithObstacles(n, m, B));
  }
}
