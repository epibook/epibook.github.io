package com.epi;

import java.util.Random;

public class InterleavingString {
  // @include
  public static boolean isInterleavingString(String s1, String s2, String s3) {
    // Early return if |s1| + |s2| != |s3|.
    if (s1.length() + s2.length() != s3.length()) {
      return false;
    }

    boolean[][] T = new boolean[s1.length() + 1][s2.length() + 1];
    T[0][0] = true; // Base case.
    // Uses chars from s1 only to match s3.
    for (int i = 0; i < s1.length(); ++i) {
      if (s1.charAt(i) == s3.charAt(i)) {
        T[i + 1][0] = true;
      } else {
        break;
      }
    }
    // Uses chars from s2 only to match s3.
    for (int j = 0; j < s2.length(); ++j) {
      if (s2.charAt(j) == s3.charAt(j)) {
        T[0][j + 1] = true;
      } else {
        break;
      }
    }

    for (int i = 0; i < s1.length(); ++i) {
      for (int j = 0; j < s2.length(); ++j) {
        T[i + 1][j + 1]
            = (T[i][j + 1] && s1.charAt(i) == s3.charAt(i + j + 1))
              || (T[i + 1][j] && s2.charAt(j) == s3.charAt(i + j + 1));
      }
    }

    return T[s1.length()][s2.length()];
  }
  // @exclude

  private static String randString(int len) {
    Random r = new Random();
    StringBuilder ret = new StringBuilder(len);
    while (len-- > 0) {
      ret.append((char)(r.nextInt(26) + 'a'));
    }
    return ret.toString();
  }

  private static void smallTest() {
    assert(isInterleavingString("aabcc", "dbbca", "aadbbcbcac"));
    assert(!isInterleavingString("aabcc", "dbbca", "aadbbbaccc"));
    assert(isInterleavingString("aabaac", "aadaaeaaf", "aadaaeaabaafaac"));
    assert(isInterleavingString("bbc", "acaab", "abcbcaab"));
  }

  public static void main(String[] args) {
    smallTest();
    if (args.length == 3) {
      String s1 = args[0], s2 = args[1], s3 = args[2];
      System.out.println(s1 + " " + s2 + " " + s3);
      System.out.println(isInterleavingString(s1, s2, s3));
    } else {
      Random r = new Random();
      String s1 = randString(r.nextInt(100) + 1),
             s2 = randString(r.nextInt(100) + 1);
      String s3 = randString(s1.length() + s2.length());
      System.out.println(s1 + " " + s2 + " " + s3);
      System.out.println(isInterleavingString(s1, s2, s3));
    }
  }
}
