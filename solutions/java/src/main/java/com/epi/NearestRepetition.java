package com.epi;

import java.util.*;

class NearestRepetition {
  private static String randString(int len) {
    StringBuilder ret = new StringBuilder();
    Random rnd = new Random();

    while (len-- > 0) {
      ret.append((char)(rnd.nextInt(26) + 97));
    }
    return ret.toString();
  }

  // @include
  public static int findNearestRepetition(String[] paragraph) {
    Map<String, Integer> wordToLatestIndex = new HashMap<>();
    int closestDis = Integer.MAX_VALUE;
    for (int i = 0; i < paragraph.length; ++i) {
      if (wordToLatestIndex.containsKey(paragraph[i])) {
        closestDis =
            Math.min(closestDis, i - wordToLatestIndex.get(paragraph[i]));
      }
      wordToLatestIndex.put(paragraph[i], i);
    }
    return closestDis;
  }
  // @exclude

  // O(n^2) checking
  private static int checkAnswer(String[] s) {
    int closestDis = Integer.MAX_VALUE;
    for (int i = 0; i < s.length; ++i) {
      for (int j = i + 1; j < s.length; ++j) {
        if (s[i].equals(s[j])) {
          closestDis = Math.min(closestDis, j - i);
        }
      }
    }
    return closestDis;
  }

  public static void main(String[] args) {
    String[] A =
        new String[] {"foo", "bar", "widget", "foo", "widget", "widget", "adnan"};
    assert(checkAnswer(A) == findNearestRepetition(A));
    A = new String[] {"foo", "bar",    "widget", "foo",
                      "xyz", "widget", "bar",    "adnan"};
    assert(checkAnswer(A) == findNearestRepetition(A));
    A = new String[] {"foo", "bar", "widget", "adnan"};
    assert(checkAnswer(A) == findNearestRepetition(A));
    A = new String[] {};
    assert(checkAnswer(A) == findNearestRepetition(A));
    A = new String[] {"foo", "foo", "foo"};
    assert(checkAnswer(A) == findNearestRepetition(A));
    Random rnd = new Random();
    for (int times = 0; times < 1000; ++times) {
      int n = 0;
      if (args.length == 1) {
        n = Integer.parseInt(args[0]);
      } else {
        n = rnd.nextInt(10000) + 1;
      }
      String[] s = new String[n];
      for (int i = 0; i < n; ++i) {
        s[i] = randString(rnd.nextInt(10) + 1);
      }
      assert(checkAnswer(s) == findNearestRepetition(s));
    }
  }
}
