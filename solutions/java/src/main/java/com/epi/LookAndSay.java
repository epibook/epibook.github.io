package com.epi;

import java.util.Random;

public class LookAndSay {
  // @include
  public static String lookAndSay(int n) {
    String s = "1";
    for (int i = 1; i < n; ++i) {
      s = nextNumber(s);
    }
    return s;
  }

  private static String nextNumber(String s) {
    StringBuilder ret = new StringBuilder();
    for (int i = 0; i < s.length(); ++i) {
      int count = 1;
      while (i + 1 < s.length() && s.charAt(i) == s.charAt(i + 1)) {
        ++i;
        ++count;
      }
      ret.append(count);
      ret.append(s.charAt(i));
    }
    return ret.toString();
  }
  // @exclude

  public static void main(String[] args) {
    Random r = new Random();
    int n;
    if (args.length == 1) {
      n = Integer.parseInt(args[0]);
    } else {
      n = r.nextInt(20) + 1;
    }
    assert(lookAndSay(1).equals("1"));
    assert(lookAndSay(2).equals("11"));
    assert(lookAndSay(3).equals("21"));
    assert(lookAndSay(4).equals("1211"));
    assert(lookAndSay(5).equals("111221"));
    assert(lookAndSay(6).equals("312211"));
    assert(lookAndSay(7).equals("13112221"));
    assert(lookAndSay(8).equals("1113213211"));

    System.out.println("n = " + n);
    System.out.println(lookAndSay(n));
  }
}
