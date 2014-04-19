package com.epi;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.Math.pow;

public class MultibetCardColorGame {
  // @include
  static double computeBestPayoffHelper(Double[][][] cache, double upperBound,
      int cash, int numRed, int numCards) {

    if (cash >= upperBound) {
      return cash;
    }

    if (numRed == numCards || numRed == 0) {
      return cash * pow(2, numCards);
    }

    if (cache[cash][numRed][numCards] == null) {
      double best = Double.MIN_VALUE;
      for (int bet = 0; bet <= cash; ++bet) {
        double redLowerBound = min(
            computeBestPayoffHelper(cache, upperBound, cash + bet, numRed - 1,
                numCards - 1),
            computeBestPayoffHelper(cache, upperBound, cash - bet, numRed,
                numCards - 1));

        double blackLowerBound = min(
            computeBestPayoffHelper(cache, upperBound, cash - bet, numRed - 1,
                numCards - 1),
            computeBestPayoffHelper(cache, upperBound, cash + bet, numRed,
                numCards - 1));
        best = max(best, max(redLowerBound, blackLowerBound));
      }

      cache[cash][numRed][numCards] = best;
    }

    return cache[cash][numRed][numCards];
  }

  static double computeBestPayoff(int cash) {
    double upperBound = 9.09 * cash;
    int numRed = 26;
    int numCards = 52;
    Double[][][] cache = new Double[(int) upperBound][numRed + 1][numCards + 1];
    return computeBestPayoffHelper(cache, upperBound, cash, 26, 52);
  }

  // @exclude

  public static void main(String[] args) {
    int ans = (int) computeBestPayoff(100);
    assert ans == 808;
    System.out.println("100 cash can get " + ans);
  }
}
