package com.epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeftJustifiedText {
  // @include
  public static List<String> leftJustifiedText(List<String> words, int L) {
    int lastIdx = 0, numWords = 0, len = 0;
    List<String> res = new ArrayList<String>();
    for (int i = 0; i < words.size(); ++i) {
      ++numWords;
      int lineLen = len + words.get(i).length()
          + (numWords >= 2 ? numWords - 1 : 0);
      if (lineLen == L) {
        res.add(joinALineWithSpace(words, lastIdx, i, i - lastIdx));
        lastIdx = i + 1;
        numWords = 0;
        len = 0;
      } else if (lineLen > L) {
        res.add(joinALineWithSpace(words, lastIdx, i - 1, L - len));
        lastIdx = i;
        numWords = 1;
        len = words.get(i).length();
      } else { // lineLen < L.
        len += words.get(i).length();
      }
    }

    // Handles the last line.
    if (numWords != 0) {
      res.add(joinALineWithSpace(words, lastIdx, words.size() - 1, L - len));
    }
    return res;
  }

  // Joins strings in words[start : end].
  private static String joinALineWithSpace(List<String> words, int start,
      int end, int numSpaces) {
    int numWords = end - start + 1;
    StringBuilder line = new StringBuilder();
    for (int i = start; i < end; ++i) {
      line.append(words.get(i));
      int numCurrSpace = (int) Math.ceil((double) numSpaces / numWords - 1);
      for (int j = 0; j < numCurrSpace; j++) {
        line.append(' ');
      }
      numSpaces -= numCurrSpace;
      --numWords;
    }
    line.append(words.get(end));
    for (int j = 0; j < numSpaces; j++) {
      line.append(' ');
    }
    return line.toString();
  }

  // @exclude

  private static void testCase(List<String> words, int L) {
    List<String> res = leftJustifiedText(words, L);
    for (String s : res) {
      System.out.println("\"" + s + "\"");
    }
    System.out.println();
  }

  public static void main(String[] args) {
    List<String> words = Arrays.asList("Text", "justification", "is",
        "trickier", "than", "it", "seems!");
    int L = 14;
    System.out.println("L = " + L);
    testCase(words, L);
    words = Arrays.asList("Listen", "to", "many,", "speak", "to", "a", "few.");
    L = 6;
    System.out.println("L = " + L);
    testCase(words, L);
    words = Arrays.asList("The", "quick", "brown", "fox", "jumped", "over",
        "the", "lazy", "dogs.");
    L = 11;
    System.out.println("L = " + L);
    testCase(words, L);
    L = 16;
    System.out.println("L = " + L);
    testCase(words, L);
    L = 17;
    System.out.println("L = " + L);
    testCase(words, L);
  }
}
