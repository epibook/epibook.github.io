package com.epi;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;

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

    // clang-format off
    @Override
    public int compareTo(ABSqrt2 o) { return Double.compare(val, o.val); }
    // clang-format on
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

    // clang-format off
    @Override
    public int hashCode() { return Objects.hash(a, b); }
    // clang-format on
    // @include
  }

  public static List<ABSqrt2> generateFirstKABSqrt2(int k) {
    SortedSet<ABSqrt2> candidates = new TreeSet<>();
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

  private static void simpleTest() {
    List<ABSqrt2> ans = generateFirstKABSqrt2(8);
    assert(0.0 == ans.get(0).val);
    assert(1.0 == ans.get(1).val);
    assert(Math.sqrt(2.0) == ans.get(2).val);
    assert(2.0 == ans.get(3).val);
    assert(1.0 + Math.sqrt(2.0) == ans.get(4).val);
    assert(2.0 * Math.sqrt(2.0) == ans.get(5).val);
    assert(3.0 == ans.get(6).val);
    assert(2.0 + Math.sqrt(2.0) == ans.get(7).val);
  }

  public static void main(String[] args) {
    Random r = new Random();
    for (int times = 0; times < 1000; ++times) {
      int k;
      if (args.length == 1) {
        k = Integer.parseInt(args[0]);
      } else {
        k = r.nextInt(10000) + 1;
      }
      List<ABSqrt2> ans = generateFirstKABSqrt2(k);
      for (int i = 0; i < ans.size(); ++i) {
        System.out.println(ans.get(i).a + " " + ans.get(i).b + " "
                           + ans.get(i).val);
        if (i > 0) {
          assert(ans.get(i).val >= ans.get(i - 1).val);
        }
      }
      ans = generateFirstKABSqrt2(8);
    }
  }
}
