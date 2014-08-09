// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.
// @author Andrey Pavlov

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
      List<String> A, List<String> Q) {

    // Stores the order of each Q[i].
    Map<String, Integer> K = new HashMap<>();

    List<Integer> L = new ArrayList<>(Q.size());
    List<Integer> D = new ArrayList<>(Q.size());

    // Initializes L, D, and K.
    for (int i = 0; i < Q.size(); ++i) {
      L.add(-1);
      D.add(Integer.MAX_VALUE);
      K.put(Q.get(i), i);
    }

    Pair<Integer, Integer> res = new Pair<>(-1, A.size());

    for (int i = 0; i < A.size(); ++i) {
      Integer it = K.get(A.get(i));
      if (it != null) {
        if (it == 0) { // First one, no predecessor.
          D.set(0, 1); // Base condition.
        } else if (D.get(it - 1) != Integer.MAX_VALUE) {
          D.set(it, i - L.get(it - 1) + D.get(it - 1));
        }
        L.set(it, i);

        if (it == Q.size() - 1
            && D.get(D.size() - 1) < res.getSecond() - res.getFirst() + 1) {
          res.setFirst(i - D.get(D.size() - 1) + 1);
          res.setSecond(i);
        }
      }
    }
    return res;
  }
  // @exclude

  public static void smallTest() {
    List<String> a3 = new ArrayList<>(Arrays.asList("0", "1", "2",
        "3", "4", "5", "6", "7", "8", "9", "2", "4", "6", "10", "10", "10",
        "3", "2", "1", "0"));
    List<String> subseq4 = new ArrayList<>(Arrays.asList("0", "2",
        "9", "4", "6"));
    Pair<Integer, Integer> rr = findSmallestSequentiallyCoveringSubset(a3,
        subseq4);
    assert (rr.getFirst() == 0 && rr.getSecond() == 12);
  }

  public static void main(String[] args) {
    smallTest();
    Random gen = new Random();
    for (int times = 0; times < 1000; ++times) {
      int n;
      List<String> A = new ArrayList<>();
      if (args.length == 1) {
        n = Integer.parseInt(args[0]);
      } else {
        n = gen.nextInt(10000) + 1;
      }
      for (int i = 0; i < n; i++) {
        A.add(randString(gen.nextInt(5) + 1));
      }
      Set<String> dict = new HashSet<>(A);

      System.out.print("A = ");
      for (int i = 0; i < A.size(); i++) {
        if (i != A.size() - 1) {
          System.out.print(A.get(i) + ",");
        } else {
          System.out.print(A.get(i));
        }
      }
      System.out.println("");
      int m = gen.nextInt(Math.min(dict.size(), 10)) + 1;
      List<String> Q = new ArrayList<>();
      Iterator<String> it = dict.iterator();
      for (int i = 0; i < m; i++) {
        if (it.hasNext()) {
          Q.add(it.next());
        }
      }
      System.out.print("Q = ");
      for (int i = 0; i < Q.size(); i++) {
        if (i != Q.size() - 1) {
          System.out.print(Q.get(i) + ",");
        } else {
          System.out.print(Q.get(i));
        }
      }
      System.out.println("");

      Pair<Integer, Integer> res
          = findSmallestSequentiallyCoveringSubset(A, Q);
      System.out.println(res.getFirst() + ", " + res.getSecond());
      if (res.getFirst() != -1 && res.getSecond() != Q.size()) {
        if (!res.getFirst().equals(res.getSecond())) {
          System.out.println(res.getFirst() + ", " + res.getSecond());
        }
        dict.clear();
        dict.addAll(Q);
        for (int i = res.getFirst(); i <= res.getSecond(); ++i) {
          if (dict.contains(A.get(i))) {
            dict.remove(A.get(i));
          }
        }
        assert (dict.isEmpty());
      }
    }
  }
}
