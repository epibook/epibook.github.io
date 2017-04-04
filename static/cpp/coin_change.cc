// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <array>
#include <cassert>

using std::array;

// @include
int ChangeMaking(int cents) {
  const array<int, 6> kCoins = {100, 50, 25, 10, 5, 1};
  int num_coins = 0;
  for (int i = 0; i < kCoins.size(); i++) {
    num_coins += cents / kCoins[i];
    cents %= kCoins[i];
  }
  return num_coins;
}
// @exclude

int main(int argc, char* argv[]) {
  assert(ChangeMaking(100) == 1);
  assert(ChangeMaking(101) == 2);
  assert(ChangeMaking(68) == 6);
  assert(ChangeMaking(13142) == 136);
  return 0;
}
