// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

package com.epi;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

class AnonymousLetter {
  private static String randString(int len) {
    StringBuilder ret = new StringBuilder();
    Random rnd = new Random();

    while (len-- > 0) {
      ret.append((char)(rnd.nextInt(26) + 97));
    }
    return ret.toString();
  }

  // @include
  public static boolean isLetterConstructibleFromMagazine(String letterText,
                                                          String magazineText) {
    Map<Character, Integer> charFrequencyForLetter = new HashMap<>();
    // Inserts all chars in letterText into a hash table.
    for (char c : letterText.toCharArray()) {
      if (!charFrequencyForLetter.containsKey(c)) {
        charFrequencyForLetter.put(c, 1);
      } else {
        charFrequencyForLetter.put(c, charFrequencyForLetter.get(c) + 1);
      }
    }

    // Checks characters in magazineText can cover characters in
    // magazineText.
    for (char c : magazineText.toCharArray()) {
      if (charFrequencyForLetter.containsKey(c)) {
        charFrequencyForLetter.put(c, charFrequencyForLetter.get(c) - 1);
        if (charFrequencyForLetter.get(c) == 0) {
          charFrequencyForLetter.remove(c);
          if (charFrequencyForLetter.isEmpty()) {
            return true;
          }
        }
      }
    }
    // Empty charFrequencyForLetter means every char in letterText can be
    // covered by a character in magazineText.
    return charFrequencyForLetter.isEmpty();
  }
  // @exclude

  public static void main(String[] args) {
    String L = null;
    String M = null;
    if (args.length == 2) {
      L = args[0];
      M = args[1];
    } else {
      Random rnd = new Random();
      L = randString(rnd.nextInt(1000) + 1);
      M = randString(rnd.nextInt(100000) + 1);
    }
    System.out.println(L);
    System.out.println(M);
    assert(!isLetterConstructibleFromMagazine("123", "456"));
    assert(!isLetterConstructibleFromMagazine("123", "12222222"));
    assert(isLetterConstructibleFromMagazine("123", "1123"));
    assert(isLetterConstructibleFromMagazine("123", "123"));
    System.out.println(isLetterConstructibleFromMagazine(L, M) ? "true"
                                                               : "false");
  }
}
