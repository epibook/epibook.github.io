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
  public static void reverseWords(char[] input) {
    // Reverses the whole string first.
    reverse(input, 0, input.length);

    int start = 0, end;
    while ((end = find(input, ' ', start)) != -1) {
      // Reverses each word in the string.
      reverse(input, start, end);
      start = end + 1;
    }
    // Reverses the last word.
    reverse(input, start, input.length);
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
    char[] input = str.toCharArray();
    reverseWords(input);
    assert ori.equals(new String(input));
  }

  private static void simpleTest() {
    char[] input = "a cat and dog".toCharArray();
    reverseWords(input);
    assert "dog and cat a".equals(new String(input));
    input = "dog".toCharArray();
    reverseWords(input);
    assert "dog".equals(new String(input));
  }

  public static void main(String[] args) {
    simpleTest();
    Random gen = new Random();
    for (int times = 0; times < 1000; ++times) {
      StringBuilder str = new StringBuilder();
      if (args.length >= 1) {
        str.append(args[0]);
        for (int i = 1; i < args.length; ++i) {
          str.append(' ').append(args[i]);
        }
      } else {
        str.append(randString(gen.nextInt(10000)));
      }
      System.out.println(str);
      char[] input = str.toString().toCharArray();
      reverseWords(input);
      checkAnswer(str.toString(), new String(input));
    }
  }
}
