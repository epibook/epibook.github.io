package com.epi;

import java.util.Random;

public class SnakeString {
  // @include
  public static String snakeString(String s) {
    StringBuilder result = new StringBuilder();
    // Outputs the first row, i.e., s[1], s[5], s[9], ...
    for (int i = 1; i < s.length(); i += 4) {
      result.append(s.charAt(i));
    }
    // Outputs the second row, i.e., s[0], s[2], s[4], ...
    for (int i = 0; i < s.length(); i += 2) {
      result.append(s.charAt(i));
    }
    // Outputs the third row, i.e., s[3], s[7], s[11], ...
    for (int i = 3; i < s.length(); i += 4) {
      result.append(s.charAt(i));
    }
    return result.toString();
  }
  // @exclude

  private static String randString(int len) {
    Random r = new Random();
    StringBuilder ret = new StringBuilder(len);
    while (len-- > 0) {
      ret.append((char)(r.nextInt(26) + 'A'));
    }
    return ret.toString();
  }

  private static void smallTest() {
    assert(snakeString("Hello World!").equals("e lHloWrdlo!"));
  }

  public static void main(String[] args) {
    smallTest();
    Random r = new Random();
    String s;
    if (args.length == 1) {
      s = args[0];
    } else {
      s = randString(r.nextInt(100) + 1);
    }
    System.out.println(snakeString(s));
  }
}
