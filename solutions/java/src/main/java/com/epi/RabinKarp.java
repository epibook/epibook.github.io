// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

package com.epi;

public class RabinKarp {
  // @include
  private static int kBase = 26, kMod = 997;

  // Returns the index of the first character of the substring if found, -1
  // otherwise.
  public static int rabinKarp(String t, String s) {
    if (s.length() > t.length()) {
      return -1; // s is not a substring of t.
    }
    int tHash = 0, sHash = 0; // Hash codes for the substring of t and s.
    int powerS = 1; // The modulo result of kBase^|s|.
    for (int i = 0; i < s.length(); i++) {
      powerS = i > 0 ? powerS * kBase % kMod : 1;
      tHash = (tHash * kBase + t.charAt(i)) % kMod;
      sHash = (sHash * kBase + s.charAt(i)) % kMod;
    }

    for (int i = s.length(); i < t.length(); i++) {
      // In case of hash collision but two strings are not equal, checks
      // the two substrings are actually equal or not.
      if (tHash == sHash && t.substring(i - s.length(), i).equals(s)) {
        return i - s.length(); // Found a match.
      }

      // Uses rolling hash to compute the new hash code.
      tHash -= (t.charAt(i - s.length()) * powerS) % kMod;
      if (tHash < 0) {
        tHash += kMod;
      }
      tHash = (tHash * kBase + t.charAt(i)) % kMod;
    }

    // Tries to match s and t[t.size() - s.size() : t.size() - 1].
    if (tHash == sHash &&
        t.subSequence(t.length() - s.length(), t.length()).equals(s)) {
      return t.length() - s.length();
    }
    return -1; // s is not a substring of t.
  }
  // @exclude

  public static void main(String args[]) {
    String t = "GACGCCA";
    String s = "CGC";
    if (args.length == 2) {
      t = args[0];
      s = args[1];
    } else {
      return;
    }
    int index = rabinKarp(t, s);
    if (index >= 0) {
      System.out.printf("Index of first occurrence of %s in %s: %d\n", s, t,
                        rabinKarp(t, s));
    } else {
      System.out.println("No match found");
    }
    assert(rabinKarp("GACGCCA", "CGC") == 2);
    assert(rabinKarp("GATACCCATCGAGTCGGATCGAGT", "GAG") == 10);
  }
}
