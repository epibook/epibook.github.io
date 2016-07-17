// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

package com.epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class TrappingRainWater {
  private static int getIndexOfMaxElement(List<Integer> A) {
    int max = Integer.MIN_VALUE;
    int maxH = -1;
    for (int i = 0; i < A.size(); i++) {
      if (A.get(i) > max) {
        max = A.get(i);
        maxH = i;
      }
    }
    return maxH;
  }

  // @include
  public static int calculateTrappingWater(List<Integer> A) {
    if (A.isEmpty()) {
      return 0;
    }

    // Finds the index with maximum height.
    int maxH = getIndexOfMaxElement(A);

    // Calculates the water within A.subList(1, maxH).
    int sum = 0, left = A.get(0);
    for (int i = 1; i < maxH; ++i) {
      if (A.get(i) >= left) {
        left = A.get(i);
      } else {
        sum += left - A.get(i);
      }
    }

    // Calculates the water within A.subList(maxH + 1, A.size() - 1).
    int right = A.get(A.size() - 1);
    for (int i = A.size() - 2; i > maxH; --i) {
      if (A.get(i) >= right) {
        right = A.get(i);
      } else {
        sum += right - A.get(i);
      }
    }
    return sum;
  }
  // @exclude

  // Stack approach, O(n) time, O(n) space
  private static class HeightBound {
    public Integer leftBound;
    public Integer rightBound;

    public HeightBound(Integer leftBound, Integer rightBound) {
      this.leftBound = leftBound;
      this.rightBound = rightBound;
    }
  }

  private static int checkAnswer(List<Integer> A) {
    Deque<HeightBound> s = new LinkedList<>();
    int sum = 0;
    for (int i = 0; i < A.size(); ++i) {
      while (!s.isEmpty() && s.peekFirst().rightBound <= A.get(i)) {
        int bottom = s.removeFirst().rightBound;
        if (s.isEmpty()) {
          break;
        }
        int top = Math.min(s.peekFirst().rightBound, A.get(i));
        sum += (top - bottom) * (i - s.peekFirst().leftBound - 1);
      }
      s.addFirst(new HeightBound(i, A.get(i)));
    }
    return sum;
  }

  private static void smallTest() {
    List<Integer> A = Arrays.asList(1, 0, 3, 2, 5, 0, 1);
    assert(calculateTrappingWater(A) == 3);
  }

  public static void main(String[] args) {
    smallTest();
    Random r = new Random();
    for (int times = 0; times < 10000; ++times) {
      int n;
      if (args.length == 1) {
        n = Integer.parseInt(args[0]);
      } else {
        n = r.nextInt(1000) + 1;
      }
      List<Integer> A = new ArrayList<>();
      for (int i = 0; i < n; i++) {
        A.add(r.nextInt(11));
      }
      System.out.println(A);
      System.out.println(checkAnswer(A) + " " + calculateTrappingWater(A));
      assert(checkAnswer(A) == calculateTrappingWater(A));
    }
  }
}
