// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

package com.epi;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PhoneMnemonic {
  // @include
  public static List<String> phoneMnemonic(String phoneNumber) {
    char[] partialMnemonic = new char[phoneNumber.length()];
    List<String> mnemonics = new ArrayList<>();
    phoneMnemonicHelper(phoneNumber, 0, partialMnemonic, mnemonics);
    return mnemonics;
  }

  // The mapping from digit to corresponding characters.
  private static final String[] MAPPING
      = {"0", "1", "ABC", "DEF", "GHI", "JKL", "MNO", "PQRS", "TUV", "WXYZ"};

  private static void phoneMnemonicHelper(String phoneNumber, int digit,
                                          char[] partialMnemonic,
                                          List<String> mnemonics) {
    if (digit == phoneNumber.length()) {
      // All digits are processed, so add partialMnemonic to mnemonics.
      // (We add a copy since subsequent calls modify partialMnemonic.)
      mnemonics.add(new String(partialMnemonic));
    } else {
      // Try all possible characters for this digit.
      for (int i = 0; i < MAPPING[phoneNumber.charAt(digit) - '0'].length();
           ++i) {
        char c = MAPPING[phoneNumber.charAt(digit) - '0'].charAt(i);
        partialMnemonic[digit] = c;
        phoneMnemonicHelper(phoneNumber, digit + 1, partialMnemonic, mnemonics);
      }
    }
  }
  // @exclude

  static String randString(int len) {
    Random gen = new Random();
    StringBuilder ret = new StringBuilder();
    while (len-- > 0) {
      ret.append(gen.nextInt(10));
    }
    return ret.toString();
  }

  public static void main(String[] args) {
    String num;
    if (args.length == 1) {
      num = args[0];
    } else {
      num = randString(10);
    }
    List<String> result = phoneMnemonic(num);
    for (String s : result) {
      System.out.println(s);
    }
    System.out.println("number = " + num);
  }
}
