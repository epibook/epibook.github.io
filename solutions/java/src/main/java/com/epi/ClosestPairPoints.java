package com.epi;

import com.epi.utils.Pair;

import java.util.*;

public class ClosestPairPoints {
  private static class Tuple<A, B, C> {
    public A a;
    public B b;
    public C c;

    public Tuple() {}

    public Tuple(A aVal, B bVal, C cVal) {
      this.a = aVal;
      this.b = bVal;
      this.c = cVal;
    }
  }

  // @include
  public static class Point {
    public int x, y;

    public Point(int x, int y) {
      this.x = x;
      this.y = y;
    }

    // @exclude
    public String toString() { return "(" + x + ", " + y + ")"; }
    // @include
  }

  public static Pair<Point, Point> findClosestPairPoints(List<Point> points) {
    Collections.sort(points, new Comparator<Point>() {
      @Override
      public int compare(Point o1, Point o2) {
        return Integer.valueOf(o1.x).compareTo(o2.x);
      }
    });
    Tuple<Point, Point, Double> closestTwoPointsWithDistance =
        findClosestPairPointsHelper(points, 0, points.size());
    return new Pair<>(closestTwoPointsWithDistance.a,
                      closestTwoPointsWithDistance.b);
  }

  // Returns the closest two points and their distance as a tuple in
  // points[begin : end - 1].
  private static Tuple<Point, Point, Double> findClosestPairPointsHelper(
      List<Point> points, int begin, int end) {
    if (end - begin <= 3) { // Switch to brute-force.
      return solveByEnumerateAllPairs(points, begin, end);
    }

    int mid = (end + begin) / 2;
    Tuple<Point, Point, Double> result0 =
        findClosestPairPointsHelper(points, begin, mid);
    Tuple<Point, Point, Double> result1 =
        findClosestPairPointsHelper(points, mid, end);
    Tuple<Point, Point, Double> bestResultInSubsets =
        result0.c < result1.c ? result0 : result1;

    // Stores the points whose x-dis < min_d.
    List<Point> remain = new ArrayList<>();

    for (Point p : points) {
      if (Math.abs(p.x - points.get(mid).x) < bestResultInSubsets.c) {
        remain.add(p);
      }
    }

    Tuple<Point, Point, Double> midRet =
        findClosestPairInRemain(remain, bestResultInSubsets.c);
    return midRet.c < bestResultInSubsets.c ? midRet : bestResultInSubsets;
  }

  // Returns the closest two points and the distance between them.
  private static Tuple<Point, Point, Double> solveByEnumerateAllPairs(
      List<Point> points, int begin, int end) {
    Tuple<Point, Point, Double> ret = new Tuple<>();
    ret.c = Double.MAX_VALUE;
    for (int i = begin; i < end; ++i) {
      for (int j = i + 1; j < end; ++j) {
        double dis = distance(points.get(i), points.get(j));
        if (dis < ret.c) {
          ret = new Tuple<>(points.get(i), points.get(j), dis);
        }
      }
    }
    return ret;
  }

  // Returns the closest two points and its distance as a tuple.
  private static Tuple<Point, Point, Double> findClosestPairInRemain(
      List<Point> remain, double d) {
    Collections.sort(remain, new Comparator<Point>() {
      @Override
      public int compare(Point o1, Point o2) {
        return Integer.valueOf(o1.y).compareTo(o2.y);
      }
    });

    // At most six points in remain.
    Tuple<Point, Point, Double> ret = new Tuple<>();
    ret.c = Double.MAX_VALUE;
    for (int i = 0; i < remain.size(); ++i) {
      for (int j = i + 1;
           j < remain.size() && remain.get(j).y - remain.get(i).y < d; ++j) {
        double dis = distance(remain.get(i), remain.get(j));
        if (dis < ret.c) {
          ret = new Tuple<>(remain.get(i), remain.get(j), dis);
        }
      }
    }
    return ret;
  }

  private static double distance(Point a, Point b) {
    return Math.sqrt((a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y));
  }
  // @exclude

  public static void main(String[] args) {
    Random r = new Random();
    for (int times = 0; times < 1000; ++times) {
      int n;
      if (args.length == 1) {
        n = Integer.parseInt(args[0]);
      } else {
        n = r.nextInt(1000) + 1;
      }
      System.out.println("num of points = " + n);
      List<Point> points = new ArrayList<>(n);
      for (int i = 0; i < n; ++i) {
        points.add(new Point(r.nextInt(10000), r.nextInt(10000)));
      }
      Pair<Point, Point> p = findClosestPairPoints(points);
      Tuple<Point, Point, Double> q =
          solveByEnumerateAllPairs(points, 0, points.size());
      System.out.println("p = " + p + ", dis = " +
                         distance(p.getFirst(), p.getSecond()));
      System.out.println("q = " + q.a + " " + q.b + ", dis = " +
                         distance(q.a, q.b));
      assert(distance(p.getFirst(), p.getSecond()) == q.c);
    }
  }
}
