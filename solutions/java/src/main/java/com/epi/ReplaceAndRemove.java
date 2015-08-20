// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.
package com.epi;

import java.util.Arrays;
import java.util.Random;

public class ReplaceAndRemove {
  // @include
  public static String replaceAndRemove(String s) {
    char[] sChars = s.toCharArray();
    // Forward iteration: remove "b"s and count the number of "a"s.
    int writeIdx = 0, aCount = 0;
    for (char c : sChars) {
      if (c != 'b') {
        sChars[writeIdx++] = c;
      }
      if (c == 'a') {
        ++aCount;
      }
    }

    // Allocates space according to the number of "a".
    sChars = Arrays.copyOf(sChars, writeIdx + aCount);
    // Backward iteration: replace "a"s with "dd"s starting from the end.
    int curIdx = writeIdx - 1;
    writeIdx = sChars.length - 1;
    while (curIdx >= 0) {
      if (sChars[curIdx] == 'a') {
        sChars[writeIdx--] = 'd';
        sChars[writeIdx--] = 'd';
      } else {
        sChars[writeIdx--] = sChars[curIdx];
      }
      --curIdx;
    }
    return new String(sChars);
  }
  // @exclude

  static String randString(int len) {
    Random gen = new Random();
    StringBuilder ret = new StringBuilder();
    while (len-- > 0) {
      ret.append((char)(gen.nextInt(4) + 97));
    }

    return ret.toString();
  }

  static void checkAns(String s, String ans) {
    StringBuilder temp = new StringBuilder();
    for (int i = 0; i < s.length(); ++i) {
      if (s.charAt(i) == 'a') {
        temp.append('d');
        temp.append('d');
      } else if (s.charAt(i) != 'b') {
        temp.append(s.charAt(i));
      }
    }
    assert(ans.equals(temp.toString()));
  }

  public static void main(String[] args) {
    Random gen = new Random();
    for (int times = 0; times < 1000; ++times) {
      String s;
      if (args.length == 1) {
        s = args[0];
      } else {
        s = randString(gen.nextInt(1000) + 1);
      }
      System.out.println(s);
      System.out.println();
      String ans = replaceAndRemove(s);
      System.out.println(ans);
      checkAns(s, ans);
    }
  }
}
