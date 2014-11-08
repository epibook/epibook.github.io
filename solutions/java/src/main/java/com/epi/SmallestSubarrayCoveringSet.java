// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

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
      String[] A, String[] Q) {
    Set<String> dict = new HashSet<>(Arrays.asList(Q));
    Map<String, Integer> countQ = new HashMap<>();
    int left = 0, right = 0;
    Pair<Integer, Integer> res = new Pair<>(-1, -1);
    while (right < A.length) {
      // Keeps moving right until it reaches end or countQ has |Q| items.
      while (right < A.length && countQ.size() < Q.length) {
        if (dict.contains(A[right])) {
          countQ.put(A[right],
              countQ.containsKey(A[right])
                  ? countQ.get(A[right]) + 1 : 1);
        }
        ++right;
      }

      if (countQ.size() == Q.length && // Found |Q| keywords.
          ((res.getFirst() == -1 && res.getSecond() == -1)
           || right - 1 - left < res.getSecond() - res.getFirst())) {
        res.setFirst(left);
        res.setSecond(right - 1);
      }

      // Keeps moving left until it reaches end or countQ has less |Q| items.
      while (left < right && countQ.size() == Q.length) {
        if (dict.contains(A[left])) {
          int it = countQ.get(A[left]);
          countQ.put(A[left], --it);
          if (it == 0) {
            countQ.remove(A[left]);
            if ((res.getFirst() == -1 && res.getSecond() == -1)
                || right - 1 - left < res.getSecond() - res.getFirst()) {
              res.setFirst(left);
              res.setSecond(right - 1);
            }
          }
        }
        ++left;
      }
    }
    return res;
  }
  // @exclude

  // O(n^2) solution
  public static int checkAns(String[] A, String[] Q) {
    Set<String> dict = new HashSet<>(Arrays.asList(Q));

    Pair<Integer, Integer> ans = new Pair<>(0, A.length - 1);
    for (int l = 0; l < A.length; ++l) {
      Map<String, Integer> count = new HashMap<>();
      for (int r = l;
           r < A.length && r - l < ans.getSecond() - ans.getFirst();
           ++r) {
        if (dict.contains(A[r])) {
          count.put(A[r],
              count.containsKey(A[r]) ? count.get(A[r]) + 1 : 1);
        }
        if (count.size() == Q.length) {
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
      if (args.length == 1) {
        n = Integer.parseInt(args[0]);
      } else {
        n = gen.nextInt(10000) + 1;
      }
      String[] A = new String[n];
      for (int i = 0; i < n; ++i) {
        A[i] = randString(gen.nextInt(10) + 1);
      }
      /*
       * for (int i = 0; i < A.size(); ++i) { cout << A[i] << ' '; } cout <<
       * endl;
       */
      Set<String> dict = new HashSet<>(Arrays.asList(A));
      int m = gen.nextInt(dict.size()) + 1;
      String[] Q = new String[m];
      int idx = 0;
      for (String aDict : dict) {
        Q[idx++] = aDict;
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
        if (dict.contains(A[i])) {
          dict.remove(A[i]);
        }
      }
      assert (dict.isEmpty());
      Pair<Integer, Integer> res2 =
          SmallestSubarrayCoveringSetStream.findSmallestSubarrayCoveringSubset(A, Q);
      System.out.println(res2.getFirst() + ", " + res2.getSecond());
      dict.clear();
      for (String aQ : Q) {
        dict.add(aQ);
      }
      for (int i = res.getFirst(); i <= res.getSecond(); ++i) {
        if (dict.contains(A[i])) {
          dict.remove(A[i]);
        }
      }
      assert (dict.isEmpty());
      assert (res.getSecond() - res.getFirst() == res2.getSecond()
          - res2.getFirst());
      assert (res.getSecond() - res.getFirst() == checkAns(A, Q));
    }
  }
}
