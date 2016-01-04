// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <numeric>
#include <random>
#include <unordered_set>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::random_device;
using std::uniform_int_distribution;
using std::unordered_set;
using std::vector;

// @include
int MinimizeDifference(const vector<int>& A) {
  int sum = accumulate(A.cbegin(), A.cend(), 0);

  unordered_set<int> is_Ok;
  is_Ok.emplace(0);
  for (int item : A) {
    for (int v = sum / 2; v >= item; --v) {
      if (is_Ok.find(v - item) != is_Ok.cend()) {
        is_Ok.emplace(v);
      }
    }
  }

  // Finds the first i from middle where is_Ok[i] == true.
  for (int i = sum / 2; i > 0; --i) {
    if (is_Ok.find(i) != is_Ok.cend()) {
      return (sum - i) - i;
    }
  }
  return sum;  // One thief takes all.
}
// @exclude

void SmallTest() {
  // The example in the book.
  vector<int> A = {65,  35,  245, 195, 65,  150, 275, 155,
                   120, 320, 75,  40,  200, 100, 220, 99};
  assert(MinimizeDifference(A) == 1);
}

int main(int argc, char* argv[]) {
  SmallTest();
  default_random_engine gen((random_device())());
  int n;
  vector<int> A;
  if (argc == 2) {
    n = atoi(argv[1]);
  } else if (argc == 1) {
    uniform_int_distribution<int> dis(1, 50);
    n = dis(gen);
  } else {
    for (int i = 1; i < argc; ++i) {
      A.emplace_back(atoi(argv[i]));
    }
    n = 0;
  }
  for (size_t i = 0; i < n; ++i) {
    uniform_int_distribution<int> dis(0, 99);
    A.emplace_back(dis(gen));
    cout << A[i] << ' ';
  }
  cout << endl;
  int sum = accumulate(A.cbegin(), A.cend(), 0);
  cout << sum << endl;
  cout << "minimum difference = " << MinimizeDifference(A) << endl;
  return 0;
}
