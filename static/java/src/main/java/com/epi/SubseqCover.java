// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

package com.epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class SubseqCover {
  public static String randString(int len) {
    StringBuilder sb = new StringBuilder();
    Random gen = new Random();
    while (len-- != 0) {
      sb.append((char)(gen.nextInt('z' + 1 - 'a') + 'a'));
    }
    return sb.toString();
  }

  // @include
  public static class Subarray {
    // Represent subarray by starting and ending indices, inclusive.
    public Integer start;
    public Integer end;

    public Subarray(Integer start, Integer end) {
      this.start = start;
      this.end = end;
    }
  }

  public static Subarray findSmallestSequentiallyCoveringSubset(
      List<String> paragraph, List<String> keywords) {
    // Maps each keyword to its index in the keywords array.
    Map<String, Integer> keywordToIdx = new HashMap<>();

    // Since keywords are uniquely identified by their indices in keywords
    // array, we can use those indices as keys to lookup in a vector.
    List<Integer> latestOccurrence = new ArrayList<>(keywords.size());

    // For each keyword (identified by its index in keywords array), stores the
    // length of the shortest subarray ending at the most recent occurrence of
    // that keyword that sequentially cover all keywords up to that keyword.
    List<Integer> shortestSubarrayLength = new ArrayList<>(keywords.size());

    // Initializes latestOccurrence, shortestSubarrayLength, and keywordToIdx.
    for (int i = 0; i < keywords.size(); ++i) {
      latestOccurrence.add(-1);
      shortestSubarrayLength.add(Integer.MAX_VALUE);
      keywordToIdx.put(keywords.get(i), i);
    }

    int shortestDistance = Integer.MAX_VALUE;
    Subarray result = new Subarray(-1, -1);
    for (int i = 0; i < paragraph.size(); ++i) {
      Integer keywordIdx = keywordToIdx.get(paragraph.get(i));
      if (keywordIdx != null) {
        if (keywordIdx == 0) { // First keyword.
          shortestSubarrayLength.set(0, 1);
        } else if (shortestSubarrayLength.get(keywordIdx - 1)
                   != Integer.MAX_VALUE) {
          int distanceToPreviousKeyword
              = i - latestOccurrence.get(keywordIdx - 1);
          shortestSubarrayLength.set(
              keywordIdx, distanceToPreviousKeyword
                              + shortestSubarrayLength.get(keywordIdx - 1));
        }
        latestOccurrence.set(keywordIdx, i);

        // Last keyword, look for improved subarray.
        if (keywordIdx == keywords.size() - 1
            && shortestSubarrayLength.get(shortestSubarrayLength.size() - 1)
                   < shortestDistance) {
          shortestDistance
              = shortestSubarrayLength.get(shortestSubarrayLength.size() - 1);
          result.start
              = i
                - shortestSubarrayLength.get(shortestSubarrayLength.size() - 1)
                + 1;
          result.end = i;
        }
      }
    }
    return result;
  }
  // @exclude

  public static void smallTest() {
    List<String> a3
        = Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "2",
                        "4", "6", "10", "10", "10", "3", "2", "1", "0");
    List<String> subseq4 = Arrays.asList("0", "2", "9", "4", "6");
    Subarray rr = findSmallestSequentiallyCoveringSubset(a3, subseq4);
    assert(rr.start == 0 && rr.end == 12);
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
      List<String> A = new ArrayList<>(n);
      for (int i = 0; i < n; i++) {
        A.add(randString(gen.nextInt(5) + 1));
      }
      Set<String> dict = new HashSet<>(A);

      System.out.println("A = " + A);
      int m = gen.nextInt(Math.min(dict.size(), 10)) + 1;
      List<String> Q = new ArrayList<>(m);
      Iterator<String> it = dict.iterator();
      for (int i = 0; i < m; i++) {
        if (it.hasNext()) {
          Q.add(it.next());
        }
      }
      System.out.println("Q = " + Q);

      Subarray res = findSmallestSequentiallyCoveringSubset(A, Q);
      System.out.println(res.start + ", " + res.end);
      if (res.start != -1 && res.end != Q.size()) {
        if (!res.start.equals(res.end)) {
          System.out.println(res.start + ", " + res.end);
        }
        dict.clear();
        dict.addAll(Q);
        for (int i = res.start; i <= res.end; ++i) {
          if (dict.contains(A.get(i))) {
            dict.remove(A.get(i));
          }
        }
        assert(dict.isEmpty());
      }
    }
  }
}
