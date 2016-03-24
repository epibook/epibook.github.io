// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <deque>
#include <iostream>
#include <random>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::deque;
using std::endl;
using std::max;
using std::random_device;
using std::uniform_int_distribution;
using std::vector;

// @include
int CelebrityFinding(const vector<deque<bool>>& F) {
  // Start checking the relation from F[0][1].
  int i = 0, j = 1;
  while (j < F.size()) {
    if (F[i][j]) {
      i = j++;  // All candidates j' < j are not celebrity candidates.
    } else {  // F[i][j] == false.
      ++j;  // i is still a celebrity candidate but j is not.
    }
  }
  return i;
}
// @exclude

void DirectedTest() {
  vector<deque<bool>> F = {{false, true, false, true, false},
                           {false, false, true, true, false},
                           {false, false, false, true, true},
                           {false, false, false, false, false},
                           {true, false, false, true, false}};
  assert(CelebrityFinding(F) == 3);
}

int main(int argc, char* argv[]) {
  DirectedTest();
  default_random_engine gen((random_device())());
  for (int times = 0; times < 1000; ++times) {
    int n;
    if (argc > 1) {
      n = atoi(argv[1]);
    } else {
      uniform_int_distribution<int> dis(1, 1000);
      n = dis(gen);
    }
    vector<deque<bool>> graph(n, deque<bool>(n));
    for (size_t i = 0; i < n; ++i) {
      for (size_t j = 0; j < n; ++j) {
        uniform_int_distribution<int> zero_or_false(0, 1);
        graph[i][j] = zero_or_false(gen);
      }
      graph[i][i] = false;
    }
    uniform_int_distribution<int> dis(0, n - 1);
    int celebrity = dis(gen);
    for (size_t i = 0; i < n; ++i) {
      graph[i][celebrity] = true;
    }
    for (size_t j = 0; j < n; ++j) {
      graph[celebrity][j] = false;
    }
    cout << CelebrityFinding(graph) << endl;
    assert(celebrity == CelebrityFinding(graph));
  }
  return 0;
}
