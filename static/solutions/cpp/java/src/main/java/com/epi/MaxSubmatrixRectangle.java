package com.epi;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.epi.MaxSubmatrixRectangleBruteForce.maxRectangleSubmatrixBruteForce;

public class MaxSubmatrixRectangle {
  // @include
  private static class MaxHW {
    public int h, w;

    public MaxHW(int h, int w) {
      this.h = h;
      this.w = w;
    }
  }

  public static int maxRectangleSubmatrix(List<List<Boolean>> A) {
    // DP table stores (h, w) for each (i, j).
    MaxHW[][] table = new MaxHW[A.size()][A.get(0).size()];

    for (int i = A.size() - 1; i >= 0; --i) {
      for (int j = A.get(i).size() - 1; j >= 0; --j) {
        // Find the largest h such that (i, j) to (i + h - 1, j) are feasible.
        // Find the largest w such that (i, j) to (i, j + w - 1) are feasible.
        table[i][j]
            = A.get(i).get(j)
                  ? new MaxHW(
                        i + 1 < A.size() ? table[i + 1][j].h + 1 : 1,
                        j + 1 < A.get(i).size() ? table[i][j + 1].w + 1 : 1)
                  : new MaxHW(0, 0);
      }
    }

    int maxRectArea = 0;
    for (int i = 0; i < A.size(); ++i) {
      for (int j = 0; j < A.get(i).size(); ++j) {
        // Process (i, j) if it is feasible and is possible to update
        // maxRectArea.
        if (A.get(i).get(j) && table[i][j].w * table[i][j].h > maxRectArea) {
          int minWidth = Integer.MAX_VALUE;
          for (int a = 0; a < table[i][j].h; ++a) {
            minWidth = Math.min(minWidth, table[i + a][j].w);
            maxRectArea = Math.max(maxRectArea, minWidth * (a + 1));
          }
        }
      }
    }
    return maxRectArea;
  }
  // @exclude

  public static void main(String[] args) {
    Random r = new Random();
    for (int times = 0; times < 1000; ++times) {
      int n, m;
      if (args.length == 2) {
        n = Integer.parseInt(args[0]);
        m = Integer.parseInt(args[1]);
      } else {
        n = r.nextInt(50) + 1;
        m = r.nextInt(50) + 1;
      }
      List<List<Boolean>> A = new ArrayList<>(n);
      for (int i = 0; i < n; ++i) {
        List<Boolean> last = new ArrayList<>(m);
        A.add(last);
        for (int j = 0; j < m; ++j) {
          last.add(r.nextBoolean());
        }
      }
      for (int i = 0; i < n; ++i) {
        for (int j = 0; j < m; ++j) {
          System.out.print(A.get(i).get(j) + " ");
        }
        System.out.println();
      }
      System.out.println(maxRectangleSubmatrix(A));
      int testArea = maxRectangleSubmatrixBruteForce(A);
      System.out.println(testArea);
      assert(testArea == maxRectangleSubmatrix(A));
    }
  }
}
