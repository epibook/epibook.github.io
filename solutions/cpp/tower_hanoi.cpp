// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <array>
#include <iostream>
#include <random>
#include <stack>

using std::array;
using std::cout;
using std::default_random_engine;
using std::endl;
using std::random_device;
using std::stack;
using std::uniform_int_distribution;

void Transfer(int n, array<stack<int>, 3>& pegs, int from, int to, int use);

// @include
void MoveTowerHanoi(int n) {
  array<stack<int>, 3> pegs;
  // Initialize pegs.
  for (int i = n; i >= 1; --i) {
    pegs[0].push(i);
  }

  Transfer(n, pegs, 0, 1, 2);
}

void Transfer(int n, array<stack<int>, 3>& pegs, int from, int to, int use) {
  if (n > 0) {
    Transfer(n - 1, pegs, from, use, to);
    pegs[to].push(pegs[from].top());
    pegs[from].pop();
    cout << "Move from peg " << from << " to peg " << to << endl;
    Transfer(n - 1, pegs, use, to, from);
  }
}
// @exclude

int main(int argc, char* argv[]) {
  int n;
  if (argc == 2) {
    n = atoi(argv[1]);
  } else {
    default_random_engine gen((random_device())());
    uniform_int_distribution<int> dis(1, 10);
    n = dis(gen);
  }
  cout << "n = " << n << endl;
  MoveTowerHanoi(n);
  return 0;
}
