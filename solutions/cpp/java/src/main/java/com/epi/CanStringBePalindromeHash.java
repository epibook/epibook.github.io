// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

package com.epi;

import java.util.HashMap;
import java.util.Map;

class CanStringBePalindromeHash {
  // @include
  public static boolean canStringBeAPalindrome(String s) {
    Map<Character, Integer> charFrequencies = new HashMap<>();
    // Compute the frequency of each char in s.
    for (char c : s.toCharArray()) {
      if (!charFrequencies.containsKey(c)) {
        charFrequencies.put(c, 1);
      } else {
        charFrequencies.put(c, charFrequencies.get(c) + 1);
      }
    }

    // A string can be permuted as a palindrome if and only if the number of
    // chars whose frequencies is odd is at most 1.
    int oddCount = 0;
    for (Map.Entry<Character, Integer> p : charFrequencies.entrySet()) {
      if ((p.getValue() % 2) != 0 && ++oddCount > 1) {
        return false;
      }
    }
    return true;
  }
  // @exclude
}
