package com.epi;

import java.util.HashMap;
import java.util.Map;

public class RomanToInteger {
  // @include
  public static int romanToInteger(String s) {
    Map<Character, Integer> T = new HashMap<Character, Integer>() {
      {
        put('I', 1);
        put('V', 5);
        put('X', 10);
        put('L', 50);
        put('C', 100);
        put('D', 500);
        put('M', 1000);
      }
    };

    int sum = T.get(s.charAt(s.length() - 1));
    for (int i = s.length() - 2; i >= 0; --i) {
      if (T.get(s.charAt(i)) < T.get(s.charAt(i + 1))) {
        sum -= T.get(s.charAt(i));
      } else {
        sum += T.get(s.charAt(i));
      }
    }
    return sum;
  }
  // @exclude

  public static void main(String[] args) {
    assert(7 == romanToInteger("VII"));
    assert(184 == romanToInteger("CLXXXIV"));
    assert(9 == romanToInteger("IX"));
    assert(40 == romanToInteger("XL"));
    assert(60 == romanToInteger("LX"));
    assert(1500 == romanToInteger("MD"));
    assert(400 == romanToInteger("CD"));
    assert(1900 == romanToInteger("MCM"));
    assert(9919 == romanToInteger("MMMMMMMMMCMXIX"));
    if (args.length == 1) {
      System.out.println(args[0] + " equals to " + romanToInteger(args[0]));
    }
  }
}
