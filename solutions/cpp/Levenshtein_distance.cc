// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <numeric>
#include <random>
#include <string>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::min;
using std::random_device;
using std::string;
using std::uniform_int_distribution;
using std::vector;

// @include
int LevenshteinDistance(string A, string B) {
  // Try to reduce the space usage.
  if (A.size() < B.size()) {
    swap(A, B);
  }

  vector<int> D(B.size() + 1);
  // Initialization.
  iota(D.begin(), D.end(), 0);

  for (int i = 1; i <= A.size(); ++i) {
    int pre_i_1_j_1 = D[0];  // Stores the value of D[i - 1][j - 1].
    D[0] = i;
    for (int j = 1; j <= B.size(); ++j) {
      int pre_i_1_j = D[j];  // Stores the value of D[i -1][j].
      D[j] = A[i - 1] == B[j - 1] ? pre_i_1_j_1
                                  : 1 + min(pre_i_1_j_1, min(D[j - 1], D[j]));
      // Previous D[i - 1][j] will become the next D[i - 1][j - 1].
      pre_i_1_j_1 = pre_i_1_j;
    }
  }
  return D.back();
}
// @exclude

string RandString(int len) {
  default_random_engine gen((random_device())());
  uniform_int_distribution<int> dis('a', 'z');
  string ret;
  while (len--) {
    ret += dis(gen);
  }
  return ret;
}

int main(int argc, char* argv[]) {
  default_random_engine gen((random_device())());
  string A, B;
  // Wiki example (http://en.wikipedia.org/wiki/Levenshtein_distance)
  A = "Saturday", B = "Sunday";
  assert(3 == LevenshteinDistance(A, B));
  A = "kitten", B = "sitting";
  assert(3 == LevenshteinDistance(A, B));

  if (argc == 3) {
    A = argv[1], B = argv[2];
  } else {
    uniform_int_distribution<int> dis(1, 100);
    A = RandString(dis(gen));
    B = RandString(dis(gen));
  }
  cout << A << endl << B << endl;
  cout << LevenshteinDistance(A, B) << endl;
  return 0;
}
