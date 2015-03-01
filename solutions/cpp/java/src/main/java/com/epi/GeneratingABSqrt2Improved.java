package com.epi;

import java.util.*;

public class GeneratingABSqrt2Improved {
  // @include
  public static class Num implements Comparable<Num> {
    public int a, b;
    public double val;

    public Num(int a, int b) {
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

      Num that = (Num) o;
      return a == that.a && b == that.b;
    }

    // Hash function for Num.
    @Override
    public int hashCode() {
      int result = a;
      result = 31 * result + b;
      return result;
    }

    @Override
    public int compareTo(Num o) {
      return Double.valueOf(val).compareTo(o.val);
    }
    // @include
  }

  public static List<Num> generateFirstK(int k) {
    List<Num> result = new ArrayList<>(); // Stores the first-k Num.
    result.add(new Num(0, 0));
    int i = 0, j = 0;
    for (int n = 0; n < k; ++n) {
      Num x = new Num(result.get(i).a + 1, result.get(i).b);
      Num y = new Num(result.get(j).a, result.get(j).b + 1);
      if (x.val < y.val) {
        ++i;
        result.add(x);
      } else if (x.val > y.val) {
        ++j;
        result.add(y);
      } else { // x == y.
        ++i;
        ++j;
        result.add(x);
      }
    }
    return result;
  }
  // @exclude

  public static List<Num> golden(int k) {
    SortedSet<Num> minHeap = new TreeSet<>();
    List<Num> smallest = new ArrayList<>();
    Set<Num> hash = new HashSet<>();

    // Initial for 0 + 0 * sqrt(2).
    minHeap.add(new Num(0, 0));
    hash.add(new Num(0, 0));

    while (smallest.size() < k) {
      Num s = minHeap.first();
      smallest.add(s);
      hash.remove(s);
      minHeap.remove(s);

      // Add the next two numbers derived from s.
      Num c1 = new Num(s.a + 1, s.b);
      Num c2 = new Num(s.a, s.b + 1);
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
      List<Num> ans = generateFirstK(k);
      for (int i = 0; i < ans.size(); ++i) {
        System.out.println(ans.get(i).a + " " + ans.get(i).b + " "
            + ans.get(i).val);
        if (i > 0) {
          assert (ans.get(i).val >= ans.get(i - 1).val);
        }
      }
      List<Num> goldRes = golden(k);
      for (int i = 0; i < k; ++i) {
        assert (ans.get(i).equals(goldRes.get(i)));
      }
    }
  }
}
