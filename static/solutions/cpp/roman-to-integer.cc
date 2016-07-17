// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <string>
#include <unordered_map>

using std::cout;
using std::endl;
using std::string;
using std::unordered_map;

// @include
int RomanToInteger(const string& s) {
  unordered_map<char, int> T = {{'I', 1},   {'V', 5},   {'X', 10},  {'L', 50},
                                {'C', 100}, {'D', 500}, {'M', 1000}};

  int sum = T[s.back()];
  for (int i = s.length() - 2; i >= 0; --i) {
    if (T[s[i]] < T[s[i + 1]]) {
      sum -= T[s[i]];
    } else {
      sum += T[s[i]];
    }
  }
  return sum;
}
// @exclude

int main(int argc, char** argv) {
  assert(7 == RomanToInteger("VII"));
  assert(184 == RomanToInteger("CLXXXIV"));
  assert(9 == RomanToInteger("IX"));
  assert(40 == RomanToInteger("XL"));
  assert(60 == RomanToInteger("LX"));
  assert(1500 == RomanToInteger("MD"));
  assert(400 == RomanToInteger("CD"));
  assert(1900 == RomanToInteger("MCM"));
  assert(9919 == RomanToInteger("MMMMMMMMMCMXIX"));
  if (argc == 2) {
    cout << argv[1] << " equals to " << RomanToInteger(argv[1]) << endl;
  }
  return 0;
}
