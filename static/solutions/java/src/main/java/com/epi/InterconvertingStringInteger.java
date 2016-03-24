package com.epi;

/*
    @slug
    integer-string-conversion

    @summary
    Interconvert strings and integers.  <b>#Strings #PrimitiveTypes</b>

    @problem
    A string is a sequence of characters. A string may encode an integer, e.g.,
   "123"
    encodes 123. In this problem, you are to implement methods that take a
   string
    representing an integer and return the corresponding integer, and vice
   versa. Your
    code should handle negative integers. You cannot use library functions like
   <code>stoi</code> in
    C++ and <code>parseInt</code> in Java.

    @hint
    Build the result one digit at a time.

*/

import java.util.Arrays;
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

  // @judge-include-display
  // @include
  public static String intToString(int x) {
    // @judge-exclude-display
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
    // @judge-include-display
  }
  // @judge-exclude-display

  // @judge-include-display
  public static int stringToInt(String s) {
    // @judge-exclude-display
    boolean isNegative = s.charAt(0) == '-';
    int result = 0;
    for (int i = s.charAt(0) == '-' ? 1 : 0; i < s.length(); ++i) {
      int digit = s.charAt(i) - '0';
      result = result * 10 + digit;
    }
    return isNegative ? -result : result;
    // @judge-include-display
  }
  // @judge-exclude-display
  // @exclude

  private static void checkIntToStr(int x, String str) {
    if (x != Integer.parseInt(str)) {
      System.err.println("Error: you converted " + x + " to " + str);
      System.exit(-1);
    }
  }

  private static void checkStrToInt(String str, int x) {
    if (x != Integer.parseInt(str)) {
      System.err.println("Error: you converted " + str + " to " + x);
      System.exit(-1);
    }
  }

  private static void directedTests() {
    checkIntToStr(0, intToString(0));
    checkIntToStr(-1, intToString(-1));
    checkIntToStr(1, intToString(1));
    checkIntToStr(2, intToString(2));
    checkIntToStr(-2, intToString(-2));
    checkIntToStr(9, intToString(9));
    checkIntToStr(10, intToString(10));
    checkIntToStr(123, intToString(123));
    checkIntToStr(Integer.MAX_VALUE - 1, intToString(Integer.MAX_VALUE - 1));
    checkIntToStr(Integer.MAX_VALUE, intToString(Integer.MAX_VALUE));
    checkIntToStr(Integer.MIN_VALUE + 1, intToString(Integer.MIN_VALUE + 1));
    // fails bacause of overflow
    // checkIntToStr(Integer.MIN_VALUE, intToString(Integer.MIN_VALUE));

    checkStrToInt("0", stringToInt("0"));
    checkStrToInt("-1", stringToInt("-1"));
    checkStrToInt("1", stringToInt("1"));
    checkStrToInt("2", stringToInt("2"));
    checkStrToInt("-2", stringToInt("-2"));
    checkStrToInt("9", stringToInt("9"));
    checkStrToInt("10", stringToInt("10"));
    checkStrToInt("123", stringToInt("123"));
    checkStrToInt("2147483646", stringToInt("2147483646"));
    checkStrToInt("2147483647", stringToInt("2147483647"));
    checkStrToInt("-2147483648", stringToInt("-2147483648"));
    checkStrToInt("-2147483647", stringToInt("-2147483647"));
  }

  public static void main(String[] args) {
    directedTests();
    Random r = new Random();
    if (args.length == 1) {
      int x = stringToInt(args[0]);
      checkStrToInt(args[0], x);
      String str = intToString(x);
      checkIntToStr(x, str);
    } else {
      for (int times = 0; times < 10000; ++times) {
        int x = r.nextInt();
        String str = intToString(x);
        checkIntToStr(x, str);
        str = randIntString(r.nextInt(10));
        x = stringToInt(str);
        checkStrToInt(str, x);
      }
    }
    System.out.println("You passed all tests!");
  }
}
