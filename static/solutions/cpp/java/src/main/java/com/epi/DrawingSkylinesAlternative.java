package com.epi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class DrawingSkylinesAlternative {
  // @include
  public static class Rectangle {
    public int left, right, height;

    public Rectangle(int left, int right, int height) {
      this.left = left;
      this.right = right;
      this.height = height;
    }
  }

  public static List<Rectangle> drawingSkylines(List<Rectangle> buildings) {
    int minLeft = Integer.MAX_VALUE, maxRight = Integer.MIN_VALUE;
    for (Rectangle building : buildings) {
      minLeft = Math.min(minLeft, building.left);
      maxRight = Math.max(maxRight, building.right);
    }

    List<Integer> heights
        = new ArrayList<>(Collections.nCopies(maxRight - minLeft + 1, 0));
    for (Rectangle building : buildings) {
      for (int i = building.left; i <= building.right; ++i) {
        heights.set(i - minLeft,
                    Math.max(heights.get(i - minLeft), building.height));
      }
    }

    List<Rectangle> result = new ArrayList<>();
    int left = 0;
    for (int i = 1; i < heights.size(); ++i) {
      if (heights.get(i) != heights.get(i - 1)) {
        result.add(
            new Rectangle(left + minLeft, i - 1 + minLeft, heights.get(i - 1)));
        left = i;
      }
    }
    result.add(new Rectangle(left + minLeft, maxRight,
                             heights.get(heights.size() - 1)));
    return result;
  }
  // @exclude

  private static void checkAnswer(List<Rectangle> ans) {
    // Just check there is no overlap.
    for (int i = 0; i < ans.size(); ++i) {
      assert(ans.get(i).left <= ans.get(i).right);
      if (i > 0) {
        assert(ans.get(i - 1).right <= ans.get(i).left);
        assert(ans.get(i - 1).right != ans.get(i).left
               || ans.get(i - 1).height != ans.get(i).height);
      }
    }
  }

  public static void main(String[] args) {
    Random r = new Random();
    for (int times = 0; times < 2000; ++times) {
      int n;
      if (args.length == 1) {
        n = Integer.parseInt(args[0]);
      } else {
        n = r.nextInt(5000) + 1;
      }
      List<Rectangle> A = new ArrayList<>();
      for (int i = 0; i < n; ++i) {
        int left = r.nextInt(1000);
        int right = r.nextInt(201) + left;
        int height = r.nextInt(100);
        A.add(new Rectangle(left, right, height));
      }
      List<Rectangle> ans = drawingSkylines(A);
      System.out.println("n = " + n);
      checkAnswer(ans);
    }
  }
}
