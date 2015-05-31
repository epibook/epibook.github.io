package com.epi;

import java.util.*;

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
    // @exclude

    // Equal function for hash.
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

    // Hash function for ABSqrt2.
    @Override
    public int hashCode() {
      int result = a;
      result = 31 * result + b;
      return result;
    }

    @Override
    public int compareTo(ABSqrt2 o) {
      return Double.valueOf(val).compareTo(o.val);
    }
    // @include
  }

  public static List<ABSqrt2> generateFirstKABSqrt2(int k) {
    List<ABSqrt2> result = new ArrayList<>(); // Stores the first-k ABSqrt2.
    result.add(new ABSqrt2(0, 0));
    int i = 0, j = 0;
    for (int n = 1; n < k; ++n) {
      ABSqrt2 resultIPlus1 = new ABSqrt2(result.get(i).a + 1, result.get(i).b);
      ABSqrt2 resultJPlusSqrt2 =
          new ABSqrt2(result.get(j).a, result.get(j).b + 1);
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
    SortedSet<ABSqrt2> minHeap = new TreeSet<>();
    List<ABSqrt2> smallest = new ArrayList<>();
    Set<ABSqrt2> hash = new HashSet<>();

    // Initial for 0 + 0 * sqrt(2).
    minHeap.add(new ABSqrt2(0, 0));
    hash.add(new ABSqrt2(0, 0));

    while (smallest.size() < k) {
      ABSqrt2 s = minHeap.first();
      smallest.add(s);
      hash.remove(s);
      minHeap.remove(s);

      // Add the next two numbers derived from s.
      ABSqrt2 c1 = new ABSqrt2(s.a + 1, s.b);
      ABSqrt2 c2 = new ABSqrt2(s.a, s.b + 1);
      if (hash.add(c1)) {
        minHeap.add(c1);
      }
      if (hash.add(c2)) {
        minHeap.add(c2);
      }
    }
    return smallest;
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
      assert(ans.size() == k);
      for (int i = 0; i < ans.size(); ++i) {
        System.out.println(ans.get(i).a + " " + ans.get(i).b + " " +
                           ans.get(i).val);
        if (i > 0) {
          assert(ans.get(i).val >= ans.get(i - 1).val);
        }
      }
      List<ABSqrt2> goldRes = golden(k);
      ans.equals(goldRes);
      /*
      for (int i = 0; i < k; ++i) {
        assert (ans.get(i).equals(goldRes.get(i)));
      }
      */
    }
  }
}
