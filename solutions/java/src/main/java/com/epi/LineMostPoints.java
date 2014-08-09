// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.
// @author Andrey Pavlov

package com.epi;

import com.epi.utils.Pair;

import java.awt.*;
import java.util.*;
import java.util.List;

import static com.epi.utils.Utils.getCanonicalFractional;
import static com.epi.utils.Utils.nullEqual;

class Line {
  private Pair<Integer, Integer> slope;
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

  public Pair<Integer, Integer> getSlope() {
    return slope;
  }

  public Pair<Integer, Integer> getIntercept() {
    return intercept;
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof Line) {
      Line l = (Line) o;
      return nullEqual(slope, l.getSlope())
          && nullEqual(intercept, l.getIntercept());
    }

    return false;
  }

  @Override
  public int hashCode() {
    return slope.getFirst() ^ slope.getSecond() ^ intercept.getFirst()
        ^ intercept.getSecond();
  }
}

public class LineMostPoints {

  private static int check(List<Point> P) {
    int maxCount = 0;
    for (int i = 0; i < P.size(); ++i) {
      for (int j = i + 1; j < P.size(); ++j) {
        int count = 2;
        Line temp = new Line(P.get(i), P.get(j));
        for (int k = j + 1; k < P.size(); ++k) {
          if (new Line(P.get(i), P.get(k)).equals(temp)) {
            ++count;
          }
        }
        maxCount = Math.max(maxCount, count);
      }
    }

    return maxCount;
  }

  // @include
  private static Line findLineWithMostPoints(List<Point> P) {
    // Adds all possible lines into hash table.
    Map<Line, Set<Point>> table = new HashMap<>();
    for (int i = 0; i < P.size(); ++i) {
      for (int j = i + 1; j < P.size(); ++j) {
        Line l = new Line(P.get(i), P.get(j));

        Set<Point> s1 = table.get(l);
        if (s1 == null) {
          s1 = new HashSet<>();
          table.put(l, s1);
        }
        s1.add(P.get(i));

        Set<Point> s2 = table.get(l);
        if (s2 == null) {
          s2 = new HashSet<>();
          table.put(l, s2);
        }
        s2.add(P.get(j));
      }
    }

    //@exclude
    Set<Point> lineMaxPoints = Collections.max(table.values(),
        new Comparator<Set<Point>>() {
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
        }
    );

    int res = check(P);
    assert (res == lineMaxPoints.size());
    // Return the line with most points have passed.

    //@include
    return Collections.max(table.entrySet(),
        new Comparator<Map.Entry<Line, Set<Point>>>() {
          @Override
          public int compare(Map.Entry<Line, Set<Point>> e1,
                             Map.Entry<Line, Set<Point>> e2) {
            if (e1 != null && e2 != null) {
              return new Integer(e1.getValue().size()).compareTo(e2.getValue()
                  .size());
            } else if (e1 != null) {
              return 1;
            } else {
              return -1;
            }
          }
        }
    ).getKey();
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
      List<Point> points = new ArrayList<>();
      Set<Point> t = new HashSet<>();
      do {
        Point p = new Point(rnd.nextInt(999), rnd.nextInt(999));
        if (!t.contains(p)) {
          points.add(p);
          t.add(p);
        }
      } while (t.size() < n);

      /*
       * for (int i = 0; i < points.size(); i++) {
       * System.out.println(points.get(i).x + ", " + points.get(i).y); }
       */
      Line l = findLineWithMostPoints(points);
      System.out.println(l.getSlope().getFirst() + " "
          + l.getSlope().getSecond() + " " + l.getIntercept().getFirst() + " "
          + l.getIntercept().getSecond());
    }
  }
}
