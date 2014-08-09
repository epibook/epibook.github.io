package com.epi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

/**
 * @author translated from c++ by Blazheev Alexander
 */
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
      return "[" + left + ", " + right + "], color = " + color + ", height = "
          + height;
    }
  }

  public static class Endpoint implements Comparable<Endpoint> {
    private boolean isLeft;
    private LineSegment l;

    public Endpoint(boolean isLeft, LineSegment l) {
      this.isLeft = isLeft;
      this.l = l;
    }

    public int val() {
      return isLeft ? l.left : l.right;
    }

    @Override
    public int compareTo(Endpoint o) {
      return Integer.valueOf(val()).compareTo(o.val());
    }
  }

  public static void calculateViewFromAbove(List<LineSegment> A) {
    List<Endpoint> E = new ArrayList<>();
    for (LineSegment a : A) {
      E.add(new Endpoint(true, a));
      E.add(new Endpoint(false, a));
    }
    Collections.sort(E);

    int prevXAxis = E.get(0).val(); // The first left end point.
    LineSegment prev = null;
    TreeMap<Integer, LineSegment> T = new TreeMap<>();
    for (Endpoint e : E) {
      if (!T.isEmpty() && prevXAxis != e.val()) {
        if (prev == null) { // Found first segment.
          prev = new LineSegment(prevXAxis, e.val(),
              T.lastEntry().getValue().color,
              T.lastEntry().getValue().height);
        } else {
          if (prev.height == T.lastEntry().getValue().height
              && prev.color == T.lastEntry().getValue().color) {
            prev.right = e.val();
          } else {
            System.out.println(prev);
            prev = new LineSegment(prevXAxis, e.val(),
                T.lastEntry().getValue().color,
                T.lastEntry().getValue().height);
          }
        }
      }
      prevXAxis = e.val();

      if (e.isLeft) { // Left end point.
        T.put(e.l.height, e.l);
      } else { // Right end point.
        T.remove(e.l.height);
      }
    }

    // Output the remaining segment if any.
    if (prev != null) {
      System.out.println(prev);
    }
  }
  // @exclude

  public static void main(String[] args) {
    List<LineSegment> A = new ArrayList<>();
    A.add(new LineSegment(0, 4, 0, 0));
    A.add(new LineSegment(1, 3, 1, 2));
    A.add(new LineSegment(2, 7, 2, 1));
    A.add(new LineSegment(4, 5, 3, 3));
    A.add(new LineSegment(5, 7, 3, 0));
    A.add(new LineSegment(6, 10, 0, 2));
    A.add(new LineSegment(8, 9, 0, 1));
    A.add(new LineSegment(9, 18, 4, 0));
    A.add(new LineSegment(11, 13, 3, 2));
    A.add(new LineSegment(12, 15, 4, 1));
    A.add(new LineSegment(14, 15, 2, 2));
    A.add(new LineSegment(16, 17, 3, 2));
    for (LineSegment s : A) {
      System.out.println("line segment, left = " + s.left + ", right = "
          + s.right + ", color = " + s.color + ", height = " + s.height);
    }
    calculateViewFromAbove(A);
  }
}
