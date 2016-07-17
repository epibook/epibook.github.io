package com.epi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class ClosestPairPoints {
  // @include
  private static class PairOfPoints {
    public Point p1;
    public Point p2;

    public PairOfPoints(Point p1, Point p2) {
      this.p1 = p1;
      this.p2 = p2;
    }
  }

  private static class PairOfPointsWithDistance {
    public Point p1;
    public Point p2;
    public double distance;

    public PairOfPointsWithDistance(Point p1, Point p2, double distance) {
      this.p1 = p1;
      this.p2 = p2;
      this.distance = distance;
    }
  }

  public static class Point {
    public int x, y;

    public Point(int x, int y) {
      this.x = x;
      this.y = y;
    }
    // @exclude
    // clang-format off
    @Override
    public String toString() { return "(" + x + ", " + y + ")"; }
    // clang-format on
    // @include
  }

  public static PairOfPoints findClosestPairPoints(List<Point> points) {
    Collections.sort(points, new Comparator<Point>() {
      @Override
      public int compare(Point o1, Point o2) {
        return Integer.compare(o1.x, o2.x);
      }
    });
    PairOfPointsWithDistance closestTwoPointsWithDistance
        = findClosestPairPointsHelper(points, 0, points.size());
    return new PairOfPoints(closestTwoPointsWithDistance.p1,
                            closestTwoPointsWithDistance.p2);
  }

  // Returns the closest two points and their distance as a tuple in
  // points.subList(begin, end).
  private static PairOfPointsWithDistance findClosestPairPointsHelper(
      List<Point> points, int begin, int end) {
    if (end - begin <= 3) { // Switch to brute-force.
      return solveByEnumerateAllPairs(points, begin, end);
    }

    int mid = begin + (end - begin) / 2;
    PairOfPointsWithDistance result0
        = findClosestPairPointsHelper(points, begin, mid);
    PairOfPointsWithDistance result1
        = findClosestPairPointsHelper(points, mid, end);
    PairOfPointsWithDistance bestResultInSubsets
        = result0.distance < result1.distance ? result0 : result1;

    // Stores the points whose separation along the X-axis is less than min_d.
    List<Point> remain = new ArrayList<>();

    for (Point p : points) {
      if (Math.abs(p.x - points.get(mid).x) < bestResultInSubsets.distance) {
        remain.add(p);
      }
    }

    PairOfPointsWithDistance midRet
        = findClosestPairInRemain(remain, bestResultInSubsets.distance);
    return midRet.distance < bestResultInSubsets.distance ? midRet
                                                          : bestResultInSubsets;
  }

  // Returns the closest two points and the distance between them.
  private static PairOfPointsWithDistance solveByEnumerateAllPairs(
      List<Point> points, int begin, int end) {
    PairOfPointsWithDistance ret
        = new PairOfPointsWithDistance(null, null, Double.MAX_VALUE);
    for (int i = begin; i < end; ++i) {
      for (int j = i + 1; j < end; ++j) {
        double dis = distance(points.get(i), points.get(j));
        if (dis < ret.distance) {
          ret = new PairOfPointsWithDistance(points.get(i), points.get(j), dis);
        }
      }
    }
    return ret;
  }

  // Returns the closest two points and its distance as a tuple.
  private static PairOfPointsWithDistance findClosestPairInRemain(
      List<Point> remain, double d) {
    Collections.sort(remain, new Comparator<Point>() {
      @Override
      public int compare(Point o1, Point o2) {
        return Integer.compare(o1.y, o2.y);
      }
    });

    // At most six points in remain.
    PairOfPointsWithDistance ret
        = new PairOfPointsWithDistance(null, null, Double.MAX_VALUE);
    for (int i = 0; i < remain.size(); ++i) {
      for (int j = i + 1;
           j < remain.size() && remain.get(j).y - remain.get(i).y < d; ++j) {
        double dis = distance(remain.get(i), remain.get(j));
        if (dis < ret.distance) {
          ret = new PairOfPointsWithDistance(remain.get(i), remain.get(j), dis);
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
    for (int times = 0; times < 100; ++times) {
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
      PairOfPoints p = findClosestPairPoints(points);
      PairOfPointsWithDistance q
          = solveByEnumerateAllPairs(points, 0, points.size());
      System.out.println("p = " + p + ", dis = " + distance(p.p1, p.p2));
      System.out.println("q = " + q.p1 + " " + q.p2 + ", dis = "
                         + distance(q.p1, q.p2));
      assert(distance(p.p1, p.p2) == q.distance);
    }
  }
}
