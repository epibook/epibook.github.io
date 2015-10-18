// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

package com.epi;

import java.util.Arrays;

public class CanStringBePalindromeSorting {
  // @include
  public static boolean canFormPalindrome(String s) {
    char[] a = s.toCharArray();
    Arrays.sort(a);
    int oddCount = 0;
    int numCurrChar = 1;

    for (int i = 1; i < a.length && oddCount <= 1; ++i) {
      if (a[i] != a[i - 1]) {
        if ((numCurrChar % 2) != 0) {
          ++oddCount;
        }
        numCurrChar = 1;
      } else {
        ++numCurrChar;
      }
    }
    if ((numCurrChar & 1) != 0) {
      ++oddCount;
    }

    // A string can be permuted as a palindrome if the number of odd time
    // chars <= 1.
    return oddCount <= 1;
  }
  // @exclude
}
