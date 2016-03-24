package com.epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class WordBreaking {
  private static String randString(int len) {
    Random r = new Random();
    StringBuilder ret = new StringBuilder(len);
    while (len-- > 0) {
      ret.append((char)(r.nextInt(26) + 'a'));
    }
    return ret.toString();
  }

  // @include
  public static List<String> decomposeIntoDictionaryWords(
      String domain, Set<String> dictionary) {
    int[] lastLength = new int[domain.length()];
    Arrays.fill(lastLength, -1);
    // When the algorithm finishes, lastLength[i] != -1 indicates
    // domain.substring(0, i + 1) has a valid decomposition, and the length of
    // the last string in the decomposition will be lastLength[i].

    for (int i = 0; i < domain.length(); ++i) {
      // If domain.substring(0, i + 1) is a valid word, set lastLength[i] to the
      // length of that word.
      if (dictionary.contains(domain.substring(0, i + 1))) {
        lastLength[i] = i + 1;
      }

      // If lastLength[i] = -1 look for j < i such that domain.substring(0, j +
      // 1) has a valid decomposition and domain.substring(j + 1, i + 1) is a
      // dictionary word. If so, record the length of that word in
      // lastLength[i].
      if (lastLength[i] == -1) {
        for (int j = 0; j < i; ++j) {
          if (lastLength[j] != -1
              && dictionary.contains(domain.substring(j + 1, i + 1))) {
            lastLength[i] = i - j;
            break;
          }
        }
      }
    }

    List<String> decompositions = new ArrayList<>();
    if (lastLength[lastLength.length - 1] != -1) {
      // domain can be assembled by valid words.
      int idx = domain.length() - 1;
      while (idx >= 0) {
        decompositions.add(
            domain.substring(idx + 1 - lastLength[idx], idx + 1));
        idx -= lastLength[idx];
      }
      Collections.reverse(decompositions);
    }
    return decompositions;
  }
  // @exclude

  // Verify the strings in ans can be assembled into s.
  private static void checkAns(String s, List<String> ans) {
    StringBuilder temp = new StringBuilder();
    System.out.println(s);
    for (String an : ans) {
      System.out.print(an + " ");
      temp.append(an);
    }
    System.out.println();
    assert(ans.isEmpty() || s.equals(temp.toString()));
  }

  private static void smallCase() {
    Set<String> dictionary = new HashSet<>();
    dictionary.add("bed");
    dictionary.add("bath");
    dictionary.add("and");
    dictionary.add("hand");
    dictionary.add("beyond");
    List<String> ans
        = decomposeIntoDictionaryWords("bedbathandbeyond", dictionary);
    List<String> goldenAns = Arrays.asList("bed", "bath", "and", "beyond");
    assert(ans.equals(goldenAns));
    checkAns("bedbathandbeyond", goldenAns);

    dictionary = new HashSet<>();
    dictionary.add("aa");
    dictionary.add("b");
    dictionary.add("ccc");

    ans = decomposeIntoDictionaryWords("b", dictionary);
    goldenAns = Arrays.asList("b");
    assert(ans.equals(goldenAns));
    checkAns("b", goldenAns);

    ans = decomposeIntoDictionaryWords("ccc", dictionary);
    goldenAns = Arrays.asList("ccc");
    assert(ans.equals(goldenAns));
    checkAns("ccc", goldenAns);

    ans = decomposeIntoDictionaryWords("aabccc", dictionary);
    goldenAns = Arrays.asList("aa", "b", "ccc");
    assert(ans.equals(goldenAns));
    checkAns("aabccc", goldenAns);

    ans = decomposeIntoDictionaryWords("baabccc", dictionary);
    goldenAns = Arrays.asList("b", "aa", "b", "ccc");
    assert(ans.equals(goldenAns));
    checkAns("baabccc", goldenAns);

    dictionary.add("bb");
    ans = decomposeIntoDictionaryWords("bbb", dictionary);
    // Note: goldenAns relies on how our algorithm is implemented: our
    // algorithm chooses longest word ending at that index, so the answer
    // is "b", "bb", not "b", "b", "b" or "bb", "b".
    goldenAns = Arrays.asList("b", "bb");
    assert(ans.equals(goldenAns));
    checkAns("bbb", goldenAns);

    ans = decomposeIntoDictionaryWords("bbcccb", dictionary);
    goldenAns = Arrays.asList("bb", "ccc", "b");
    assert(ans.equals(goldenAns));
    checkAns("bbcccb", goldenAns);

    ans = decomposeIntoDictionaryWords("bbcccbabb", dictionary);
    goldenAns = Arrays.asList();
    assert(ans.equals(goldenAns));

    ans = decomposeIntoDictionaryWords("d", dictionary);
    goldenAns = Arrays.asList();
    assert(ans.equals(goldenAns));
  }

  public static void main(String[] args) {
    smallCase();
    Random r = new Random();
    for (int times = 0; times < 1000; ++times) {
      Set<String> dictionary = new HashSet<>();
      String target;
      if (args.length >= 2) {
        target = args[0];
        for (int i = 3; i < args.length; ++i) {
          dictionary.add(args[i]);
        }
      } else if (args.length == 1) {
        target = args[0];
        int n = r.nextInt(10000) + 1;
        while (n-- != 0) {
          dictionary.add(randString(r.nextInt(15) + 1));
        }
      } else {
        target = randString(r.nextInt(50) + 1);
        int n = r.nextInt(10000) + 1;
        while (n-- != 0) {
          dictionary.add(randString(r.nextInt(15) + 1));
        }
      }
      List<String> ans = decomposeIntoDictionaryWords(target, dictionary);
      checkAns(target, ans);
      if (args.length == 2) {
        break;
      }
    }
  }
}
