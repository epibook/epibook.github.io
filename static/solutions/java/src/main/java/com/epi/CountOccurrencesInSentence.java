// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

package com.epi;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class CountOccurrencesInSentence {
  private static Map<Character, Integer> charToCount = new HashMap<>();

  // @include
  public static void countOccurrences(String s) {
    char[] a = s.toCharArray();
    Arrays.sort(a);

    int currentCharacterCount = 1;
    for (int i = 1; i < a.length; ++i) {
      if (a[i] == a[i - 1]) {
        ++currentCharacterCount;
      } else {
        System.out.print("(" + a[i - 1] + "," + currentCharacterCount + "),");
        // @exclude
        charToCount.put(a[i - 1], currentCharacterCount);
        // @include
        currentCharacterCount = 1;
      }
    }
    System.out.println("(" + a[a.length - 1] + ',' + currentCharacterCount
                       + ")");
  }
  // @exclude

  private static String randomString(int len) {
    StringBuilder ret = new StringBuilder();
    Random rnd = new Random();

    while (len-- > 0) {
      ret.append((char)(rnd.nextInt(26) + 97));
    }
    return ret.toString();
  }

  private static void simpleTest() {
    countOccurrences("foo bar! ABA A");
    assert(charToCount.get('f') == 1);
    assert(charToCount.get('F') == null);
    assert(charToCount.get('o') == 2);
    assert(charToCount.get('x') == null);
    assert(charToCount.get(' ') == 3);
    assert(charToCount.get('!') == 1);
    assert(charToCount.get('A') == 3);
    assert(charToCount.get('B') == 1);
  }

  public static void main(String[] args) {
    simpleTest();
    Random rnd = new Random();
    String s;
    if (args.length == 1) {
      s = args[0];
    } else {
      s = randomString(rnd.nextInt(1000) + 1);
    }
    System.out.println(s);
    countOccurrences(s);
  }
}
