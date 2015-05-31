package com.epi;

import java.util.Random;

public class ConvertBase {
  // @include
  public static String convertBase(String s, int b1, int b2) {
    boolean isNegative = s.startsWith("-");
    int x = 0;
    for (int i = (isNegative ? 1 : 0); i < s.length(); ++i) {
      x *= b1;
      x += Character.isDigit(s.charAt(i)) ? s.charAt(i) - '0'
                                          : s.charAt(i) - 'A' + 10;
    }

    StringBuilder result = new StringBuilder();
    do {
      int reminder = x % b2;
      result.append(
          (char)(reminder >= 10 ? 'A' + reminder - 10 : '0' + reminder));
      x /= b2;
    } while (x != 0);

    if (isNegative) { // s is a negative number.
      result.append('-');
    }
    result.reverse();
    return result.toString();
  }
  // @exclude

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

  public static void main(String[] args) {
    if (args.length == 3) {
      String input = args[0];
      int b1 = Integer.parseInt(args[1]);
      int b2 = Integer.parseInt(args[2]);
      System.out.println(convertBase(input, b1, b2));
      assert(input.equals(convertBase(convertBase(input, b1, b2), b2, b1)));
    } else {
      Random r = new Random();
      for (int times = 0; times < 100000; ++times) {
        String input = randIntString(r.nextInt(9) + 1);
        int base = r.nextInt(15) + 2;
        System.out.println("input is " + input + ", base1 = 10, base2 = " + base +
                           ", result = " + convertBase(input, 10, base));
        assert(input.equals(convertBase(convertBase(input, 10, base), base, 10)));
      }
    }
  }
}
