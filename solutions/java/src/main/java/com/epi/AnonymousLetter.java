// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

package com.epi;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

class AnonymousLetter {
  private static String randString(int len) {
    StringBuilder ret = new StringBuilder();
    Random rnd = new Random();

    while (len-- > 0) {
      ret.append((char) (rnd.nextInt(26) + 97));
    }
    return ret.toString();
  }

  // @include
  public static boolean anonymousLetter(String letter, String magazine) {
    Map<Character, Integer> hash = new HashMap<>();
    // Inserts all chars in letter into a hash table.
    for (char c : letter.toCharArray()) {
      if (!hash.containsKey(c)) {
        hash.put(c, 1);
      } else {
        hash.put(c, hash.get(c) + 1);
      }
    }

    // Checks characters in magazine that could cover characters in hash table.
    for (char c : magazine.toCharArray()) {
      if (hash.containsKey(c)) {
        hash.put(c, hash.get(c) - 1);
        if (hash.get(c) == 0) {
          hash.remove(c);
          if (hash.isEmpty()) {
            return true;
          }
        }
      }
    }
    // No entry in hash means letter can be covered by magazine.
    return hash.isEmpty();
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
    assert (!anonymousLetter("123", "456"));
    assert (!anonymousLetter("123", "12222222"));
    assert (anonymousLetter("123", "1123"));
    assert (anonymousLetter("123", "123"));
    System.out.println(anonymousLetter(L, M) ? "true" : "false");
  }
}
