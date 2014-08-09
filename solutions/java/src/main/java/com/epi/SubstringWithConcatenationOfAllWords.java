package com.epi;

import java.util.*;

public class SubstringWithConcatenationOfAllWords {
  // @include
  public static List<Integer>
  findAllSubstrings(String s, List<String> words) {
    Map<String, Integer> dict = new HashMap<>();
    for (String word : words) {
      increment(word, dict);
    }

    int unitSize = words.get(0).length();
    List<Integer> result = new ArrayList<>();
    for (int i = 0; i + unitSize * words.size() <= s.length(); ++i) {
      if (matchAllWordsInDict(s, dict, i, words.size(), unitSize)) {
        result.add(i);
      }
    }
    return result;
  }

  private static boolean
  matchAllWordsInDict(String s,
                      Map<String, Integer> dict,
                      int start, int numWords, int unitSize) {
    Map<String, Integer> currDict = new HashMap<>();
    for (int i = 0; i < numWords; ++i) {
      String currWord = s.substring(start + i * unitSize, start + (i + 1)
          * unitSize);
      Integer iter = dict.get(currWord);
      if (iter == null) {
        return false;
      }
      increment(currWord, currDict);
      if (currDict.get(currWord) > iter) {
        return false;
      }
    }
    return true;
  }

  private static void increment(String word, Map<String, Integer> dict) {
    Integer count = dict.get(word);
    if (count == null) {
      count = 0;
    }
    count++;
    dict.put(word, count);
  }
  // @exclude

  private static void smallTest() {
    String s = "barfoothefoobarman";
    List<Integer> result = findAllSubstrings(s, Arrays.asList("foo", "bar"));
    assert (result.size() == 2 && result.get(0) == 0 && result.get(1) == 9);
    s = "dcacdabcd";
    result = findAllSubstrings(s, Arrays.asList("cd", "ab"));
    assert (result.size() == 2 && result.get(0) == 3 && result.get(1) == 5);
  }

  public static void main(String[] args) {
    smallTest();
  }
}
