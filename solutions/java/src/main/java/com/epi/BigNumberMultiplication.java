// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

package com.epi;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Random;

public class BigNumberMultiplication {
  static String randString(int len) {
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

  static void simpleTest() {
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

// @include
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

  // @exclude
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

  // @include
  int sign; // -1 or 1;
  char[] digits;
}
// @exclude
