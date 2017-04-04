// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <array>
#include <cassert>
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

void ComputeTowerHanoiSteps(int, array<stack<int>, 3>&, int, int, int);

static int num_steps = 0;

// @include
const int kNumPegs = 3;

void ComputeTowerHanoi(int num_rings) {
  array<stack<int>, kNumPegs> pegs;
  // Initialize pegs.
  for (int i = num_rings; i >= 1; --i) {
    pegs[0].push(i);
  }

  ComputeTowerHanoiSteps(num_rings, pegs, 0, 1, 2);
}

void ComputeTowerHanoiSteps(int num_rings_to_move,
                            array<stack<int>, kNumPegs>& pegs, int from_peg,
                            int to_peg, int use_peg) {
  if (num_rings_to_move > 0) {
    ComputeTowerHanoiSteps(num_rings_to_move - 1, pegs, from_peg, use_peg,
                           to_peg);
    pegs[to_peg].push(pegs[from_peg].top());
    pegs[from_peg].pop();
    cout << "Move from peg " << from_peg << " to peg " << to_peg << endl;
    // @exclude
    num_steps++;
    // @include
    ComputeTowerHanoiSteps(num_rings_to_move - 1, pegs, use_peg, to_peg,
                           from_peg);
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
  ComputeTowerHanoi(n);

  num_steps = 0;
  ComputeTowerHanoi(4);
  assert(15 == num_steps);

  num_steps = 0;
  ComputeTowerHanoi(1);
  assert(1 == num_steps);

  num_steps = 0;
  ComputeTowerHanoi(0);
  assert(0 == num_steps);

  num_steps = 0;
  ComputeTowerHanoi(10);
  assert(1023 == num_steps);

  return 0;
}
