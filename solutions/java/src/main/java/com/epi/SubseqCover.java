// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

package com.epi;

import com.epi.utils.Pair;

import java.util.*;

class SubseqCover {

  public static String randString(int len) {
    StringBuilder sb = new StringBuilder();
    Random gen = new Random();
    while (len-- != 0) {
      sb.append((char) (gen.nextInt('z' + 1 - 'a') + 'a'));
    }
    return sb.toString();
  }

  // @include
  public static Pair<Integer, Integer> findSmallestSequentiallyCoveringSubset(
      String[] A, String[] Q) {

    // Stores the order of each Q[i].
    Map<String, Integer> K = new HashMap<>();

    int[] L = new int[Q.length];
    int[] D = new int[Q.length];

    // Initializes L, D, and K.
    for (int i = 0; i < Q.length; ++i) {
      L[i] = -1;
      D[i] = Integer.MAX_VALUE;
      K.put(Q[i], i);
    }

    Pair<Integer, Integer> res = new Pair<>(-1, A.length);

    for (int i = 0; i < A.length; ++i) {
      Integer it = K.get(A[i]);
      if (it != null) {
        if (it == 0) { // First one, no predecessor.
          D[0] = 1; // Base condition.
        } else if (D[it - 1] != Integer.MAX_VALUE) {
          D[it] = i - L[it - 1] + D[it - 1];
        }
        L[it] = i;

        if (it == Q.length - 1
            && D[D.length - 1] < res.getSecond() - res.getFirst() + 1) {
          res.setFirst(i - D[D.length - 1] + 1);
          res.setSecond(i);
        }
      }
    }
    return res;
  }
  // @exclude

  public static void smallTest() {
    String[] a3 = new String[]{"0", "1", "2",
        "3", "4", "5", "6", "7", "8", "9", "2", "4", "6", "10", "10", "10",
        "3", "2", "1", "0"};
    String[] subseq4 = new String[]{"0", "2", "9", "4", "6"};
    Pair<Integer, Integer> rr = findSmallestSequentiallyCoveringSubset(a3, subseq4);
    assert (rr.getFirst() == 0 && rr.getSecond() == 12);
  }

  public static void main(String[] args) {
    smallTest();
    Random gen = new Random();
    for (int times = 0; times < 1000; ++times) {
      int n;
      if (args.length == 1) {
        n = Integer.parseInt(args[0]);
      } else {
        n = gen.nextInt(10000) + 1;
      }
      String[] A = new String[n];
      for (int i = 0; i < n; i++) {
        A[i] = randString(gen.nextInt(5) + 1);
      }
      Set<String> dict = new HashSet<>(Arrays.asList(A));

      System.out.print("A = ");
      for (int i = 0; i < A.length; i++) {
        if (i != A.length - 1) {
          System.out.print(A[i] + ",");
        } else {
          System.out.print(A[i]);
        }
      }
      System.out.println("");
      int m = gen.nextInt(Math.min(dict.size(), 10)) + 1;
      String[] Q = new String[m];
      Iterator<String> it = dict.iterator();
      for (int i = 0; i < m; i++) {
        if (it.hasNext()) {
          Q[i] = it.next();
        }
      }
      System.out.print("Q = ");
      for (int i = 0; i < Q.length; i++) {
        if (i != Q.length - 1) {
          System.out.print(Q[i] + ",");
        } else {
          System.out.print(Q[i]);
        }
      }
      System.out.println("");

      Pair<Integer, Integer> res
          = findSmallestSequentiallyCoveringSubset(A, Q);
      System.out.println(res.getFirst() + ", " + res.getSecond());
      if (res.getFirst() != -1 && res.getSecond() != Q.length) {
        if (!res.getFirst().equals(res.getSecond())) {
          System.out.println(res.getFirst() + ", " + res.getSecond());
        }
        dict.clear();
        dict.addAll(Arrays.asList(Q));
        for (int i = res.getFirst(); i <= res.getSecond(); ++i) {
          if (dict.contains(A[i])) {
            dict.remove(A[i]);
          }
        }
        assert (dict.isEmpty());
      }
    }
  }
}
