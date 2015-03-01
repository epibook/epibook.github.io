// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

package com.epi;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Random;

public class BigNumberMultiplication {
  // @include
  public static String Multiply(String num1, String num2) {
    boolean isPositive = true;
    if (num1.charAt(0) == '-') {
      isPositive = !isPositive;
      num1 = num1.substring(1);
    }
    if (num2.charAt(0) == '-') {
      isPositive = !isPositive;
      num2 = num2.substring(1);
    }

    // Reverses num1 and num2 to make multiplication easier.
    num1 = new StringBuilder(num1).reverse().toString();
    num2 = new StringBuilder(num2).reverse().toString();
    int[] result = new int[num1.length() + num2.length()];
    java.util.Arrays.fill(result, 0);
    for (int i = 0; i < num1.length(); ++i) {
      for (int j = 0; j < num2.length(); ++j) {
        result[i + j] += (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
        result[i + j + 1] += result[i + j] / 10;
        result[i + j] %= 10;
      }
    }

    // Converts result to string in reverse order, and skips the first 0s and
    // keeps one 0 if all are 0s.
    int i = num1.length() + num2.length() - 1;
    while (result[i] == 0 && i != 0) {
      --i;
    }
    StringBuilder sb = new StringBuilder();
    if (!isPositive && result[i] != 0) {  // It won't print "-0".
      sb.append('-');
    }
    while (i >= 0) {
      sb.append(result[i--]);
    }
    return sb.toString();
  }
  // @exclude

  private static String randString(int len) {
    StringBuilder ret = new StringBuilder();
    if (len == 0) {
      ret.append("0");
    } else {
      Random gen = new Random();
      if (gen.nextBoolean()) {
        ret.append("-");
      }
      ret.append(gen.nextInt(9) + 1 + '0');
      --len;
      while (len-- > 0) {
        ret.append(gen.nextInt(10) + '0');
      }
    }
    return ret.toString();
  }

  private static void simpleTest() {
    assert (Multiply("0", "1000").equals("0"));
    assert (Multiply("131412", "-1313332").equals("-172587584784"));

    assert "0".equals(new BigInt("0").multiply(new BigInt("1000")).toString());
    assert "-172587584784".equals(new BigInt("131412").multiply(
        new BigInt("-1313332")).toString());
  }

  public static void main(String[] args) {
    simpleTest();
    for (int times = 0; times < 1000; ++times) {
      String s1, s2;
      if (args.length == 2) {
        s1 = args[0];
        s2 = args[1];
      } else {
        Random gen = new Random();
        s1 = randString(gen.nextInt(20));
        s2 = randString(gen.nextInt(20));
      }

      BigInt res = new BigInt(s1).multiply(new BigInt(s2));
      System.out.println(String.format("%s * %s = %s", s1, s2, res.toString()));

      System.out.println("multiplying using Java's BigInteger...");
      BigInteger result = new BigInteger(s1).multiply(new BigInteger(s2));
      System.out.println("answer = " + result.toString());

      assert (res.toString().equals(result.toString()));
    }
  }

}

class BigInt {
  BigInt(int capacity) {
    sign = 1;
    digits = new char[capacity];
  }

  BigInt(String s) {
    sign = s.charAt(0) == '-' ? -1 : 1;
    digits = new char[s.length() - (s.charAt(0) == '-' ? 1 : 0)];

    for (int i = s.length() - 1, j = 0;
         i >= (s.charAt(0) == '-' ? 1 : 0);
         --i, ++j) {
      if (Character.isDigit(s.charAt(i))) {
        digits[j] = (char) (s.charAt(i) - '0');
      }
    }
  }

  BigInt multiply(BigInt n) {
    BigInt result = new BigInt(digits.length + n.digits.length);
    result.sign = sign * n.sign;
    int i = 0, j = 0;
    for (i = 0; i < n.digits.length; ++i) {
      if (n.digits[i] != 0) {
        int carry = 0;
        for (j = 0; j < digits.length || carry > 0; ++j) {
          int nDigit = result.digits[i + j]
              + (j < digits.length ? n.digits[i] * digits[j] : 0) + carry;
          result.digits[i + j] = (char) (nDigit % 10);
          carry = nDigit / 10;
        }
      }
    }

    // If one number is 0, the result size should be 0.
    if ((digits.length == 1 && digits[0] == 0)
        || (n.digits.length == 1 && n.digits[0] == 0)) {
      result.sign = 1;
      result.digits = Arrays.copyOf(result.digits, 1);
    } else {
      result.digits = Arrays.copyOf(result.digits, i + j - 1);
    }
    return result;
  }

  public String toString() {
    StringBuilder s = new StringBuilder(sign > 0 ? "" : "-");

    for (int i = digits.length - 1; i >= 0; --i) {
      s.append((char) (digits[i] + '0'));
    }

    if (digits.length == 0) {
      s.append('0');
    }
    return s.toString();
  }

  int sign; // -1 or 1;
  char[] digits;
}
