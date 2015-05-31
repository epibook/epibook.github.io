package com.epi;

import java.util.Random;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class LevenshteinDistance {
  // @include
  public static int levenshteinDistance(String A, String B) {
    // Try to reduce the space usage.
    if (A.length() < B.length()) {
      String temp = A;
      A = B;
      B = temp;
    }

    int[] D = new int[B.length() + 1];
    // Initialization.
    for (int i = 0; i < D.length; i++) {
      D[i] = i;
    }

    for (int i = 1; i <= A.length(); ++i) {
      int preI1J1 = D[0]; // Stores the value of D[i - 1][j - 1].
      D[0] = i;
      for (int j = 1; j <= B.length(); ++j) {
        int preI1J = D[j]; // Stores the value of D[i -1][j].
        D[j] = A.charAt(i - 1) == B.charAt(j - 1)
                   ? preI1J1
                   : 1 + Math.min(preI1J1, Math.min(D[j - 1], D[j]));
        // Previous D[i - 1][j] will become the next D[i - 1][j - 1].
        preI1J1 = preI1J;
      }
    }
    return D[D.length - 1];
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
