package com.epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author translated from c++ by Blazheev Alexander
 */
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
  public static int findPrettyPrinting(List<String> W, int L) {
    // Calculates M(i).
    long[] M = new long[W.size()];
    Arrays.fill(M, Long.MAX_VALUE);
    for (int i = 0; i < W.size(); ++i) {
      int bLen = L - W.get(i).length();
      M[i] = Math.min((i - 1 < 0 ? 0 : M[i - 1]) + (1 << bLen), M[i]);
      for (int j = i - 1; j >= 0; --j) {
        bLen -= (W.get(j).length() + 1);
        if (bLen < 0) {
          break;
        }
        M[i] = Math.min((j - 1 < 0 ? 0 : M[j - 1]) + (1 << bLen), M[i]);
      }
    }

    // Finds the minimum cost without considering the last line.
    long minMess = (W.size() >= 2 ? M[W.size() - 2] : 0);
    int bLen = L - W.get(W.size() - 1).length();
    for (int i = W.size() - 2; i >= 0; --i) {
      bLen -= (W.get(i).length() + 1);
      if (bLen < 0) {
        return (int)minMess;
      }
      minMess = Math.min(minMess, (i - 1 < 0 ? 0 : M[i - 1]));
    }
    return (int)minMess;
  }
  // @exclude

  public static void main(String[] args) {
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
    System.out.println(findPrettyPrinting(W, L));
  }
}
