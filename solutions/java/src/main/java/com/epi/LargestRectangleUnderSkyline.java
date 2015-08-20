package com.epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class LargestRectangleUnderSkyline {
  public static int calculateLargestRectangleAlternative(List<Integer> A) {
    // Calculate L.
    LinkedList<Integer> s = new LinkedList<>();
    List<Integer> L = new ArrayList<>();
    for (int i = 0; i < A.size(); ++i) {
      while (!s.isEmpty() && A.get(s.peek()) >= A.get(i)) {
        s.pop();
      }
      L.add(s.isEmpty() ? -1 : s.peek());
      s.push(i);
    }

    // Clear stack for calculating R.
    while (!s.isEmpty()) {
      s.pop();
    }
    int[] R = new int[A.size()];
    for (int i = A.size() - 1; i >= 0; --i) {
      while (!s.isEmpty() && A.get(s.peek()) >= A.get(i)) {
        s.pop();
      }
      R[i] = s.isEmpty() ? A.size() : s.peek();
      s.push(i);
    }

    // For each A[i], find its maximum area include it.
    int maxArea = 0;
    for (int i = 0; i < A.size(); ++i) {
      maxArea = Math.max(maxArea, A.get(i) * (R[i] - L.get(i) - 1));
    }
    return maxArea;
  }

  // @include
  public static int calculateLargestRectangle(List<Integer> A) {
    LinkedList<Integer> s = new LinkedList<>();
    int maxArea = 0;
    for (int i = 0; i <= A.size(); ++i) {
      while (!s.isEmpty() && (i == A.size() || A.get(i) < A.get(s.peek()))) {
        int height = A.get(s.peek());
        s.pop();
        maxArea =
            Math.max(maxArea, height * (s.isEmpty() ? i : i - s.peek() - 1));
      }
      s.push(i);
    }
    return maxArea;
  }
  // @exclude

  // O(n^2) implementation checks answer.
  private static int checkAnswer(List<Integer> A) {
    int max = -1;
    for (int i = 0; i < A.size(); ++i) {
      int left = i - 1, right = i + 1;
      while (left >= 0 && A.get(left) >= A.get(i)) {
        --left;
      }
      while (right < A.size() && A.get(right) >= A.get(i)) {
        ++right;
      }
      int area = (right - left - 1) * A.get(i);
      if (area > max) {
        max = area;
      }
    }
    System.out.println(max);
    return max;
  }

  private static void smallTest() {
    List<Integer> A = Arrays.asList(2, 3, 4, 1, 2);
    int area = calculateLargestRectangle(A);
    int alterArea = calculateLargestRectangleAlternative(A);
    assert(area == alterArea);
    assert(checkAnswer(A) == area);
    assert(6 == area);
    A = Arrays.asList(2, 2, 2);
    System.out.println(calculateLargestRectangle(A));
    assert(6 == calculateLargestRectangle(A));
  }

  public static void main(String[] args) {
    smallTest();
    Random r = new Random();
    for (int times = 0; times < 3000; ++times) {
      int n;
      if (args.length == 1) {
        n = Integer.parseInt(args[0]);
      } else {
        n = r.nextInt(1000) + 1;
      }
      List<Integer> A = new ArrayList<>();
      for (int i = 0; i < n; ++i) {
        A.add(r.nextInt(999));
      }
      int area = calculateLargestRectangle(A);
      int alterArea = calculateLargestRectangleAlternative(A);
      System.out.println(area + " " + alterArea);
      assert(area == alterArea);
      assert(checkAnswer(A) == area);
    }
  }
}
