package com.epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class ScoreCombination {
  // @include
  public static int numCombinationsForFinalScore(
      int finalScore, List<Integer> individualPlayScores) {
    int[][] numCombinationsForScore
        = new int[individualPlayScores.size()][finalScore + 1];
    for (int i = 0; i < individualPlayScores.size(); ++i) {
      numCombinationsForScore[i][0] = 1; // One way to reach 0.
      for (int j = 1; j <= finalScore; ++j) {
        int withoutThisPlay
            = i - 1 >= 0 ? numCombinationsForScore[i - 1][j] : 0;
        int withThisPlay
            = j >= individualPlayScores.get(i)
                  ? numCombinationsForScore[i][j - individualPlayScores.get(i)]
                  : 0;
        numCombinationsForScore[i][j] = withoutThisPlay + withThisPlay;
      }
    }
    return numCombinationsForScore[individualPlayScores.size() - 1][finalScore];
  }
  // @exclude

  private static void simpleTest() {
    List<Integer> individualPlayScores = Arrays.asList(2, 3, 7);
    assert(4 == numCombinationsForFinalScore(12, individualPlayScores));
    assert(1 == numCombinationsForFinalScore(5, individualPlayScores));
    assert(3 == numCombinationsForFinalScore(9, individualPlayScores));
  }

  private static int checkAnswer(int totalScore, List<Integer> scoreWays) {
    int[] combinations = new int[totalScore + 1];
    combinations[0] = 1; // One way to reach 0.
    for (int score : scoreWays) {
      for (int j = score; j <= totalScore; ++j) {
        combinations[j] += combinations[j - score];
      }
    }
    return combinations[totalScore];
  }

  public static void main(String[] args) {
    simpleTest();
    Random r = new Random();
    int k;
    Set<Integer> individualPlayScoresSet = new HashSet<>();
    if (args.length == 0) {
      k = r.nextInt(1000);
      int size = r.nextInt(50) + 1;
      for (int i = 0; i < size; ++i) {
        individualPlayScoresSet.add(r.nextInt(1000) + 1);
      }
    } else if (args.length == 2) {
      k = Integer.parseInt(args[0]);
      int size = Integer.parseInt(args[1]);
      for (int i = 0; i < size; ++i) {
        individualPlayScoresSet.add(r.nextInt(1000) + 1);
      }
    } else {
      k = Integer.parseInt(args[0]);
      for (int i = 2; i < args.length; ++i) {
        individualPlayScoresSet.add(Integer.parseInt(args[i]));
      }
    }
    List<Integer> individualPlayScores
        = new ArrayList<>(individualPlayScoresSet);
    System.out.println(numCombinationsForFinalScore(k, individualPlayScores));
    assert(numCombinationsForFinalScore(k, individualPlayScores)
           == checkAnswer(k, individualPlayScores));
  }
}
