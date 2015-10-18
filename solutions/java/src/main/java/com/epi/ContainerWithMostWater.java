package com.epi;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ContainerWithMostWater {
  // @include
  public static int getMaxArea(List<Integer> heights) {
    int i = 0, j = heights.size() - 1, maxArea = 0;
    while (i < j) {
      maxArea = Math.max(maxArea,
                         Math.min(heights.get(i), heights.get(j)) * (j - i));
      if (heights.get(i) > heights.get(j)) {
        --j;
      } else if (heights.get(i) < heights.get(j)) {
        ++i;
      } else { // heights.get(i) == heights.get(j).
        ++i;
        --j;
      }
    }
    return maxArea;
  }
  // @exclude

  // O(n^2) checking answer.
  private static int checkAns(List<Integer> heights) {
    int res = 0;
    for (int i = 0; i < heights.size(); ++i) {
      for (int j = i + 1; j < heights.size(); ++j) {
        res = Math.max(res, Math.min(heights.get(i), heights.get(j)) * (j - i));
      }
    }
    return res;
  }

  private static void smallTest() {
    List<Integer> A
        = Arrays.asList(1, 2, 1, 3, 4, 4, 5, 6, 2, 1, 3, 1, 3, 2, 1, 2, 4, 1);
    assert(48 == getMaxArea(A));
  }

  public static void main(String[] args) {
    smallTest();
    Random r = new Random();
    for (int times = 0; times < 1000; ++times) {
      int n;
      if (args.length == 1) {
        n = Integer.parseInt(args[0]);
      } else {
        n = r.nextInt(9999) + 2;
      }
      List<Integer> heights = new ArrayList<>(n);
      for (int i = 0; i < n; i++) {
        heights.add(r.nextInt(1000) + 1);
      }
      System.out.println(getMaxArea(heights));
      assert(getMaxArea(heights) == checkAns(heights));
    }
  }
}
