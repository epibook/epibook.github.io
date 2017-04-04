// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

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
vector<int> GrayCode(int num_bits) {
  if (num_bits == 0) {
    return {0};
  }

  // These implicitly begin with 0 at bit-index (num_bits - 1).
  vector<int> gray_code_num_bits_minus_1 = GrayCode(num_bits - 1);

  // Now, add a 1 at bit-index (num_bits - 1) to all entries in
  // grayCodeNumBitsMinus1.
  int leading_bit_one = 1 << (num_bits - 1);
  // Process in reverse order to achieve reflection of
  // gray_code_num_bits_minus_1.
  for (int i = gray_code_num_bits_minus_1.size() - 1; i >= 0; --i) {
    gray_code_num_bits_minus_1.emplace_back(leading_bit_one |
                                            gray_code_num_bits_minus_1[i]);
  }
  return gray_code_num_bits_minus_1;
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
