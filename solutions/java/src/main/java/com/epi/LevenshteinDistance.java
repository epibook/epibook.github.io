package com.epi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class LevenshteinDistance {
  // @include
  public static int levenshteinDistance(String A, String B) {
    // Try to reduce the space usage.
    if (A.length() < B.length()) {
      String temp = A;
      A = B;
      B = temp;
    }

    List<Integer> D = new ArrayList<>(B.length() + 1);
    // Initialization.
    for (int i = 0; i < B.length() + 1; i++) {
      D.add(i);
    }

    for (int i = 1; i <= A.length(); ++i) {
      int preI1J1 = D.get(0); // Stores the value of D.get(i - 1).get(j - 1).
      D.set(0, i);
      for (int j = 1; j <= B.length(); ++j) {
        int preI1J = D.get(j); // Stores the value of D.get(i -1).get(j).
        D.set(j, A.charAt(i - 1) == B.charAt(j - 1)
                     ? preI1J1
                     : 1 + Math.min(preI1J1, Math.min(D.get(j - 1), D.get(j))));
        // Previous D.get(i - 1).get(j) will become the next D.get(i - 1).get(j
        // - 1).
        preI1J1 = preI1J;
      }
    }
    return D.get(D.size() - 1);
  }
  // @exclude

  private static String randString(int len) {
    Random r = new Random();
    StringBuilder ret = new StringBuilder(len);
    while (len-- > 0) {
      ret.append((char)(r.nextInt(26) + 'a'));
    }
    return ret.toString();
  }

  public static void main(String[] args) {
    Random r = new Random();
    String A, B;
    // Wiki example (http://en.wikipedia.org/wiki/levenshteinDistance)
    A = "Saturday";
    B = "Sunday";
    assert(3 == levenshteinDistance(A, B));
    A = "kitten";
    B = "sitting";
    assert(3 == levenshteinDistance(A, B));

    if (args.length == 2) {
      A = args[0];
      B = args[1];
    } else {
      A = randString(r.nextInt(100) + 1);
      B = randString(r.nextInt(100) + 1);
    }
    System.out.println(A + "\n" + B);
    System.out.println(levenshteinDistance(A, B));
  }
}
