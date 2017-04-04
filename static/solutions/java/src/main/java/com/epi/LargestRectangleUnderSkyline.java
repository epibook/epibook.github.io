package com.epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class LargestRectangleUnderSkyline {
  public static int calculateLargestRectangleAlternative(
      List<Integer> heights) {
    // Calculate L.
    Deque<Integer> s = new LinkedList<>();
    List<Integer> L = new ArrayList<>();
    for (int i = 0; i < heights.size(); ++i) {
      while (!s.isEmpty() && heights.get(s.peekFirst()) >= heights.get(i)) {
        s.removeFirst();
      }
      L.add(s.isEmpty() ? Integer.valueOf(-1) : s.peekFirst());
      s.addFirst(i);
    }

    // Clear stack for calculating R.
    while (!s.isEmpty()) {
      s.removeFirst();
    }
    List<Integer> R = new ArrayList<>(Collections.nCopies(heights.size(), 0));
    for (int i = heights.size() - 1; i >= 0; --i) {
      while (!s.isEmpty() && heights.get(s.peekFirst()) >= heights.get(i)) {
        s.removeFirst();
      }
      R.set(i, s.isEmpty() ? heights.size() : s.peekFirst());
      s.addFirst(i);
    }

    // For each heights.get(i), find its maximum area include it.
    int maxRectangleArea = 0;
    for (int i = 0; i < heights.size(); ++i) {
      maxRectangleArea = Math.max(maxRectangleArea,
                                  heights.get(i) * (R.get(i) - L.get(i) - 1));
    }
    return maxRectangleArea;
  }

  // @include
  public static int calculateLargestRectangle(List<Integer> heights) {
    Deque<Integer> pillarIndices = new LinkedList<>();
    int maxRectangleArea = 0;
    for (int i = 0; i <= heights.size(); ++i) {
      if (!pillarIndices.isEmpty() && i < heights.size()
          && heights.get(i).equals(heights.get(pillarIndices.peekFirst()))) {
        // Replace earlier building with same height by current building. This
        // ensures the later buildings have the correct left endpoint.
        pillarIndices.removeFirst();
        pillarIndices.addFirst(i);
      }
      // By iterating to heights.size() instead of heights.size() - 1, we can
      // uniformly handle the computation for rectangle area here.
      while (!pillarIndices.isEmpty()
             && isNewPillarOrReachEnd(heights, i, pillarIndices.peekFirst())) {
        int height = heights.get(pillarIndices.removeFirst());
        int width
            = pillarIndices.isEmpty() ? i : i - pillarIndices.peekFirst() - 1;
        maxRectangleArea = Math.max(maxRectangleArea, height * width);
      }
      pillarIndices.addFirst(i);
    }
    return maxRectangleArea;
  }

  private static boolean isNewPillarOrReachEnd(List<Integer> heights,
                                               int currIdx, int lastPillarIdx) {
    return currIdx < heights.size()
        ? heights.get(currIdx) < heights.get(lastPillarIdx)
        : true;
  }
  // @exclude

  // O(n^2) implementation checks answer.
  private static int checkAnswer(List<Integer> heights) {
    int max = -1;
    for (int i = 0; i < heights.size(); ++i) {
      int left = i - 1, right = i + 1;
      while (left >= 0 && heights.get(left) >= heights.get(i)) {
        --left;
      }
      while (right < heights.size() && heights.get(right) >= heights.get(i)) {
        ++right;
      }
      int area = (right - left - 1) * heights.get(i);
      if (area > max) {
        max = area;
      }
    }
    System.out.println(max);
    return max;
  }

  private static void smallTest() {
    List<Integer> heights = Arrays.asList(2, 3, 4, 1, 2);
    int area = calculateLargestRectangle(heights);
    int alterArea = calculateLargestRectangleAlternative(heights);
    assert(area == alterArea);
    assert(checkAnswer(heights) == area);
    assert(6 == area);
    heights = Arrays.asList(2, 2, 2);
    System.out.println(calculateLargestRectangle(heights));
    assert(6 == calculateLargestRectangle(heights));
    heights = Arrays.asList(1, 1, 2);
    System.out.println(calculateLargestRectangle(heights));
    assert(3 == calculateLargestRectangle(heights));
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
      List<Integer> heights = new ArrayList<>();
      for (int i = 0; i < n; ++i) {
        heights.add(r.nextInt(999));
      }
      int area = calculateLargestRectangle(heights);
      int alterArea = calculateLargestRectangleAlternative(heights);
      System.out.println(area + " " + alterArea);
      assert(area == alterArea);
      assert(checkAnswer(heights) == area);
    }
  }
}
