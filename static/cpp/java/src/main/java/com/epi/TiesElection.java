package com.epi;

import java.util.Arrays;
import java.util.List;

public class TiesElection {
  // @include
  // V contains the number of votes for each state.
  // This function returns the total number of ways to tie.
  public static long tiesElection(List<Integer> V) {
    int totalVotes = 0;
    for (int v : V) {
      totalVotes += v;
    }

    // No way to tie if the total number of votes is odd.
    if ((totalVotes % 2) != 0) {
      return 0;
    }

    long[] table = new long[totalVotes + 1];
    table[0] = 1; // Base condition: One way to reach 0.
    for (int i = 0; i < V.size(); ++i) {
      for (int j = totalVotes; j >= V.get(i); --j) {
        table[j] = table[j] + table[j - V.get(i)];
      }
    }
    return table[totalVotes / 2];
  }
  // @exclude

  private static void simpleTest() {
    List<Integer> votes = Arrays.asList(4, 5, 2, 7);
    System.out.println(tiesElection(votes));
    assert(tiesElection(votes) == 2);
  }

  public static void main(String[] args) {
    simpleTest();
    List<Integer> votes = Arrays.asList(
        9, 3, 11, 6, 55, 9, 7, 3, 29, 16, 4, 4, 20, 11, 6, 6, 8, 8, 4, 10, 11,
        16, 10, 6, 10, 3, 5, 6, 4, 14, 5, 29, 15, 3, 18, 7, 7, 20, 4, 9, 3, 11,
        38, 6, 3, 13, 12, 5, 10, 3, 3);
    assert(16976480564070L == tiesElection(votes));
    System.out.println(tiesElection(votes));
  }
}
