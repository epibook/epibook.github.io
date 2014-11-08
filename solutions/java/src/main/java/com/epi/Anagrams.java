// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

package com.epi;

import java.util.*;

class Anagrams {
  // @include
  public static List<List<String>> findAnagrams(String[] dictionary) {
    // Gets the sorted string and then insert into hash table.
    Map<String, List<String>> hash = new HashMap<>();
    for (String s : dictionary) {
      char[] sortedCharArray = s.toCharArray();
      // Uses sorted string as the hash code.
      Arrays.sort(sortedCharArray);
      String sortedStr = new String(sortedCharArray);
      if (!hash.containsKey(sortedStr)) {
        hash.put(sortedStr, new ArrayList<String>());
      }
      hash.get(sortedStr).add(s);
    }

    List<List<String>> anagrams = new ArrayList<>();
    for (Map.Entry<String, List<String>> p : hash.entrySet()) {
      // Multiple strings with the same hash code => anagrams.
      if (p.getValue().size() >= 2) {
        anagrams.add(p.getValue());
      }
    }
    return anagrams;
  }
  // @exclude

  private static void smallTest() {
    String[] dictionary = new String[]{"debit card", "bad credit", "the morse code", "here come dots", "the eyes", "they see", "THL"};
    List<List<String>> result = findAnagrams(dictionary);
    assert result.size() == 3;
  }

  private static String randString(int len) {
    StringBuilder ret = new StringBuilder();
    Random rnd = new Random();

    while (len-- > 0) {
      ret.append((char) (rnd.nextInt(26) + 97));
    }
    return ret.toString();
  }

  public static void main(String[] args) {
    smallTest();
    Random rnd = new Random();
    int n = rnd.nextInt(100000);
    Set<String> table = new HashSet<>();
    for (int i = 0; i < n; ++i) {
      table.add(randString(rnd.nextInt(12) + 1));
    }
    String[] dictionary = new String[table.size()];
    int idx = 0;
    for (String s : table) {
      dictionary[idx++] = s;
    }
    findAnagrams(dictionary);
  }
}
