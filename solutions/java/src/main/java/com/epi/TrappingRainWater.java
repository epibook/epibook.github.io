// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

package com.epi;

import com.epi.utils.Pair;

import java.util.*;
import java.util.LinkedList;

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

    // Calculates the water within [1 : maxH - 1].
    int sum = 0, left = A.get(0);
    for (int i = 1; i < maxH; ++i) {
      if (A.get(i) >= left) {
        left = A.get(i);
      } else {
        sum += left - A.get(i);
      }
    }

    // Calculates the water within [maxH + 1 : A.size() - 2].
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
  private static int checkAnswer(List<Integer> A) {
    LinkedList<Pair<Integer, Integer>> s = new LinkedList<>();
    int sum = 0;
    for (int i = 0; i < A.size(); ++i) {
      while (!s.isEmpty() && s.peek().getSecond() <= A.get(i)) {
        int bottom = s.pop().getSecond();
        if (s.isEmpty()) {
          break;
        }
        int top = Math.min(s.peek().getSecond(), A.get(i));
        sum += (top - bottom) * (i - s.peek().getFirst() - 1);
      }
      s.push(new Pair<>(i, A.get(i)));
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
