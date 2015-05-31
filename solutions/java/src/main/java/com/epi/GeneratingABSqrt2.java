package com.epi;

import java.util.*;

// These numbers have very interesting property, and people called it ugly
// numbers. It is also called Quadratic integer rings.
public class GeneratingABSqrt2 {
  // @include
  public static class ABSqrt2 implements Comparable<ABSqrt2> {
    public int a, b;
    public double val;

    public ABSqrt2(int a, int b) {
      this.a = a;
      this.b = b;
      val = a + b * Math.sqrt(2);
    }

    @Override
    public int compareTo(ABSqrt2 o) {
      return Double.valueOf(val).compareTo(o.val);
    }
    // @exclude

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }

      ABSqrt2 that = (ABSqrt2)o;
      return a == that.a && b == that.b;
    }
    // @include
  }

  public static List<ABSqrt2> generateFirstK(int k) {
    TreeSet<ABSqrt2> candidates = new TreeSet<>();
    // Initial for 0 + 0 * sqrt(2).
    candidates.add(new ABSqrt2(0, 0));

    List<ABSqrt2> result = new ArrayList<>();
    while (result.size() < k) {
      ABSqrt2 nextSmallest = candidates.first();
      result.add(nextSmallest);

      // Add the next two numbers derived from nextSmallest.
      candidates.add(new ABSqrt2(nextSmallest.a + 1, nextSmallest.b));
      candidates.add(new ABSqrt2(nextSmallest.a, nextSmallest.b + 1));
      candidates.remove(nextSmallest);
    }
    return result;
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
      List<ABSqrt2> ans = generateFirstK(k);
      for (int i = 0; i < ans.size(); ++i) {
        System.out.println(ans.get(i).a + " " + ans.get(i).b + " " +
                           ans.get(i).val);
        if (i > 0) {
          assert(ans.get(i).val >= ans.get(i - 1).val);
        }
      }
    }
  }
}
