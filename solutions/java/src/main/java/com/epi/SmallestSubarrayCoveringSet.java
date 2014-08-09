// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.
// @author Andrey Pavlov

package com.epi;

import com.epi.utils.Pair;

import java.util.*;

class SmallestSubarrayCoveringSet {

  public static String randString(int len) {
    StringBuilder sb = new StringBuilder();
    Random gen = new Random();
    while (len-- != 0) {
      sb.append((char) (gen.nextInt('z' + 1 - 'a') + 'a'));
    }
    return sb.toString();
  }

  // @include
  public static Pair<Integer, Integer> findSmallestSubarrayCoveringSubset(
      final List<String> A, final List<String> Q) {
    Set<String> dict = new HashSet<>(Q);
    Map<String, Integer> countQ = new HashMap<>();
    int l = 0, r = 0;
    Pair<Integer, Integer> res = new Pair<>(-1, -1);
    while (r < A.size()) {
      // Keeps moving r until it reaches end or countQ has |Q| items.
      while (r < A.size() && countQ.size() < Q.size()) {
        if (dict.contains(A.get(r))) {
          countQ.put(A.get(r),
              countQ.containsKey(A.get(r)) ? countQ.get(A.get(r)) + 1 : 1);
        }
        ++r;
      }

      if (countQ.size() == Q.size() && // Found |Q| keywords.
          ((res.getFirst() == -1 && res.getSecond() == -1) || r - 1 - l < res
              .getSecond() - res.getFirst())) {
        res.setFirst(l);
        res.setSecond(r - 1);
      }

      // Keeps moving l until it reaches end or countQ has less |Q| items.
      while (l < r && countQ.size() == Q.size()) {
        if (dict.contains(A.get(l))) {
          int it = countQ.get(A.get(l));
          countQ.put(A.get(l), --it);
          if (it == 0) {
            countQ.remove(A.get(l));
            if ((res.getFirst() == -1 && res.getSecond() == -1)
                || r - 1 - l < res.getSecond() - res.getFirst()) {
              res.setFirst(l);
              res.setSecond(r - 1);
            }
          }
        }
        ++l;
      }
    }
    return res;
  }
  // @exclude

  // O(n^2) solution
  public static int checkAns(List<String> A, List<String> Q) {
    Set<String> dict = new HashSet<>();
    for (String s : Q) {
      dict.add(s);
    }

    Pair<Integer, Integer> ans = new Pair<>(0, A.size() - 1);
    for (int l = 0; l < A.size(); ++l) {
      Map<String, Integer> count = new HashMap<>();
      for (int r = l;
           r < A.size() && r - l < ans.getSecond() - ans.getFirst();
           ++r) {
        if (dict.contains(A.get(r))) {
          count.put(A.get(r),
              count.containsKey(A.get(r)) ? count.get(A.get(r)) + 1 : 1);
        }
        if (count.size() == Q.size()) {
          if (r - l < ans.getSecond() - ans.getFirst()) {
            ans.setFirst(l);
            ans.setSecond(r);
          }
          break;
        }
      }
      count.clear();
    }

    return ans.getSecond() - ans.getFirst();
  }

  public static void main(String[] args) {
    Random gen = new Random();
    for (int times = 0; times < 1000; ++times) {
      int n;
      List<String> A = new ArrayList<>();
      if (args.length == 1) {
        n = Integer.parseInt(args[0]);
      } else {
        n = gen.nextInt(10000) + 1;
      }
      for (int i = 0; i < n; ++i) {
        A.add(randString(gen.nextInt(10) + 1));
      }
      /*
       * for (int i = 0; i < A.size(); ++i) { cout << A[i] << ' '; } cout <<
       * endl;
       */
      Set<String> dict = new HashSet<>(A);
      int m = gen.nextInt(dict.size()) + 1;
      List<String> Q = new ArrayList<>();
      for (String aDict : dict) {
        Q.add(aDict);
        if (--m == 0) {
          break;
        }
      }

      /*
       * for (int i = 0; i < Q.size(); ++i) { cout << Q[i] << ' '; } cout <<
       * endl;
       */
      Pair<Integer, Integer> res = findSmallestSubarrayCoveringSubset(A, Q);
      System.out.println(res.getFirst() + ", " + res.getSecond());
      dict.clear();
      for (String aQ1 : Q) {
        dict.add(aQ1);
      }
      for (int i = res.getFirst(); i <= res.getSecond(); ++i) {
        if (dict.contains(A.get(i))) {
          dict.remove(A.get(i));
        }
      }
      assert (dict.isEmpty());
      Pair<Integer, Integer> res2 = SmallestSubarrayCoveringSetStream
          .findSmallestSubarrayCoveringSubset(A, Q);
      System.out.println(res2.getFirst() + ", " + res2.getSecond());
      dict.clear();
      for (String aQ : Q) {
        dict.add(aQ);
      }
      for (int i = res.getFirst(); i <= res.getSecond(); ++i) {
        if (dict.contains(A.get(i))) {
          dict.remove(A.get(i));
        }
      }
      assert (dict.isEmpty());
      assert (res.getSecond() - res.getFirst() == res2.getSecond()
          - res2.getFirst());
      assert (res.getSecond() - res.getFirst() == checkAns(A, Q));
    }
  }
}
