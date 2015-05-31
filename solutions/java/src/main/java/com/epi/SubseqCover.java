// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

package com.epi;

import com.epi.utils.Pair;

import java.util.*;

class SubseqCover {
  public static String randString(int len) {
    StringBuilder sb = new StringBuilder();
    Random gen = new Random();
    while (len-- != 0) {
      sb.append((char)(gen.nextInt('z' + 1 - 'a') + 'a'));
    }
    return sb.toString();
  }

  // @include
  public static Pair<Integer, Integer> findSmallestSequentiallyCoveringSubset(
      String[] paragraph, String[] keywords) {
    // Maps each keyword to its index in the keywords array.
    Map<String, Integer> keywordToIdx = new HashMap<>();
    // Since keywords are uniquely identified by their indices in keywords
    // array, we can use those indices as keys to lookup in a vector.
    int[] latestOccurrence = new int[keywords.length];
    // For each keyword (identified by its index in keywords array), stores the
    // length of the shortest subarray ending at the most recent occurrence of
    // that keyword that sequentially cover all keywords up to that keyword.
    int[] shortestSubarrayLength = new int[keywords.length];

    // Initializes latestOccurrence, shortestSubarrayLength, and keywordToIdx.
    for (int i = 0; i < keywords.length; ++i) {
      latestOccurrence[i] = -1;
      shortestSubarrayLength[i] = Integer.MAX_VALUE;
      keywordToIdx.put(keywords[i], i);
    }

    int shortestDistance = Integer.MAX_VALUE;
    Pair<Integer, Integer> result = new Pair<>(-1, -1);
    for (int i = 0; i < paragraph.length; ++i) {
      Integer keywordIdx = keywordToIdx.get(paragraph[i]);
      if (keywordIdx != null) {
        if (keywordIdx == 0) { // First keyword.
          shortestSubarrayLength[0] = 1;
        } else if (shortestSubarrayLength[keywordIdx - 1] != Integer.MAX_VALUE) {
          int distanceToPreviousKeyword = i - latestOccurrence[keywordIdx - 1];
          shortestSubarrayLength[keywordIdx] =
              distanceToPreviousKeyword + shortestSubarrayLength[keywordIdx - 1];
        }
        latestOccurrence[keywordIdx] = i;

        // Last keyword, look for improved subarray.
        if (keywordIdx == keywords.length - 1 &&
            shortestSubarrayLength[shortestSubarrayLength.length - 1] <
                shortestDistance) {
          shortestDistance =
              shortestSubarrayLength[shortestSubarrayLength.length - 1];
          result.setFirst(
              i - shortestSubarrayLength[shortestSubarrayLength.length - 1] + 1);
          result.setSecond(i);
        }
      }
    }
    return result;
  }
  // @exclude

  public static void smallTest() {
    String[] a3 =
        new String[] {"0", "1", "2", "3",  "4",  "5",  "6", "7", "8", "9",
                      "2", "4", "6", "10", "10", "10", "3", "2", "1", "0"};
    String[] subseq4 = new String[] {"0", "2", "9", "4", "6"};
    Pair<Integer, Integer> rr =
        findSmallestSequentiallyCoveringSubset(a3, subseq4);
    assert(rr.getFirst() == 0 && rr.getSecond() == 12);
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

      Pair<Integer, Integer> res = findSmallestSequentiallyCoveringSubset(A, Q);
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
        assert(dict.isEmpty());
      }
    }
  }
}
