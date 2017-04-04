package com.epi;

public class CoinChange {
  // @include
  public static int changeMaking(int cents) {
    final int[] COINS = {100, 50, 25, 10, 5, 1};
    int numCoins = 0;
    for (int i = 0; i < COINS.length; i++) {
      numCoins += cents / COINS[i];
      cents %= COINS[i];
    }
    return numCoins;
  }
  // @exclude

  public static void main(String[] args) {
    assert(changeMaking(100) == 1);
    assert(changeMaking(101) == 2);
    assert(changeMaking(68) == 6);
    assert(changeMaking(13142) == 136);
  }
}
