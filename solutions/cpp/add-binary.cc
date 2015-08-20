// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <random>
#include <string>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::random_device;
using std::string;
using std::uniform_int_distribution;

// @include
string add_binary(string a, string b) {
  string sum;
  reverse(a.begin(), a.end()), reverse(b.begin(), b.end());
  bool have_carry = false;
  for (size_t i = 0; i < a.size() || i < b.size(); ++i) {
    int val_a = i < a.size() ? a[i] - '0' : 0,
        val_b = i < b.size() ? b[i] - '0' : 0;
    int val = val_a + val_b + have_carry;
    have_carry = val >= 2;
    sum += (val == 1 || val == 3) ? '1' : '0';
  }
  if (have_carry) {
    sum += '1';
  }
  reverse(sum.begin(), sum.end());
  return sum;
}
// @exclude

int main(int argc, char** argv) {
  if (argc == 3) {
    cout << "a = " << argv[1] << ", b = " << argv[2] << endl;
    cout << add_binary(argv[1], argv[2]) << endl;
  }
  assert(!add_binary("0", "0").compare("0"));
  assert(!add_binary("11", "1").compare("100"));
  assert(!add_binary("1", "0").compare("1"));
  assert(!add_binary("111", "1").compare("1000"));
  return 0;
}
