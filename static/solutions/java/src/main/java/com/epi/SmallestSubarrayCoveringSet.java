// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

package com.epi;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.io.IOException;

public class SmallestSubarrayCoveringSet {
  public static String randString(int len) {
    StringBuilder sb = new StringBuilder();
    Random gen = new Random();
    while (len-- != 0) {
      sb.append((char)(gen.nextInt('z' + 1 - 'a') + 'a'));
    }
    return sb.toString();
  }

  // @include
  // Represent subarray by starting and ending indices, inclusive.
  private static class Subarray {
    public Integer start;
    public Integer end;

    public Subarray(Integer start, Integer end) {
      this.start = start;
      this.end = end;
    }
  }

  public static Subarray findSmallestSubarrayCoveringSet(List<String> paragraph,
                                                         Set<String> keywords) {
    Map<String, Integer> keywordsToCover = new HashMap<>();
    for (String keyword : keywords) {
      keywordsToCover.put(keyword, keywordsToCover.containsKey(keyword)
                                       ? keywordsToCover.get(keyword) + 1
                                       : 1);
    }

    Subarray result = new Subarray(-1, -1);
    int remainingToCover = keywords.size();
    for (int left = 0, right = 0; right < paragraph.size(); ++right) {
      Integer keywordCount = keywordsToCover.get(paragraph.get(right));
      if (keywordCount != null) {
        keywordsToCover.put(paragraph.get(right), --keywordCount);
        if (keywordCount >= 0) {
          --remainingToCover;
        }
      }

      // Keeps advancing left until it reaches end or keywordsToCover does not
      // have all keywords.
      while (remainingToCover == 0) {
        if ((result.start == -1 && result.end == -1)
            || right - left < result.end - result.start) {
          result.start = left;
          result.end = right;
        }
        keywordCount = keywordsToCover.get(paragraph.get(left));
        if (keywordCount != null) {
          keywordsToCover.put(paragraph.get(left), ++keywordCount);
          if (keywordCount > 0) {
            ++remainingToCover;
          }
        }
        ++left;
      }
    }
    return result;
  }
  // @exclude

  // O(n^2) solution
  public static int checkAns(List<String> A, List<String> Q) {
    Set<String> dict = new HashSet<>(Q);

    Subarray ans = new Subarray(0, A.size() - 1);
    for (int l = 0; l < A.size(); ++l) {
      Map<String, Integer> count = new HashMap<>();
      for (int r = l; r < A.size() && r - l < ans.end - ans.start; ++r) {
        if (dict.contains(A.get(r))) {
          count.put(A.get(r),
                    count.containsKey(A.get(r)) ? count.get(A.get(r)) + 1 : 1);
        }
        if (count.size() == Q.size()) {
          if (r - l < ans.end - ans.start) {
            ans.start = l;
            ans.end = r;
          }
          break;
        }
      }
      count.clear();
    }

    return ans.end - ans.start;
  }

  private static void simpleTestCase(List<String> A, List<String> dict,
                                     int start, int finish)
      throws ClassNotFoundException, IOException {
    Subarray res
        = findSmallestSubarrayCoveringSet(A, new HashSet<String>(dict));
    System.out.println("res = " + res.start + " " + res.end);
    assert(res.start == start && res.end == finish);
    Integer[] res2Array
        = SmallestSubarrayCoveringSetStream
              .findSmallestSubarrayCoveringSubsetAsArray(A, dict);
    System.out.println("res2Array = " + res2Array[0] + " " + res2Array[1]);
    assert(res2Array[0] == start && res2Array[1] == finish);
  }

  private static void simpleTest() throws ClassNotFoundException, IOException {
    List<String> A = Arrays.asList("a", "b", "c", "b", "a", "d", "c", "a", "e",
                                   "a", "a", "b", "e");
    List<String> dict = Arrays.asList("b", "c", "e");
    simpleTestCase(A, dict, 3, 8);
    dict = Arrays.asList("a", "c");
    simpleTestCase(A, dict, 6, 7);
    A = Arrays.asList("a", "b");
    dict = Arrays.asList("a", "b");
    simpleTestCase(A, dict, 0, 1);
    A = Arrays.asList("a", "b");
    dict = Arrays.asList("b", "a");
    simpleTestCase(A, dict, 0, 1);
  }

  public static void main(String[] args)
      throws ClassNotFoundException, IOException {
    simpleTest();
    Random gen = new Random();
    for (int times = 0; times < 1000; ++times) {
      int n;
      if (args.length == 1) {
        n = Integer.parseInt(args[0]);
      } else {
        n = gen.nextInt(10000) + 1;
      }
      List<String> A = new ArrayList<>(n);
      for (int i = 0; i < n; ++i) {
        A.add(randString(gen.nextInt(10) + 1));
      }
      Set<String> dict = new HashSet<>(A);
      int m = gen.nextInt(dict.size()) + 1;
      List<String> Q = new ArrayList<>(m);
      int idx = 0;
      for (String aDict : dict) {
        Q.add(aDict);
        if (--m == 0) {
          break;
        }
      }

      dict = new HashSet<>(Q);
      Subarray res = findSmallestSubarrayCoveringSet(A, dict);
      System.out.println(res.start + ", " + res.end);
      dict.clear();
      for (String aQ1 : Q) {
        dict.add(aQ1);
      }
      for (int i = res.start; i <= res.end; ++i) {
        if (dict.contains(A.get(i))) {
          dict.remove(A.get(i));
        }
      }
      assert(dict.isEmpty());
      Integer[] res2Array
          = SmallestSubarrayCoveringSetStream
                .findSmallestSubarrayCoveringSubsetAsArray(A, Q);
      Subarray res2 = new Subarray(res2Array[0], res2Array[1]);

      System.out.println(res2.start + ", " + res2.end);
      dict.clear();
      for (String aQ : Q) {
        dict.add(aQ);
      }
      for (int i = res.start; i <= res.end; ++i) {
        if (dict.contains(A.get(i))) {
          dict.remove(A.get(i));
        }
      }
      assert(dict.isEmpty());
      assert(res.end - res.start == res2.end - res2.start);
      assert(res.end - res.start == checkAns(A, Q));
    }
  }
}
