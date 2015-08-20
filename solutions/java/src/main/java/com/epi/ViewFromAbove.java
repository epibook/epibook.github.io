package com.epi;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

public class ViewFromAbove {
  // @include
  public static class LineSegment {
    public int left, right; // Specifies the interval.
    public int color;
    public int height;

    public LineSegment(int left, int right, int color, int height) {
      this.left = left;
      this.right = right;
      this.color = color;
      this.height = height;
    }

    public String toString() {
      return "[" + left + ", " + right + "], color = " + color + ", height = " +
          height;
    }
  }

  public static class Endpoint implements Comparable<Endpoint> {
    private boolean isLeft;
    private LineSegment line;

    public Endpoint(boolean isLeft, LineSegment line) {
      this.isLeft = isLeft;
      this.line = line;
    }

    public int val() { return isLeft ? line.left : line.right; }

    @Override
    public int compareTo(Endpoint o) {
      return Integer.valueOf(val()).compareTo(o.val());
    }
  }

  public static void calculateViewFromAbove(List<LineSegment> A) {
    List<Endpoint> sortedEndpoints = new ArrayList<>();
    for (LineSegment a : A) {
      sortedEndpoints.add(new Endpoint(true, a));
      sortedEndpoints.add(new Endpoint(false, a));
    }
    Collections.sort(sortedEndpoints);

    int prevXAxis = sortedEndpoints.get(0).val(); // Leftmost end point.
    LineSegment prev = null;
    TreeMap<Integer, LineSegment> activeLineSegments = new TreeMap<>();
    for (Endpoint endpoint : sortedEndpoints) {
      if (!activeLineSegments.isEmpty() && prevXAxis != endpoint.val()) {
        if (prev == null) { // Found first segment.
          prev =
              new LineSegment(prevXAxis, endpoint.val(),
                              activeLineSegments.lastEntry().getValue().color,
                              activeLineSegments.lastEntry().getValue().height);
        } else {
          if (prev.height == activeLineSegments.lastEntry().getValue().height &&
              prev.color == activeLineSegments.lastEntry().getValue().color &&
              prevXAxis == prev.right) {
            prev.right = endpoint.val();
          } else {
            System.out.println(prev);
            prev =
                new LineSegment(prevXAxis, endpoint.val(),
                                activeLineSegments.lastEntry().getValue().color,
                                activeLineSegments.lastEntry().getValue().height);
          }
        }
      }
      prevXAxis = endpoint.val();

      if (endpoint.isLeft) { // Left end point.
        activeLineSegments.put(endpoint.line.height, endpoint.line);
      } else { // Right end point.
        activeLineSegments.remove(endpoint.line.height);
      }
    }

    // Output the remaining segment (if any).
    if (prev != null) {
      System.out.println(prev);
    }
  }
  // @exclude

  private static void simpleTest() {
    List<LineSegment> A =
        Arrays.asList(new LineSegment(1, 2, 0, 1), new LineSegment(3, 4, 0, 1));
    calculateViewFromAbove(A);
  }

  public static void main(String[] args) {
    simpleTest();
    List<LineSegment> A = Arrays.asList(
        new LineSegment(0, 4, 0, 0), new LineSegment(1, 3, 1, 2),
        new LineSegment(2, 7, 2, 1), new LineSegment(4, 5, 3, 3),
        new LineSegment(5, 7, 3, 0), new LineSegment(6, 10, 0, 2),
        new LineSegment(8, 9, 0, 1), new LineSegment(9, 18, 4, 0),
        new LineSegment(11, 13, 3, 2), new LineSegment(12, 15, 4, 1),
        new LineSegment(14, 15, 2, 2), new LineSegment(16, 17, 3, 2));
    for (LineSegment s : A) {
      System.out.println("line segment, left = " + s.left + ", right = " +
                         s.right + ", color = " + s.color + ", height = " +
                         s.height);
    }
    calculateViewFromAbove(A);
  }
}
