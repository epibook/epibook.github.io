package com.epi;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;

public class GeneratingABSqrt2Improved {
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
  }

  public static List<ABSqrt2> generateFirstKABSqrt2(int k) {
    // Will store the first k numbers of the form a + b sqrt(2).
    List<ABSqrt2> result = new ArrayList<>();
    result.add(new ABSqrt2(0, 0));
    int i = 0, j = 0;
    for (int n = 1; n < k; ++n) {
      ABSqrt2 resultIPlus1 = new ABSqrt2(result.get(i).a + 1, result.get(i).b);
      ABSqrt2 resultJPlusSqrt2
          = new ABSqrt2(result.get(j).a, result.get(j).b + 1);
      if (resultIPlus1.val < resultJPlusSqrt2.val) {
        ++i;
        result.add(resultIPlus1);
      } else if (resultIPlus1.val > resultJPlusSqrt2.val) {
        ++j;
        result.add(resultJPlusSqrt2);
      } else { // resultIPlus1 == resultJPlusSqrt2.
        ++i;
        ++j;
        result.add(resultIPlus1);
      }
    }
    return result;
  }
  // @exclude

  public static List<ABSqrt2> golden(int k) {
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
    simpleTest();
    Random r = new Random();
    for (int times = 0; times < 1000; ++times) {
      int k;
      if (args.length == 1) {
        k = Integer.parseInt(args[0]);
      } else {
        k = r.nextInt(10000) + 1;
      }
      List<ABSqrt2> ans = generateFirstKABSqrt2(k);
      assert(ans.size() == k);
      for (int i = 0; i < ans.size(); ++i) {
        System.out.println(ans.get(i).a + " " + ans.get(i).b + " "
                           + ans.get(i).val);
        if (i > 0) {
          assert(ans.get(i).val >= ans.get(i - 1).val);
        }
      }
      List<ABSqrt2> goldRes = golden(k);
      ans.equals(goldRes);
    }
  }
}
