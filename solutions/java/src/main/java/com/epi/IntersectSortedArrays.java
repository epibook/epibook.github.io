package com.epi;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class IntersectSortedArrays {

  public static void check(List<Integer> R1, List<Integer> R2,
                           List<Integer> R3) {
    assert (R1.size() == R2.size());
    assert (R2.size() == R3.size());
    for (Integer aR1 : R1) {
      int r1 = aR1;
      int r2 = aR1;
      int r3 = aR1;
      assert (r1 == r2);
      assert (r2 == r3);
    }
  }

  public static void main(String[] args) {
    int n = 100;
    int l = 1000;
    if (args.length > 0) {
      n = new Integer(args[0]);
    }
    if (args.length > 1) {
      l = new Integer(args[1]);
    }
    ArrayList<Integer> a = null;
    ArrayList<Integer> b = null;
    Random rnd = new Random(0);
    for (int i = 0; i < n; i++) {
      a = new ArrayList<>(l);
      b = new ArrayList<>(l);
      for (int j = 0; j < l; j++) {
        a.add(rnd.nextInt());
        b.add(rnd.nextInt());
      }
      List<Integer> r1 = IntersectSortedArrays1.intersect(a, b);
      List<Integer> r2 = IntersectSortedArrays2.intersect(a, b);
      List<Integer> r3 = IntersectSortedArrays3.intersect(a, b);
      check(r1, r2, r3);
    }
  }
}
