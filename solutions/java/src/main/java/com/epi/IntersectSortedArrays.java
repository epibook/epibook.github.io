package com.epi;

import java.util.ArrayList;
import java.util.Random;

public class IntersectSortedArrays {

  public static void check(ArrayList<Integer> R1, ArrayList<Integer> R2,
      ArrayList<Integer> R3) {
    assert (R1.size() == R2.size());
    assert (R2.size() == R3.size());
    for (int i = 0; i < R1.size(); i++) {
      int r1 = R1.get(i);
      int r2 = R1.get(i);
      int r3 = R1.get(i);
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
      a = new ArrayList<Integer>(l);
      b = new ArrayList<Integer>(l);
      for (int j = 0; j < l; j++) {
        a.add(rnd.nextInt());
        b.add(rnd.nextInt());
      }
      ArrayList<Integer> r1 = IntersectSortedArrays1.intersect(a, b);
      ArrayList<Integer> r2 = IntersectSortedArrays2.intersect(a, b);
      ArrayList<Integer> r3 = IntersectSortedArrays3.intersect(a, b);
      check(r1, r2, r3);
    }
  }
}
