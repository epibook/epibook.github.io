package com.epi;

import static com.epi.LargestRectangleUnderSkyline.calculateLargestRectangle;
import static com.epi.MaxSubmatrixRectangleBruteForce.maxRectangleSubmatrixBruteForce;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class MaxSubmatrixRectangleImproved {
  // @include
  public static int maxRectangleSubmatrix(ArrayList<ArrayList<Boolean>> A) {
    Integer[][] table = new Integer[A.size()][A.get(0).size()];

    for (int i = A.size() - 1; i >= 0; --i) {
      for (int j = A.get(i).size() - 1; j >= 0; --j) {
        table[i][j] = A.get(i).get(j) ? i + 1 < A.size() ? table[i + 1][j] + 1
            : 1 : 0;
      }
    }

    // Find the max among all instances of the largest rectangle.
    int maxRectArea = 0;
    for (Integer[] t : table) {
      maxRectArea = Math.max(maxRectArea,
          calculateLargestRectangle(Arrays.asList(t)));
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
        n = r.nextInt(60) + 1;
        m = r.nextInt(60) + 1;
      }
      ArrayList<ArrayList<Boolean>> A = new ArrayList<ArrayList<Boolean>>(n);
      for (int i = 0; i < n; ++i) {
        ArrayList<Boolean> last = new ArrayList<Boolean>(m);
        A.add(last);
        for (int j = 0; j < m; ++j) {
          last.add(r.nextBoolean());
        }
      }
      // System.out.println(A);
      System.out.println(maxRectangleSubmatrix(A));
      System.out.println(maxRectangleSubmatrixBruteForce(A));
      assert (maxRectangleSubmatrixBruteForce(A) == maxRectangleSubmatrix(A));
    }
  }
}
