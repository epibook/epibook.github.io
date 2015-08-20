// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.
package com.epi;

import java.util.Random;

public class ReverseWords {
  static String randString(int len) {
    Random gen = new Random();
    StringBuilder ret = new StringBuilder();
    while (len-- > 0) {
      int ch = gen.nextInt(53);
      if (ch == 52) {
        ret.append(' ');
      } else if (ch < 26) {
        ret.append(ch + 'a');
      } else {
        ret.append(ch - 26 + 'A');
      }
    }
    return ret.toString();
  }

  // @include
  public static String reverseWords(String input) {
    // Reverses the whole string first.
    char[] reversed = input.toCharArray();
    reverse(reversed, 0, input.length());

    int start = 0, end;
    while ((end = find(reversed, ' ', start)) != -1) {
      // Reverses each word in the string.
      reverse(reversed, start, end);
      start = end + 1;
    }
    // Reverses the last word.
    reverse(reversed, start, reversed.length);

    return new String(reversed);
  }

  public static void reverse(char[] array, int start, int stopIndex) {
    if (start >= stopIndex) {
      return;
    }

    int last = stopIndex - 1;
    for (int i = start; i <= start + (last - start) / 2; i++) {
      char tmp = array[i];
      array[i] = array[last - i + start];
      array[last - i + start] = tmp;
    }
  }

  public static int find(char[] array, char c, int start) {
    for (int i = start; i < array.length; i++) {
      if (array[i] == c) {
        return i;
      }
    }

    return -1;
  }
  // @exclude

  private static void checkAnswer(String ori, String str) {
    String reversed = reverseWords(str);
    assert ori.equals(reversed);
  }

  private static void simpleTest() {
    assert reverseWords("a cat and dog").equals("dog and cat a");
    assert reverseWords("dog").equals("dog");
  }

  public static void main(String[] args) {
    simpleTest();
    Random gen = new Random();
    for (int times = 0; times < 1000; ++times) {
      String str = "";
      if (args.length >= 1) {
        str += args[0];
        for (int i = 1; i < args.length; ++i) {
          str += ' ';
          str += args[i];
        }
      } else {
        str = randString(gen.nextInt(10000));
      }
      System.out.println(str);
      String reversed = reverseWords(str);
      System.out.println(reversed);
      checkAnswer(str, reversed);
    }
  }
}
