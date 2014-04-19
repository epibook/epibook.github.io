package com.epi;

import java.util.Random;

public class SnakeString {
  // @include
  public static String snakeString(String s) {
    String ret = "";
    // Processes the first row.
    int idx = 1;
    while (idx < s.length()) {
      ret += s.charAt(idx);
      idx += 4;
    }
    // Processes the second row.
    idx = 0;
    while (idx < s.length()) {
      ret += s.charAt(idx);
      idx += 2;
    }
    // Processes the third row.
    idx = 3;
    while (idx < s.length()) {
      ret += s.charAt(idx);
      idx += 4;
    }

    return ret;
  }

  // @exclude

  private static String randString(int len) {
    Random r = new Random();
    StringBuilder ret = new StringBuilder(len);
    while (len-- > 0) {
      ret.append((char) (r.nextInt(26) + 'A'));
    }
    return ret.toString();
  }

  private static void smallTest() {
    assert (snakeString("Hello World!").equals("e lHloWrdlo!"));
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
