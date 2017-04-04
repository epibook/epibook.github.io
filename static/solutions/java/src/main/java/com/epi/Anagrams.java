// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

/*

@slug
anagrams

@summary
Anagrams are popular word play puzzles, where by rearranging
letters of one set of words, you get another set of words. For example, ``eleven
plus
two'' is an anagram for ``twelve plus one''.
Crossword puzzle enthusiasts and Scrabble players benefit from the ability to
view all possible anagrams of a given set of letters.


@problem
Write a program that takes as input a set of words
and returns groups of anagrams for those words.
Each group must contain at least two words.
*/

package com.epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class Anagrams {
  // @include
  public static List<List<String>> findAnagrams(List<String> dictionary) {
    Map<String, List<String>> sortedStringToAnagrams = new HashMap<>();
    for (String s : dictionary) {
      // Sorts the string, uses it as a key, and then appends
      // the original string as another value in the hash table.
      char[] sortedCharArray = s.toCharArray();
      Arrays.sort(sortedCharArray);
      String sortedStr = new String(sortedCharArray);
      if (!sortedStringToAnagrams.containsKey(sortedStr)) {
        sortedStringToAnagrams.put(sortedStr, new ArrayList<String>());
      }
      sortedStringToAnagrams.get(sortedStr).add(s);
    }

    List<List<String>> anagramGroups = new ArrayList<>();
    for (Map.Entry<String, List<String>> p :
         sortedStringToAnagrams.entrySet()) {
      if (p.getValue().size() >= 2) { // Found anagrams.
        anagramGroups.add(p.getValue());
      }
    }
    return anagramGroups;
  }
  // @exclude

  private static void smallTest() {
    List<String> dictionary
        = Arrays.asList("debit card", "bad credit", "the morse code",
                        "here come dots", "the eyes", "they see", "THL");
    List<List<String>> result = findAnagrams(dictionary);
    assert result.size() == 3;
  }

  private static String randString(int len) {
    StringBuilder ret = new StringBuilder();
    Random rnd = new Random();

    while (len-- > 0) {
      ret.append((char)(rnd.nextInt(26) + 97));
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
    List<String> dictionary = new ArrayList<>();
    for (String s : table) {
      dictionary.add(s);
    }
    findAnagrams(dictionary);
  }
}
