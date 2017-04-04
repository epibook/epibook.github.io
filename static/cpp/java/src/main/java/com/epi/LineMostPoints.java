// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

package com.epi;

import java.awt.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.Set;

public class LineMostPoints {
  // @include
  private static class Line {
    private static class Rational {
      public Integer numerator;
      public Integer denominator;

      public Rational(Integer numerator, Integer denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
      }
    }

    public static Rational getCanonicalFractional(int a, int b) {
      int gcd = BigInteger.valueOf(a).gcd(BigInteger.valueOf(b)).intValue();
      a /= gcd;
      b /= gcd;
      return b < 0 ? new Rational(-a, -b) : new Rational(a, b);
    }

    // Slope is a rational number. Note that if the line is parallel to y-axis
    // that we store 1/0.
    private Rational slope;
    // Intercept is a rational number for the y-intercept unless
    // the line is parallel to y-axis in which case it is the x-intercept
    private Rational intercept;

    Line(Point a, Point b) {
      if (a.x != b.x) {
        slope = getCanonicalFractional(b.y - a.y, b.x - a.x);
      } else {
        slope = new Rational(1, 0);
      }
      if (a.x != b.x) {
        intercept = getCanonicalFractional(b.x * a.y - a.x * b.y, b.x - a.x);
      } else {
        intercept = new Rational(a.x, 1);
      }
    }

    // @exclude
    public Rational getSlope() { return slope; }

    public Rational getIntercept() { return intercept; }

    // @include
    @Override
    public boolean equals(Object obj) {
      if (obj == null || !(obj instanceof Line)) {
        return false;
      }
      if (this == obj) {
        return true;
      }
      Line that = (Line)obj;
      return slope.equals(that.slope) && intercept.equals(that.intercept);
    }

    @Override
    public int hashCode() {
      return Objects.hash(slope.numerator, slope.denominator,
                          intercept.numerator, intercept.denominator);
    }
  }

  public static Line findLineWithMostPoints(List<Point> P) {
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
    Set<Point> lineMaxPoints
        = Collections.max(table.values(), new Comparator<Set<Point>>() {
            @Override
            public int compare(Set<Point> p1, Set<Point> p2) {
              if (p1 != null && p2 != null) {
                return Integer.compare(p1.size(), p2.size());
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
                             public int compare(
                                 Map.Entry<Line, Set<Point>> e1,
                                 Map.Entry<Line, Set<Point>> e2) {
                               if (e1 != null && e2 != null) {
                                 return Integer.compare(e1.getValue().size(),
                                                        e2.getValue().size());
                               }
                               return (e1 != null ? 1 : -1);
                             }
                           })
        .getKey();
  }
  //@exclude

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

  public static void main(String[] args) {
    Random rnd = new Random();

    for (int times = 0; times < 100; ++times) {
      System.out.println(times);
      int n;
      if (args.length == 1) {
        n = Integer.parseInt(args[0]);
      } else {
        // Needs to at least two points to form a line.
        n = rnd.nextInt(1000 - 1) + 2;
      }
      List<Point> points = new ArrayList<Point>(n);
      Set<Point> t = new HashSet<>();
      int idx = 0;
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
      System.out.println(l.getSlope().numerator + " " + l.getSlope().denominator
                         + " " + l.getIntercept().numerator + " "
                         + l.getIntercept().denominator);
    }
  }
}
