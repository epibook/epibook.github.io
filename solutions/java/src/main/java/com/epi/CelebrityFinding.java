package com.epi;

import java.util.Random;

public class CelebrityFinding {
  // @include
  public static int celebrityFinding(boolean[][] f) {
    // Start checking the relation from f[0][1].
    int i = 0, j = 1;
    while (j < f.length) {
      if (f[i][j]) {
        i = j++; // All candidates j' < j are not celebrity candidates.
      } else { // f[i][j] == false.
        ++j; // i is still a celebrity candidate but j is not.
      }
    }
    return i;
  }
  // @exclude

  public static void main(String[] args) {
    Random gen = new Random();
    for (int times = 0; times < 1000; ++times) {
      int n;
      if (args.length > 0) {
        n = Integer.valueOf(args[0]);
      } else {
        n = gen.nextInt(1000) + 1;
      }
      boolean[][] graph = new boolean[n][n];
      for (int i = 0; i < n; ++i) {
        for (int j = 0; j < n; ++j) {
          graph[i][j] = gen.nextBoolean();
        }
        graph[i][i] = false;
      }
      int celebrity = gen.nextInt(n);
      for (int i = 0; i < n; ++i) {
        graph[i][celebrity] = true;
      }
      for (int j = 0; j < n; ++j) {
        graph[celebrity][j] = false;
      }
      System.out.println(celebrityFinding(graph));
      assert celebrity == celebrityFinding(graph);
    }
  }
}
