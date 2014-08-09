// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.
// @author Ivan Sharov

package com.epi;

import java.util.HashMap;
import java.util.Map;

class CanStringBePalindromeHash {
  // @include
  public static boolean canStringBeAPalindrome(String s) {
    Map<Character, Integer> hash = new HashMap<>();
    // Insert each char into hash.
    for (char c : s.toCharArray()) {
      if (!hash.containsKey(c)) {
        hash.put(c, 1);
      } else {
        hash.put(c, hash.get(c) + 1);
      }
    }

    // A string can be permuted as a palindrome if the number of odd time
    // chars <= 1.
    int oddCount = 0;
    for (Map.Entry<Character, Integer> p : hash.entrySet()) {
      if ((p.getValue() & 1) != 0 && ++oddCount > 1) {
        break;
      }
    }
    return oddCount <= 1;
  }
  // @exclude
}
