// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

package com.epi;

import java.util.Random;

public class RabinKarp {
  // @include
  // Returns the index of the first character of the substring if found, -1
  // otherwise.
  public static int rabinKarp(String t, String s) {
    if (s.length() > t.length()) {
      return -1; // s is not a substring of t.
    }

    final int BASE = 26;
    int tHash = 0, sHash = 0; // Hash codes for the substring of t and s.
    int powerS = 1; // BASE^|s|.
    for (int i = 0; i < s.length(); i++) {
      powerS = i > 0 ? powerS * BASE : 1;
      tHash = tHash * BASE + t.charAt(i);
      sHash = sHash * BASE + s.charAt(i);
    }

    for (int i = s.length(); i < t.length(); i++) {
      // Checks the two substrings are actually equal or not, to protect
      // against hash collision.
      if (tHash == sHash && t.substring(i - s.length(), i).equals(s)) {
        return i - s.length(); // Found a match.
      }

      // Uses rolling hash to compute the new hash code.
      tHash -= t.charAt(i - s.length()) * powerS;
      tHash = tHash * BASE + t.charAt(i);
    }
    // Tries to match s and t.substring(t.length() - s.length()).
    if (tHash == sHash && t.substring(t.length() - s.length()).equals(s)) {
      return t.length() - s.length();
    }
    return -1; // s is not a substring of t.
  }
  // @exclude

  private static int checkAnswer(String t, String s) {
    for (int i = 0; i + s.length() - 1 < t.length(); ++i) {
      boolean find = true;
      for (int j = 0; j < s.length(); ++j) {
        if (t.charAt(i + j) != s.charAt(j)) {
          find = false;
          break;
        }
      }
      if (find) {
        return i;
      }
    }
    return -1; // No matching.
  }

  private static String randString(int len) {
    Random r = new Random();
    StringBuilder ret = new StringBuilder(len);
    while (len-- > 0) {
      ret.append((char)(r.nextInt(26) + 'a'));
    }
    return ret.toString();
  }

  private static void smallTest() {
    assert(rabinKarp("GACGCCA", "CGC") == 2);
    assert(rabinKarp("GATACCCATCGAGTCGGATCGAGT", "GAG") == 10);
    assert(rabinKarp("FOOBARWIDGET", "WIDGETS") == -1);
    assert(rabinKarp("A", "A") == 0);
    assert(rabinKarp("A", "B") == -1);
    assert(rabinKarp("A", "") == 0);
    assert(rabinKarp("ADSADA", "") == 0);
    assert(rabinKarp("", "A") == -1);
    assert(rabinKarp("", "AAA") == -1);
    assert(rabinKarp("A", "AAA") == -1);
    assert(rabinKarp("AA", "AAA") == -1);
    assert(rabinKarp("AAA", "AAA") == 0);
    assert(rabinKarp("BAAAA", "AAA") == 1);
    assert(rabinKarp("BAAABAAAA", "AAA") == 1);
    assert(rabinKarp("BAABBAABAAABS", "AAA") == 8);
    assert(rabinKarp("BAABBAABAAABS", "AAAA") == -1);
    assert(rabinKarp("FOOBAR", "BAR") > 0);
  }

  public static void main(String args[]) {
    smallTest();
    if (args.length == 2) {
      String t = args[0];
      String s = args[1];
      System.out.println("t = " + t);
      System.out.println("s = " + s);
      assert(checkAnswer(t, s) == rabinKarp(t, s));
    } else {
      Random r = new Random();
      for (int times = 0; times < 10000; ++times) {
        String t = randString(r.nextInt(1000) + 1);
        String s = randString(r.nextInt(20) + 1);
        System.out.println("t = " + t);
        System.out.println("s = " + s);
        assert(checkAnswer(t, s) == rabinKarp(t, s));
      }
    }
  }
}
