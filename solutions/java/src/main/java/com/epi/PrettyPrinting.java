package com.epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class PrettyPrinting {
  private static String randString(int len) {
    Random r = new Random();
    StringBuilder ret = new StringBuilder(len);
    while (len-- > 0) {
      ret.append((char)(r.nextInt(26) + 'a'));
    }
    return ret.toString();
  }

  // @include
  public static int minimumMessiness(List<String> words, int lineLength) {
    // minimumMessiness[i] is the minimum messiness when placing words[0 : i].
    int[] minimumMessiness = new int[words.size()];
    Arrays.fill(minimumMessiness, Integer.MAX_VALUE);
    int numRemainingBlanks = lineLength - words.get(0).length();
    minimumMessiness[0] = numRemainingBlanks * numRemainingBlanks;
    for (int i = 1; i < words.size(); ++i) {
      numRemainingBlanks = lineLength - words.get(i).length();
      minimumMessiness[i]
          = minimumMessiness[i - 1] + numRemainingBlanks * numRemainingBlanks;
      // Try adding words.get(i - 1), words.get(i - 2), ...
      for (int j = i - 1; j >= 0; --j) {
        numRemainingBlanks -= (words.get(j).length() + 1);
        if (numRemainingBlanks < 0) {
          // Not enough space to add more words.
          break;
        }
        int firstJMessiness = j - 1 < 0 ? 0 : minimumMessiness[j - 1];
        int currentLineMessiness = numRemainingBlanks * numRemainingBlanks;
        minimumMessiness[i] = Math.min(minimumMessiness[i],
                                       firstJMessiness + currentLineMessiness);
      }
    }
    return minimumMessiness[words.size() - 1];
  }
  // @exclude

  private static void smallTest() {
    assert(minimumMessiness(
               Arrays.asList("aaa", "bbb", "c", "d", "ee", "ff", "gggggg"), 11)
           == 45);
    assert(minimumMessiness(Arrays.asList("a", "b", "c", "d"), 5) == 8);
  }

  public static void main(String[] args) {
    smallTest();
    Random r = new Random();
    int n, L;
    if (args.length == 1) {
      n = Integer.parseInt(args[0]);
      L = r.nextInt(10) + 10;
    } else if (args.length == 2) {
      n = Integer.parseInt(args[0]);
      L = Integer.parseInt(args[1]);
    } else {
      n = r.nextInt(30) + 1;
      L = r.nextInt(10) + 11;
    }
    List<String> W = new ArrayList<>(n);
    for (int i = 0; i < n; ++i) {
      W.add(randString(r.nextInt(10) + 1));
    }
    System.out.println(W);
    System.out.println(L);
    System.out.println(minimumMessiness(W, L));
  }
}
