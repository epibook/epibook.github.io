package com.epi;

/*
@slug
view-from-above

@summary
You are given a set of line segments. Each segment
consists of a closed interval of the $X$-axis,
a color, and a height.  When viewed from above, the color at point $x$ on the
$X$-axis is the color
of the highest segment that includes $x$.
This is illustrated in Figure~\vref{2d-render-fig}.

@problem
Write a program that takes lines segments as input, and
outputs the view from above for these segments.
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
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
    // @exclude
    @Override
    public boolean equals(Object obj) {
      if (obj == null || !(obj instanceof LineSegment)) {
        return false;
      }
      LineSegment that = (LineSegment)obj;
      return this == obj ? true : left == that.left && right == that.right
                                      && color == that.color
                                      && height == that.height;
    }
    // @include
  }

  public static class Endpoint implements Comparable<Endpoint> {
    private boolean isLeft;
    private LineSegment line;

    public Endpoint(boolean isLeft, LineSegment line) {
      this.isLeft = isLeft;
      this.line = line;
    }

    public int value() { return isLeft ? line.left : line.right; }

    // clang-format off
    @Override
    public int compareTo(Endpoint o) { return Integer.compare(value(), o.value()); }
    // clang-format on
  }

  public static List<LineSegment> calculateViewFromAbove(List<LineSegment> A) {
    List<Endpoint> sortedEndpoints = new ArrayList<>();
    for (LineSegment a : A) {
      sortedEndpoints.add(new Endpoint(true, a));
      sortedEndpoints.add(new Endpoint(false, a));
    }
    Collections.sort(sortedEndpoints);

    List<LineSegment> result = new ArrayList<>();
    int prevXAxis = sortedEndpoints.get(0).value(); // Leftmost end point.
    LineSegment prev = null;
    TreeMap<Integer, LineSegment> activeLineSegments = new TreeMap<>();
    for (Endpoint endpoint : sortedEndpoints) {
      if (!activeLineSegments.isEmpty() && prevXAxis != endpoint.value()) {
        if (prev == null) { // Found first segment.
          prev = new LineSegment(
              prevXAxis, endpoint.value(),
              activeLineSegments.lastEntry().getValue().color,
              activeLineSegments.lastEntry().getValue().height);
        } else {
          if (prev.height == activeLineSegments.lastEntry().getValue().height
              && prev.color == activeLineSegments.lastEntry().getValue().color
              && prevXAxis == prev.right) {
            prev.right = endpoint.value();
          } else {
            result.add(prev);
            prev = new LineSegment(
                prevXAxis, endpoint.value(),
                activeLineSegments.lastEntry().getValue().color,
                activeLineSegments.lastEntry().getValue().height);
          }
        }
      }
      prevXAxis = endpoint.value();

      if (endpoint.isLeft) { // Left end point.
        activeLineSegments.put(endpoint.line.height, endpoint.line);
      } else { // Right end point.
        activeLineSegments.remove(endpoint.line.height);
      }
    }

    // Output the remaining segment (if any).
    if (prev != null) {
      result.add(prev);
    }
    return result;
  }
  // @exclude

  private static void simpleTest() {
    List<LineSegment> A = Arrays.asList(new LineSegment(1, 2, 0, 1),
                                        new LineSegment(3, 4, 0, 1));
    List<LineSegment> result = calculateViewFromAbove(A);
    List<LineSegment> goldenResult = Arrays.asList(new LineSegment(1, 2, 0, 1),
                                                   new LineSegment(3, 4, 0, 1));
    assert(result.equals(goldenResult));
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
      System.out.println("line segment, left = " + s.left + ", right = "
                         + s.right + ", color = " + s.color + ", height = "
                         + s.height);
    }
    List<LineSegment> result = calculateViewFromAbove(A);
    List<LineSegment> goldenResult = Arrays.asList(
        new LineSegment(0, 1, 0, 0), new LineSegment(1, 3, 1, 2),
        new LineSegment(3, 4, 2, 1), new LineSegment(4, 5, 3, 3),
        new LineSegment(5, 6, 2, 1), new LineSegment(6, 10, 0, 2),
        new LineSegment(10, 11, 4, 0), new LineSegment(11, 13, 3, 2),
        new LineSegment(13, 14, 4, 1), new LineSegment(14, 15, 2, 2),
        new LineSegment(15, 16, 4, 0), new LineSegment(16, 17, 3, 2),
        new LineSegment(17, 18, 4, 0));
    assert(result.equals(goldenResult));
  }
}
