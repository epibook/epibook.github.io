// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <bitset>
#include <cassert>
#include <iostream>
#include <random>
#include <string>
#include <unordered_set>
#include <vector>

using std::bitset;
using std::cout;
using std::default_random_engine;
using std::endl;
using std::random_device;
using std::stoi;
using std::string;
using std::uniform_int_distribution;
using std::unordered_set;
using std::vector;

bool DirectedGrayCode(int, unordered_set<int>*, vector<int>*);
bool DiffersByOneBit(int, int);

// @include
vector<int> GrayCode(int num_bits) {
  vector<int> result({0});
  DirectedGrayCode(num_bits, new unordered_set<int>({0}), &result);
  return result;
}

bool DirectedGrayCode(int num_bits, unordered_set<int>* history,
                      vector<int>* result) {
  if (result->size() == (1 << num_bits)) {
    // Check if the first and last codes differ by one bit.
    return DiffersByOneBit(result->front(), result->back());
  }

  for (int i = 0; i < num_bits; ++i) {
    int previous_code = result->back();
    int candidate_next_code = previous_code ^ (1 << i);
    if (!history->count(candidate_next_code)) {
      history->emplace(candidate_next_code);
      result->emplace_back(candidate_next_code);
      if (DirectedGrayCode(num_bits, history, result)) {
        return true;
      }
      history->erase(candidate_next_code);
      result->pop_back();
    }
  }
  return false;
}

bool DiffersByOneBit(int x, int y) {
  int bit_difference = x ^ y;
  return bit_difference && !(bit_difference & (bit_difference - 1));
}
// @exclude

void SmallTest() {
  auto vec = GrayCode(3);
  vector<int> expected = {0, 1, 3, 2, 6, 7, 5, 4};
  assert(equal(vec.begin(), vec.end(), expected.begin(), expected.end()));
}

void CheckAns(const vector<int>& A) {
  for (size_t i = 0; i < A.size(); ++i) {
    int num_differ_bits = 0;
    bitset<10> prev(A[i]), now(A[(i + 1) % A.size()]);
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
