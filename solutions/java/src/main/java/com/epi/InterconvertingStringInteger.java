package com.epi;

import java.util.Random;

public class InterconvertingStringInteger {
  public static String randIntString(int len) {
    Random r = new Random();
    StringBuilder ret = new StringBuilder();
    if (len == 0) {
      return "0";
    }
    if (r.nextBoolean()) {
      ret.append('-');
    }
    ret.append((char)('1' + r.nextInt(9)));
    while (--len != 0) {
      ret.append((char)('0' + r.nextInt(10)));
    }
    return ret.toString();
  }

  // @include
  public static String intToString(int x) {
    boolean isNegative = false;
    if (x < 0) {
      x = -x;
      isNegative = true;
    }

    StringBuilder s = new StringBuilder();
    do {
      s.append((char)('0' + x % 10));
      x /= 10;
    } while (x != 0);

    if (isNegative) {
      s.append('-'); // Adds the negative sign back.
    }
    s.reverse();
    return s.toString();
  }

  public static int stringToInt(String s) {
    boolean isNegative = s.charAt(0) == '-';
    int result = 0;
    for (int i = s.charAt(0) == '-' ? 1 : 0; i < s.length(); ++i) {
      int digit = s.charAt(i) - '0';
      result = result * 10 + digit;
    }
    return isNegative ? -result : result;
  }
  // @exclude

  public static void main(String[] args) {
    Random r = new Random();
    if (args.length == 1) {
      try {
        System.out.println(stringToInt(args[0]));
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
    } else {
      for (int times = 0; times < 10000; ++times) {
        int x = r.nextInt();
        String str = intToString(x);
        System.out.println(x + " " + str);
        assert(x == Integer.parseInt(str));
        str = randIntString(r.nextInt(10));
        x = stringToInt(str);
        System.out.println(str + " " + x);
        assert(x == Integer.parseInt(str));
      }
    }
  }
}
