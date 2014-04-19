// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.
// @author Andrey Pavlov

package com.epi;

import static com.epi.utils.Utils.getCanonicalFractional;
import static com.epi.utils.Utils.nullEqual;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;

import com.epi.utils.Pair;

class Line {
  private Pair<Integer, Integer> slope;
  private Pair<Integer, Integer> intercept;

  Line(Point a, Point b) {
    if (a.x != b.x) {
      slope = getCanonicalFractional(b.y - a.y, b.x - a.x);
    } else {
      slope = new Pair<Integer, Integer>(1, 0);
    }
    if (a.x != b.x) {
      intercept = getCanonicalFractional(b.x * a.y - a.x * b.y, b.x - a.x);
    } else {
      intercept = new Pair<Integer, Integer>(a.x, 1);
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

  private static int check(ArrayList<Point> P) {
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

  private static Line findLineWithMostPoints(ArrayList<Point> P) {
    // Add all possible lines into hash table.
    HashMap<Line, HashSet<Point>> table = new HashMap<Line, HashSet<Point>>();
    for (int i = 0; i < P.size(); ++i) {
      for (int j = i + 1; j < P.size(); ++j) {
        Line l = new Line(P.get(i), P.get(j));

        HashSet<Point> s1 = table.get(l);
        if (s1 == null) {
          s1 = new HashSet<Point>();
          table.put(l, s1);
        }
        s1.add(P.get(i));

        HashSet<Point> s2 = table.get(l);
        if (s2 == null) {
          s2 = new HashSet<Point>();
          table.put(l, s2);
        }
        s2.add(P.get(j));
      }
    }

    HashSet<Point> lineMaxPoints = Collections.max(table.values(),
        new Comparator<HashSet<Point>>() {
          @Override
          public int compare(HashSet<Point> p1, HashSet<Point> p2) {
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
    // cout << res << " " << lineMaxPoints.getSecond().size() << endl;
    assert (res == lineMaxPoints.size());
    // @include
    // Return the line with most points have passed.

    return Collections.max(table.entrySet(),
        new Comparator<Map.Entry<Line, HashSet<Point>>>() {
          @Override
          public int compare(Map.Entry<Line, HashSet<Point>> e1,
              Map.Entry<Line, HashSet<Point>> e2) {
            if (e1 != null && e2 != null) {
              return new Integer(e1.getValue().size()).compareTo(e2.getValue()
                  .size());
            } else if (e1 != null) {
              return 1;
            } else {
              return -1;
            }
          }
        }).getKey();
  }

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
      ArrayList<Point> points = new ArrayList<Point>();
      HashSet<Point> t = new HashSet<Point>();
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
