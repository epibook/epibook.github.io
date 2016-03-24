package com.epi;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static com.epi.LargestRectangleUnderSkyline.calculateLargestRectangle;
import static com.epi.MaxSubmatrixRectangleBruteForce.maxRectangleSubmatrixBruteForce;

public class MaxSubmatrixRectangleImproved {
  // @include
  public static int maxRectangleSubmatrix(List<List<Boolean>> A) {
    List<Integer> table
        = new ArrayList<>(Collections.nCopies(A.get(0).size(), 0));
    int maxRectArea = 0;
    // Find the maximum among all instances of the largest rectangle.
    for (int i = 0; i < A.size(); ++i) {
      for (int j = 0; j < A.get(i).size(); ++j) {
        table.set(j, A.get(i).get(j) ? table.get(j) + 1 : 0);
      }
      maxRectArea = Math.max(maxRectArea, calculateLargestRectangle(table));
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
      List<List<Boolean>> A = new ArrayList<>(n);
      for (int i = 0; i < n; ++i) {
        List<Boolean> last = new ArrayList<>(m);
        A.add(last);
        for (int j = 0; j < m; ++j) {
          last.add(r.nextBoolean());
        }
      }
      // System.out.println(A);
      System.out.println(maxRectangleSubmatrix(A));
      System.out.println(maxRectangleSubmatrixBruteForce(A));
      assert(maxRectangleSubmatrixBruteForce(A) == maxRectangleSubmatrix(A));
    }
  }
}
