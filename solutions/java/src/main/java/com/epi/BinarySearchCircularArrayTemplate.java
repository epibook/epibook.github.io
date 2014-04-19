package com.epi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class BinarySearchCircularArrayTemplate {
  // @include
  public static int searchSmallest(ArrayList<Integer> A) {
    int l = 0, r = A.size() - 1;
    while (l < r) {
      int m = l + ((r - l) >> 1);
      if (A.get(m) > A.get(r)) {
        l = m + 1;
      } else { // A.get(m) <= A.get(r)
        r = m;
      }
    }
    return l;
  }

  // @exclude

  public static void main(String[] args) {
    Random r = new Random();
    for (int times = 0; times < 1000; ++times) {
      int n;
      if (args.length == 1) {
        n = Integer.parseInt(args[0]);
      } else {
        n = r.nextInt(10000) + 1;
      }
      ArrayList<Integer> A = new ArrayList<Integer>();
      HashSet<Integer> table = new HashSet<Integer>();
      for (int i = 0; i < n; ++i) {
        while (true) {
          int x = r.nextInt(100001);
          if (table.add(x)) {
            A.add(x);
            break;
          }
        }
      }
      Collections.sort(A);
      int shift = r.nextInt(n);
      Collections.reverse(A);
      Collections.reverse(A.subList(0, shift + 1));
      Collections.reverse(A.subList(shift + 1, A.size()));
      // System.out.println(A);
      assert ((shift + 1) % n == searchSmallest(A));
    }
    // hand-made tests.
    ArrayList<Integer> A = new ArrayList<Integer>();
    A.add(2);
    A.add(3);
    A.add(4);
    assert (0 == searchSmallest(A));
    A.clear();
    A.add(100);
    A.add(101);
    A.add(102);
    A.add(2);
    A.add(5);
    assert (3 == searchSmallest(A));
    A.clear();
    A.add(10);
    A.add(20);
    A.add(30);
    A.add(40);
    A.add(5);
    assert (4 == searchSmallest(A));
  }
}
