// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

package com.epi;

import com.epi.utils.Pair;

import java.util.*;

class SmallestSubarrayCoveringSet {
  public static String randString(int len) {
    StringBuilder sb = new StringBuilder();
    Random gen = new Random();
    while (len-- != 0) {
      sb.append((char)(gen.nextInt('z' + 1 - 'a') + 'a'));
    }
    return sb.toString();
  }

  // @include
  public static Pair<Integer, Integer> findSmallestSubarrayCoveringSet(
      String[] paragraph, Set<String> keywords) {
    Map<String, Integer> keywordsToCount = new HashMap<>();
    int left = 0, right = 0;
    Pair<Integer, Integer> result = new Pair<>(-1, -1);
    while (right < paragraph.length) {
      // Keeps advancing right until it reaches end or keywordsToCount has
      // all keywords.
      while (right < paragraph.length &&
             keywordsToCount.size() < keywords.size()) {
        if (keywords.contains(paragraph[right])) {
          keywordsToCount.put(paragraph[right],
                              keywordsToCount.containsKey(paragraph[right])
                                  ? keywordsToCount.get(paragraph[right]) + 1
                                  : 1);
        }
        ++right;
      }

      // Found all keywords.
      if (keywordsToCount.size() == keywords.size() &&
          ((result.getFirst() == -1 && result.getSecond() == -1) ||
           right - 1 - left < result.getSecond() - result.getFirst())) {
        result.setFirst(left);
        result.setSecond(right - 1);
      }

      // Keeps advancing left until it reaches end or keywordsToCount does not
      // have all keywords.
      while (left < right && keywordsToCount.size() == keywords.size()) {
        if (keywords.contains(paragraph[left])) {
          int keywordCount = keywordsToCount.get(paragraph[left]);
          keywordsToCount.put(paragraph[left], --keywordCount);
          if (keywordCount == 0) {
            keywordsToCount.remove(paragraph[left]);
            if ((result.getFirst() == -1 && result.getSecond() == -1) ||
                right - 1 - left < result.getSecond() - result.getFirst()) {
              result.setFirst(left);
              result.setSecond(right - 1);
            }
          }
        }
        ++left;
      }
    }
    return result;
  }
  // @exclude

  // O(n^2) solution
  public static int checkAns(String[] A, String[] Q) {
    Set<String> dict = new HashSet<>(Arrays.asList(Q));

    Pair<Integer, Integer> ans = new Pair<>(0, A.length - 1);
    for (int l = 0; l < A.length; ++l) {
      Map<String, Integer> count = new HashMap<>();
      for (int r = l; r < A.length && r - l < ans.getSecond() - ans.getFirst();
           ++r) {
        if (dict.contains(A[r])) {
          count.put(A[r], count.containsKey(A[r]) ? count.get(A[r]) + 1 : 1);
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

      dict = new HashSet<>(Arrays.asList(Q));
      Pair<Integer, Integer> res = findSmallestSubarrayCoveringSet(A, dict);
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
      assert(dict.isEmpty());
      Pair<Integer, Integer> res2 =
          SmallestSubarrayCoveringSetStream.findSmallestSubarrayCoveringSubset(A,
                                                                               Q);
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
      assert(dict.isEmpty());
      assert(res.getSecond() - res.getFirst() ==
             res2.getSecond() - res2.getFirst());
      assert(res.getSecond() - res.getFirst() == checkAns(A, Q));
    }
  }
}
