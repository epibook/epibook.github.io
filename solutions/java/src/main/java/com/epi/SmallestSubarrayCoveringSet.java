// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.
// @author Andrey Pavlov

package com.epi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

import com.epi.utils.Pair;

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
      ArrayList<String> A, ArrayList<String> Q) {
    HashSet<String> dict = new HashSet<String>(Q);
    HashMap<String, Integer> countQ = new HashMap<String, Integer>();
    int l = 0, r = 0;
    Pair<Integer, Integer> res = new Pair<Integer, Integer>(-1, -1);
    while (r < A.size()) {
      // Keep moving r until it reaches end or countQ has |Q| items.
      while (r < A.size() && countQ.size() < Q.size()) {
        if (dict.contains(A.get(r))) {
          countQ.put(A.get(r),
              countQ.containsKey(A.get(r)) ? countQ.get(A.get(r)) + 1 : 1);
        }
        ++r;
      }

      if (countQ.size() == Q.size() && // found |Q| keywords.
          ((res.getFirst() == -1 && res.getSecond() == -1) || r - 1 - l < res
              .getSecond() - res.getFirst())) {
        res.setFirst(l);
        res.setSecond(r - 1);
      }

      // Keep moving l until it reaches end or countQ has less |Q| items.
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
  public static int checkAns(ArrayList<String> A, ArrayList<String> Q) {
    HashSet<String> dict = new HashSet<String>();
    for (String s : Q) {
      dict.add(s);
    }

    Pair<Integer, Integer> ans = new Pair<Integer, Integer>(0, A.size() - 1);
    for (int l = 0; l < A.size(); ++l) {
      HashMap<String, Integer> count = new HashMap<String, Integer>();
      for (int r = l; r < A.size() && r - l < ans.getSecond() - ans.getFirst(); ++r) {
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
      ArrayList<String> A = new ArrayList<String>();
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
      HashSet<String> dict = new HashSet<String>(A);
      int m = gen.nextInt(dict.size()) + 1;
      ArrayList<String> Q = new ArrayList<String>();
      Iterator<String> it = dict.iterator();
      while (it.hasNext()) {
        Q.add(it.next());
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
      for (int i = 0; i < Q.size(); ++i) {
        dict.add(Q.get(i));
      }
      for (int i = res.getFirst(); i <= res.getSecond(); ++i) {
        if (dict.contains(A.get(i))) {
          dict.remove(A.get(i));
        }
      }
      assert (dict.isEmpty() == true);
      Pair<Integer, Integer> res2 = SmallestSubarrayCoveringSetStream
          .findSmallestSubarrayCoveringSubset(A, Q);
      System.out.println(res2.getFirst() + ", " + res2.getSecond());
      dict.clear();
      for (int i = 0; i < Q.size(); ++i) {
        dict.add(Q.get(i));
      }
      for (int i = res.getFirst(); i <= res.getSecond(); ++i) {
        if (dict.contains(A.get(i))) {
          dict.remove(A.get(i));
        }
      }
      assert (dict.isEmpty() == true);
      assert (res.getSecond() - res.getFirst() == res2.getSecond()
          - res2.getFirst());
      assert (res.getSecond() - res.getFirst() == checkAns(A, Q));
    }
  }
}
