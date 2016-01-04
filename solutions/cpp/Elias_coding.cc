// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <iterator>
#include <random>
#include <string>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::random_device;
using std::string;
using std::uniform_int_distribution;
using std::vector;

string TransIntToBinary(int decimal);
int TransBinaryToInt(const string& binary);

// @include
string Encode(const vector<int>& A) {
  string ret;
  for (int a : A) {
    string binary = TransIntToBinary(a);
    binary.insert(0, binary.size() - 1, '0');  // prepend 0s.
    ret += binary;
  }
  return ret;
}

string TransIntToBinary(int decimal) {
  string ret;
  while (decimal) {
    ret.push_back('0' + (decimal % 2));
    decimal >>= 1;
  }
  reverse(ret.begin(), ret.end());
  return ret;
}

vector<int> Decode(const string& s) {
  vector<int> ret;
  int idx = 0;
  while (idx < s.size()) {
    // Count the number of consecutive 0s.
    int zero_idx = idx;
    while (zero_idx < s.size() && s[zero_idx] == '0') {
      ++zero_idx;
    }

    int len = zero_idx - idx + 1;
    ret.emplace_back(TransBinaryToInt(s.substr(zero_idx, len)));
    idx = zero_idx + len;
  }
  return ret;
}

int TransBinaryToInt(const string& binary) {
  int ret = 0;
  for (char c : binary) {
    ret = (ret * 2) + c - '0';
  }
  return ret;
}
// @exclude

int main(int argc, char* argv[]) {
  vector<int> A;
  default_random_engine gen((random_device())());
  if (argc == 1) {
    generate_n(back_inserter(A), uniform_int_distribution<int>(1, 10000)(gen),
               [&] { return uniform_int_distribution<int>()(gen); });
  } else {
    generate_n(back_inserter(A), atoi(argv[1]),
               [&] { return uniform_int_distribution<int>(4, 4)(gen); });
  }
  string ret = Encode(A);
  cout << ret << endl;

  vector<int> res = Decode(ret);
  assert(A.size() == res.size());
  for (int i = 0; i < A.size(); ++i) {
    assert(res[i] == A[i]);
  }
  return 0;
}
