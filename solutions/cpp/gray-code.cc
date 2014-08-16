// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <bitset>
#include <cassert>
#include <iostream>
#include <random>
#include <string>
#include <vector>

using std::bitset;
using std::cout;
using std::default_random_engine;
using std::endl;
using std::random_device;
using std::stoi;
using std::string;
using std::uniform_int_distribution;
using std::vector;

// @include
vector<int> GrayCode(int n) {
  if (n == 0) {
    return {0};
  }
  if (n == 1) {
    return {0, 1};
  }

  auto prev_res = GrayCode(n - 1);  // Result prepends 0.
  // Creates result prepending 1.
  int leading_bit_one = 1 << (n - 1);
  vector<int> result;
  for (int i = prev_res.size() - 1; i >= 0; --i) {
    result.emplace_back(leading_bit_one + prev_res[i]);
  }
  prev_res.insert(prev_res.end(), result.begin(), result.end());
  return prev_res;
}
// @exclude

void SmallTest() {
  auto vec = GrayCode(3);
  vector<int> expected = {0, 1, 3, 2, 6, 7, 5, 4};
  assert(vec.size() == expected.size());
  assert(equal(vec.begin(), vec.end(), expected.begin()));
}

void CheckAns(const vector<int>& A) {
  for (size_t i = 1; i < A.size(); ++i) {
    int num_differ_bits = 0;
    bitset<10> prev(A[i - 1]), now(A[i]);
    string prev_s = prev.to_string(), now_s = now.to_string();
    for (size_t i = 0; i < 10; ++i) {
      if (prev_s[i] != now_s[i]) {
        ++num_differ_bits;
      }
    }
    assert(num_differ_bits == 1);
  }
}

int main(int argc, char** argv) {
  SmallTest();
  default_random_engine gen((random_device())());
  int n;
  if (argc == 2) {
    n = stoi(argv[1]);
  } else {
    uniform_int_distribution<int> dis(1, 9);
    n = dis(gen);
  }
  cout << "n = " << n << endl;
  auto vec = GrayCode(n);
  for (int a : vec) {
    cout << a << endl;
  }
  CheckAns(vec);
  return 0;
}
