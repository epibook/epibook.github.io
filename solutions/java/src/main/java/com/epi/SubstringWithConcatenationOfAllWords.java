package com.epi;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubstringWithConcatenationOfAllWords {
  // @include
  public static List<Integer> findAllSubstrings(String s, List<String> words) {
    Map<String, Integer> wordToFreq = new HashMap<>();
    for (String word : words) {
      increment(word, wordToFreq);
    }

    int unitSize = words.get(0).length();
    List<Integer> result = new ArrayList<>();
    for (int i = 0; i + unitSize * words.size() <= s.length(); ++i) {
      if (matchAllWordsInDict(s, wordToFreq, i, words.size(), unitSize)) {
        result.add(i);
      }
    }
    return result;
  }

  private static boolean matchAllWordsInDict(String s,
                                             Map<String, Integer> wordToFreq,
                                             int start, int numWords,
                                             int unitSize) {
    Map<String, Integer> currStringToFreq = new HashMap<>();
    for (int i = 0; i < numWords; ++i) {
      String currWord
          = s.substring(start + i * unitSize, start + (i + 1) * unitSize);
      Integer freq = wordToFreq.get(currWord);
      if (freq == null) {
        return false;
      }
      increment(currWord, currStringToFreq);
      if (currStringToFreq.get(currWord) > freq) {
        // currWord occurs too many times for a match to be possible.
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
    List<String> A = Arrays.asList("foo", "bar");
    List<Integer> result = findAllSubstrings(s, A);
    assert(result.size() == 2 && result.get(0) == 0 && result.get(1) == 9);
    s = "dcacdabcd";
    A = Arrays.asList("cd", "ab");
    result = findAllSubstrings(s, A);
    assert(result.size() == 2 && result.get(0) == 3 && result.get(1) == 5);
  }

  public static void main(String[] args) { smallTest(); }
}
