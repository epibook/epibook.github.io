package com.epi;

import com.epi.utils.Pair;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Random;
import java.util.Set;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class TransformStringToOther {
  private static String randString(int len) {
    Random r = new Random();
    StringBuilder ret = new StringBuilder(len);
    while (len-- > 0) {
      ret.append((char)(r.nextInt(26) + 'a'));
    }
    return ret.toString();
  }

  // @include
  // Uses BFS to find the least steps of transformation.
  public static int transformString(Set<String> D, String s, String t) {
    LinkedList<Pair<String, Integer>> q = new LinkedList<>();
    D.remove(s); // Marks s as visited by erasing it in D.
    q.push(new Pair<>(s, 0));

    while (!q.isEmpty()) {
      Pair<String, Integer> f = q.peek();
      // Returns if we find a match.
      if (f.getFirst().equals(t)) {
        return f.getSecond(); // Number of steps reaches t.
      }

      // Tries all possible transformations of f.first.
      String str = f.getFirst();
      for (int i = 0; i < str.length(); ++i) {
        String strStart = i == 0 ? "" : str.substring(0, i);
        String strEnd = i + 1 < str.length() ? str.substring(i + 1) : "";
        for (int j = 0; j < 26; ++j) { // Iterates through 'a' ~ 'z'.
          String modStr = strStart + (char)('a' + j) + strEnd;
          if (D.remove(modStr)) {
            q.push(new Pair<>(modStr, f.getSecond() + 1));
          }
        }
      }
      q.pop();
    }

    return -1; // Cannot find a possible transformations.
  }
  // @exclude

  public static void main(String[] args) {
    Random r = new Random();
    int len;
    if (args.length == 1) {
      len = Integer.parseInt(args[0]);
    } else {
      len = r.nextInt(10) + 1;
    }
    String s = randString(len);
    String t = randString(len);
    Set<String> D = new HashSet<>();
    D.add(s);
    D.add(t);
    int n = r.nextInt(1000000) + 1;
    for (int i = 0; i < n; ++i) {
      D.add(randString(len));
    }
    System.out.println(D);
    System.out.println(s + " " + t + " " + D.size());
    System.out.println(transformString(D, s, t));
  }
}
