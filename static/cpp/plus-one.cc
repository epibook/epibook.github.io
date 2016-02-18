// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <random>
#include <string>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::random_device;
using std::uniform_int_distribution;
using std::stoul;
using std::vector;

// @include
vector<int> PlusOne(vector<int> A) {
  ++A.back();
  for (int i = A.size() - 1; i > 0 && A[i] == 10; --i) {
    A[i] = 0, ++A[i - 1];
  }
  if (A[0] == 10) {
    // Need additional digit as the most significant digit (i.e., A[0]) has a
    // carry-out.
    A[0] = 0;
    A.insert(A.begin(), 1);
  }
  return A;
}
// @exclude

vector<int> RandVector(size_t len) {
  if (!len) {
    return {0};
  }

  default_random_engine gen((random_device())());
  vector<int> A;
  uniform_int_distribution<int> dis_first(1, 9);
  A.emplace_back(dis_first(gen));
  --len;
  while (len) {
    uniform_int_distribution<int> dis(0, 9);
    A.emplace_back(dis(gen));
    --len;
  }
  return A;
}

void SmallTest() {
  auto result = PlusOne({9, 9});
  assert(result.size() == 3 && result[0] == 1 && result[1] == 0 &&
         result[2] == 0);
  result = PlusOne({3, 1, 4});
  assert(result.size() == 3 && result[0] == 3 && result[1] == 1 &&
         result[2] == 5);
}

int main(int argc, char** argv) {
  SmallTest();
  default_random_engine gen((random_device())());
  size_t n;
  if (argc == 2) {
    n = stoul(argv[1]);
  } else {
    uniform_int_distribution<size_t> dis(0, 1000);
    n = dis(gen);
  }
  auto A = RandVector(n);
  for (int a : A) {
    cout << a;
  }
  cout << endl << endl;
  auto result = PlusOne(A);
  for (int a : result) {
    cout << a;
  }
  cout << endl;
  return 0;
}
