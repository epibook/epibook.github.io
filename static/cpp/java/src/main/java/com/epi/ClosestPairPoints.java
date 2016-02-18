package com.epi;

import com.epi.utils.Pair;

import java.util.*;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class ClosestPairPoints {
  private static class Tuple<A, B, C> {
    public A a;
    public B b;
    public C c;

    public Tuple() {
    }

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
    public String toString() {
      return "(" + x + ", " + y + ")";
    }
    // @include
  }

  public static Pair<Point, Point> findClosestPairPoints(List<Point> P) {
    Collections.sort(P, new Comparator<Point>() {
      @Override
      public int compare(Point o1, Point o2) {
        return Integer.valueOf(o1.x).compareTo(o2.x);
      }
    });
    Tuple<Point, Point, Double> ret = findClosestPairPointsHelper(P, 0,
        P.size());
    return new Pair<>(ret.a, ret.b);
  }

  // Returns the closest two points and its distance as a tuple.
  private static Tuple<Point, Point, Double> findClosestPairPointsHelper(
      List<Point> points, int s, int e) {
    if (e - s <= 3) { // Brute-force to find answer if there are <= 3 points.
      return bruteForce(points, s, e);
    }

    int mid = (e + s) / 2;
    Tuple<Point, Point, Double> lRet = findClosestPairPointsHelper(points, s,
        mid);
    Tuple<Point, Point, Double> rRet = findClosestPairPointsHelper(points, mid,
        e);
    Tuple<Point, Point, Double> minLR = lRet.c < rRet.c ? lRet : rRet;

    // Stores the points whose x-dis < min_d.
    List<Point> remain = new ArrayList<>();

    for (Point p : points) {
      if (Math.abs(p.x - points.get(mid).x) < minLR.c) {
        remain.add(p);
      }
    }

    Tuple<Point, Point, Double> midRet = findClosestPairInRemain(remain,
        minLR.c);
    return midRet.c < minLR.c ? midRet : minLR;
  }

  // Returns the closest two points and the distance between them.
  private static Tuple<Point, Point, Double> bruteForce(List<Point> P, int s,
                                                        int e) {
    Tuple<Point, Point, Double> ret = new Tuple<>();
    ret.c = Double.MAX_VALUE;
    for (int i = s; i < e; ++i) {
      for (int j = i + 1; j < e; ++j) {
        double dis = distance(P.get(i), P.get(j));
        if (dis < ret.c) {
          ret = new Tuple<>(P.get(i), P.get(j), dis);
        }
      }
    }
    return ret;
  }

  // Returns the closest two points and its distance as a tuple.
  private static Tuple<Point, Point, Double> findClosestPairInRemain(
      List<Point> P, double d) {
    Collections.sort(P, new Comparator<Point>() {
      @Override
      public int compare(Point o1, Point o2) {
        return Integer.valueOf(o1.y).compareTo(o2.y);
      }
    });

    // At most six points in P.
    Tuple<Point, Point, Double> ret = new Tuple<>();
    ret.c = Double.MAX_VALUE;
    for (int i = 0; i < P.size(); ++i) {
      for (int j = i + 1; j < P.size() && P.get(j).y - P.get(i).y < d; ++j) {
        double dis = distance(P.get(i), P.get(j));
        if (dis < ret.c) {
          ret = new Tuple<>(P.get(i), P.get(j), dis);
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
      Tuple<Point, Point, Double> q = bruteForce(points, 0, points.size());
      System.out.println("p = " + p + ", dis = "
          + distance(p.getFirst(), p.getSecond()));
      System.out.println("q = " + q.a + " " + q.b + ", dis = "
          + distance(q.a, q.b));
      assert (distance(p.getFirst(), p.getSecond()) == q.c);
    }
  }
}
