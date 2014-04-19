package com.epi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class WordBreaking {
  private static String randString(int len) {
    Random r = new Random();
    StringBuilder ret = new StringBuilder(len);
    while (len-- > 0) {
      ret.append((char) (r.nextInt(26) + 'a'));
    }
    return ret.toString();
  }

  // @include
  public static ArrayList<String> wordBreaking(String s, Set<String> dict) {
    // T[i] stores the length of the last string which composed of s(0, i).
    int[] T = new int[s.length()];
    for (int i = 0; i < s.length(); ++i) {
      // Set T[i] if s(0, i) is a valid word.
      if (dict.contains(s.substring(0, i + 1))) {
        T[i] = i + 1;
      }

      // Set T[i] if T[j] != 0 and s(j + 1, i) is a valid word.
      for (int j = 0; j < i && T[i] == 0; ++j) {
        if (T[j] != 0 && (j + 1 < i - j)
            && dict.contains(s.substring(j + 1, i - j))) {
          T[i] = i - j;
        }
      }
    }

    ArrayList<String> ret = new ArrayList<String>();
    // s can be assembled by valid words.
    if (T[T.length - 1] != 0) {
      int idx = s.length() - 1;
      while (idx >= 0) {
        ret.add(s.substring(idx - T[idx] + 1, idx + 1));
        idx -= T[idx];
      }
      Collections.reverse(ret);
    }
    return ret;
  }

  // @exclude

  // Verify the strings in ans can be assembled into s.
  private static void checkAns(String s, List<String> ans) {
    String temp = "";
    System.out.println(s);
    for (int i = 0; i < ans.size(); ++i) {
      System.out.print(ans.get(i) + " ");
      temp += ans.get(i);
    }
    System.out.println();
    assert (ans.size() == 0 || s.equals(temp));
  }

  public static void main(String[] args) {
    Random r = new Random();
    for (int times = 0; times < 1000; ++times) {
      HashSet<String> dictionary = new HashSet<String>();
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
      ArrayList<String> ans = wordBreaking(target, dictionary);
      checkAns(target, ans);
      if (args.length == 2) {
        break;
      }
    }
  }
}
