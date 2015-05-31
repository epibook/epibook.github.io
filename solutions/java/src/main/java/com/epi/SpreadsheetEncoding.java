package com.epi;

import java.util.Random;

public class SpreadsheetEncoding {
  private static String randString(int len) {
    Random r = new Random();
    StringBuilder ret = new StringBuilder();
    while (len-- != 0) {
      ret.append((char)(r.nextInt('Z' - 'A' + 1) + 'A'));
    }
    return ret.toString();
  }

  // @include
  public static int ssDecodeColID(final String col) {
    int ret = 0;
    for (char c : col.toCharArray()) {
      ret = ret * 26 + c - 'A' + 1;
    }
    return ret;
  }
  // @exclude

  private static void simpleTest() {
    assert(1 == ssDecodeColID("A"));
    assert(27 == ssDecodeColID("AA"));
  }

  public static void main(String[] args) {
    simpleTest();
    Random r = new Random();
    if (args.length == 1) {
      System.out.println(args[0] + " " + ssDecodeColID(args[0]));
    } else {
      String s = randString(r.nextInt(5) + 1);
      System.out.println(s + " " + ssDecodeColID(s));
    }
  }
}
