package com.epi;

import java.util.*;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class GeneratingABSqrt2 {
  // @include
  public static class Num implements Comparable<Num> {
    private int a, b;
    private double val;

    public Num(int aVal, int bVal) {
      this.a = aVal;
      this.b = bVal;
      val = a + b * Math.sqrt(2);
    }

    public int getA() {
      return a;
    }

    public void setA(int aVal) {
      this.a = aVal;
    }

    public int getB() {
      return b;
    }

    public void setB(int bVal) {
      this.b = bVal;
    }

    public double getVal() {
      return val;
    }

    public void setVal(double val) {
      this.val = val;
    }

    @Override
    public int compareTo(Num o) {
      return Double.valueOf(val).compareTo(o.getVal());
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }

      Num that = (Num) o;
      return a == that.a && b == that.b;
    }

    @Override
    public String toString() {
      return a + " " + b + " " + val;
    }
  }

  public static List<Num> generateFirstK(int k) {
    List<Num> smallest = new ArrayList<>();
    TreeSet<Num> set = new TreeSet<>();

    // Initial for 0 + 0 * sqrt(2).
    set.add(new Num(0, 0));

    while (smallest.size() < k) {
      Num s = set.first();
      smallest.add(s);

      // Add the next two numbers derived from s.
      Num c1 = new Num(s.a + 1, s.b);
      set.add(c1);
      Num c2 = new Num(s.a, s.b + 1);
      set.add(c2);
      set.remove(s);
    }
    return smallest;
  }
  // @exclude

  public static void main(String[] args) {
    Random r = new Random();
    for (int times = 0; times < 1000; ++times) {
      int k;
      if (args.length == 1) {
        k = Integer.parseInt(args[0]);
      } else {
        k = r.nextInt(10000) + 1;
      }
      List<Num> ans = generateFirstK(k);
      for (int i = 0; i < ans.size(); ++i) {
        System.out.println(ans.get(i));
        if (i > 0) {
          assert (ans.get(i).getVal() >= ans.get(i - 1).getVal());
        }
      }
    }
  }
}
