package com.epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JustifyText {
  // @include
  public static List<String> justifyText(String[] words, int L) {
    int currLineStart = 0, numWordsCurrLine = 0, currLineLength = 0;
    List<String> result = new ArrayList<>();
    for (int i = 0; i < words.length; ++i) {
      // currLineStart is the first word in the current line, and i is used to
      // identify the last word.
      ++numWordsCurrLine;
      int lookaheadLineLength
          = currLineLength + words[i].length() + (numWordsCurrLine - 1);
      if (lookaheadLineLength == L) {
        result.add(
            joinALineWithSpace(words, currLineStart, i, i - currLineStart));
        currLineStart = i + 1;
        numWordsCurrLine = 0;
        currLineLength = 0;
      } else if (lookaheadLineLength > L) {
        result.add(joinALineWithSpace(words, currLineStart, i - 1,
                                      L - currLineLength));
        currLineStart = i;
        numWordsCurrLine = 1;
        currLineLength = words[i].length();
      } else { // lookaheadLineLength < L.
        currLineLength += words[i].length();
      }
    }

    // Handles the last line. Last line is to be left-aligned.
    if (numWordsCurrLine > 0) {
      StringBuilder line = new StringBuilder(joinALineWithSpace(
          words, currLineStart, words.length - 1, numWordsCurrLine - 1));
      for (int i = 0; i < L - currLineLength - (numWordsCurrLine - 1); i++) {
        line.append(' ');
      }
      result.add(line.toString());
    }
    return result;
  }

  // Joins strings in words[start : end] with numSpaces spaces spread evenly.
  private static String joinALineWithSpace(String[] words, int start, int end,
                                           int numSpaces) {
    int numWordsCurrLine = end - start + 1;
    StringBuilder line = new StringBuilder();
    for (int i = start; i < end; ++i) {
      line.append(words[i]);
      --numWordsCurrLine;
      int numCurrSpace = (int)Math.ceil((double)numSpaces / numWordsCurrLine);
      for (int j = 0; j < numCurrSpace; j++) {
        line.append(' ');
      }
      numSpaces -= numCurrSpace;
    }
    line.append(words[end]);
    for (int i = 0; i < numSpaces; i++) {
      line.append(' ');
    }
    return line.toString();
  }
  // @exclude

  private static void testCase(String[] words, int L, String[] golden) {
    List<String> result = justifyText(words, L);
    for (String s : result) {
      System.out.println("\"" + s + "\"");
    }
    System.out.println();
    assert(Arrays.equals(result.toArray(), golden));
  }

  public static void main(String[] args) {
    String words[]
        = {"Text", "justification", "is", "trickier", "than", "it", "seems!"};
    String golden[] = {"Text          ", "justification ", "is    trickier",
                       "than it seems!"};
    int L = 14;
    System.out.println("L = " + L);
    testCase(words, L, golden);
    words = new String[] {"Listen", "to", "many,", "speak", "to", "a", "few."};
    golden = new String[] {"Listen", "to    ", "many, ",
                           "speak ", "to   a", "few.  "};
    L = 6;
    System.out.println("L = " + L);
    testCase(words, L, golden);
    words = new String[] {"The",  "quick", "brown", "fox",  "jumped",
                          "over", "the",   "lazy",  "dogs."};
    golden = new String[] {"The   quick", "brown   fox", "jumped over",
                           "the    lazy", "dogs.      "};
    L = 11;
    System.out.println("L = " + L);
    testCase(words, L, golden);
    golden = new String[] {"The  quick brown", "fox  jumped over",
                           "the lazy dogs.  "};
    L = 16;
    System.out.println("L = " + L);
    testCase(words, L, golden);
    golden = new String[] {"The  quick  brown", "fox  jumped  over",
                           "the lazy dogs.   "};
    L = 17;
    System.out.println("L = " + L);
    testCase(words, L, golden);
    words = new String[] {"Hello", "World"};
    golden = new String[] {"Hello World   "};
    L = 14;
    System.out.println("L = " + L);
    testCase(words, L, golden);
  }
}
