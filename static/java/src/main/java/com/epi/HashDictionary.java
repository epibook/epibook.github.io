// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

package com.epi;

import java.util.Random;

public class HashDictionary {
  private static String randString(int len) {
    StringBuilder ret = new StringBuilder();
    Random rnd = new Random();

    while (len-- > 0) {
      ret.append((char)(rnd.nextInt(26) + 97));
    }
    return ret.toString();
  }

  // @include
  public static int stringHash(String str, int modulus) {
    int kMult = 997;
    int val = 0;
    for (int i = 0; i < str.length(); i++) {
      char c = str.charAt(i);
      val = (val * kMult + c) % modulus;
    }
    return val;
  }
  // @exclude

  public static void main(String[] args) {
    String str = null;
    if (args.length == 1) {
      str = args[0];
    } else {
      Random rnd = new Random();
      str = randString(rnd.nextInt(20) + 1);
    }
    System.out.println("string = " + str);
    System.out.println(stringHash(str, 1 << 16));
  }
}
