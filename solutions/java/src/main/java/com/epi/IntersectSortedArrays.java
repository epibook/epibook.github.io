package com.epi;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class IntersectSortedArrays {
  public static void main(String[] args) {
    int n = 100;
    int l = 1000;
    if (args.length > 0) {
      n = Integer.parseInt(args[0]);
    }
    if (args.length > 1) {
      l = Integer.parseInt(args[1]);
    }
    Random rnd = new Random(0);
    for (int i = 0; i < n; i++) {
      List<Integer> a = new ArrayList<>();
      List<Integer> b = new ArrayList<>();
      for (int j = 0; j < l; j++) {
        a.add(rnd.nextInt());
        b.add(rnd.nextInt());
      }
      List<Integer> r1 = IntersectSortedArrays1.intersectTwoSortedArrays(a, b);
      List<Integer> r2 = IntersectSortedArrays2.intersectTwoSortedArrays(a, b);
      List<Integer> r3 = IntersectSortedArrays3.intersectTwoSortedArrays(a, b);
      assert r1.equals(r2);
      assert r2.equals(r3);
    }
  }
}
