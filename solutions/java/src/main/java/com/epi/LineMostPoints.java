// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

package com.epi;

import com.epi.utils.Pair;

import java.awt.*;
import java.util.*;
import java.util.List;

import static com.epi.utils.Utils.getCanonicalFractional;
import static com.epi.utils.Utils.nullEqual;

class Line {
  // slope is a rational number. Note that if the line is parallel to y-axis
  // that we store 1/0.
  private Pair<Integer, Integer> slope;
  // intercept is a rational number for the y-intercept unless
  // the line is parallel to y-axis in which case it is the x-intercept
  private Pair<Integer, Integer> intercept;

  Line(Point a, Point b) {
    if (a.x != b.x) {
      slope = getCanonicalFractional(b.y - a.y, b.x - a.x);
    } else {
      slope = new Pair<>(1, 0);
    }
    if (a.x != b.x) {
      intercept = getCanonicalFractional(b.x * a.y - a.x * b.y, b.x - a.x);
    } else {
      intercept = new Pair<>(a.x, 1);
    }
  }

  public Pair<Integer, Integer> getSlope() { return slope; }

  public Pair<Integer, Integer> getIntercept() { return intercept; }

  @Override
  public boolean equals(Object o) {
    if (o instanceof Line) {
      Line l = (Line)o;
      return nullEqual(slope, l.getSlope()) &&
          nullEqual(intercept, l.getIntercept());
    }
    return false;
  }

  @Override
  public int hashCode() {
    return slope.getFirst() ^ slope.getSecond() ^ intercept.getFirst() ^
        intercept.getSecond();
  }
}

public class LineMostPoints {
  private static int check(Point[] P) {
    int maxCount = 0;
    for (int i = 0; i < P.length; ++i) {
      for (int j = i + 1; j < P.length; ++j) {
        int count = 2;
        Line temp = new Line(P[i], P[j]);
        for (int k = j + 1; k < P.length; ++k) {
          if (new Line(P[i], P[k]).equals(temp)) {
            ++count;
          }
        }
        maxCount = Math.max(maxCount, count);
      }
    }

    return maxCount;
  }

  // @include
  private static Line findLineWithMostPoints(Point[] P) {
    // Adds all possible lines into hash table.
    Map<Line, Set<Point>> table = new HashMap<>();
    for (int i = 0; i < P.length; ++i) {
      for (int j = i + 1; j < P.length; ++j) {
        Line l = new Line(P[i], P[j]);

        Set<Point> s1 = table.get(l);
        if (s1 == null) {
          s1 = new HashSet<>();
          table.put(l, s1);
        }
        s1.add(P[i]);

        Set<Point> s2 = table.get(l);
        if (s2 == null) {
          s2 = new HashSet<>();
          table.put(l, s2);
        }
        s2.add(P[j]);
      }
    }

    //@exclude
    Set<Point> lineMaxPoints =
        Collections.max(table.values(), new Comparator<Set<Point>>() {
          @Override
          public int compare(Set<Point> p1, Set<Point> p2) {
            if (p1 != null && p2 != null) {
              return new Integer(p1.size()).compareTo(p2.size());
            } else if (p1 != null) {
              return 1;
            } else {
              return -1;
            }
          }
        });

    int res = check(P);
    assert(res == lineMaxPoints.size());
    // Return the line with most points have passed.

    //@include
    return Collections.max(table.entrySet(),
                           new Comparator<Map.Entry<Line, Set<Point>>>() {
                             @Override
                             public int compare(Map.Entry<Line, Set<Point>> e1,
                                                Map.Entry<Line, Set<Point>> e2) {
                               if (e1 != null && e2 != null) {
                                 return new Integer(e1.getValue().size())
                                     .compareTo(e2.getValue().size());
                               } else if (e1 != null) {
                                 return 1;
                               } else {
                                 return -1;
                               }
                             }
                           })
        .getKey();
  }
  //@exclude

  public static void main(String[] args) {
    Random rnd = new Random();

    for (int times = 0; times < 100; ++times) {
      System.out.println(times);
      int n;
      if (args.length == 1) {
        n = Integer.parseInt(args[0]);
      } else {
        n = rnd.nextInt(1000 - 1) + 1;
      }
      Point[] points = new Point[n];
      Set<Point> t = new HashSet<>();
      int idx = 0;
      do {
        Point p = new Point(rnd.nextInt(999), rnd.nextInt(999));
        if (!t.contains(p)) {
          points[idx++] = p;
          t.add(p);
        }
      } while (t.size() < n);

      /*
       * for (int i = 0; i < points.size(); i++) {
       * System.out.println(points.get(i).x + ", " + points.get(i).y); }
       */
      Line l = findLineWithMostPoints(points);
      System.out.println(
          l.getSlope().getFirst() + " " + l.getSlope().getSecond() + " " +
          l.getIntercept().getFirst() + " " + l.getIntercept().getSecond());
    }
  }
}
