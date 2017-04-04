// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <array>
#include <cassert>
#include <deque>
#include <iostream>
#include <random>
#include <string>
#include <vector>

using std::array;
using std::cout;
using std::default_random_engine;
using std::deque;
using std::endl;
using std::random_device;
using std::stoi;
using std::string;
using std::uniform_int_distribution;
using std::vector;

void append_roman_chars(int digit, size_t idx, const array<char, 9>& kSymbols,
                        string* ret);

// @include
string int_to_roman(int n) {
  // kSymbols represents {1000, 500, 100, 50, 10, 5, 1}.
  const array<char, 9> kSymbols = {'M', 'D', 'C', 'L', 'X', 'V', 'I'};
  int scale = 1000;
  string ret;
  for (size_t i = 0; n && i < kSymbols.size(); i += 2) {
    append_roman_chars(n / scale, i, kSymbols, &ret);
    n %= scale;
    scale /= 10;
  }
  return ret;
}

void append_roman_chars(int digit, size_t idx, const array<char, 9>& kSymbols,
                        string* ret) {
  if (digit == 0) {
    return;
  } else if (digit <= 3) {
    ret->append(digit, kSymbols[idx]);
  } else if (digit == 4) {
    ret->push_back(kSymbols[idx]);
    ret->push_back(kSymbols[idx - 1]);
  } else if (digit <= 8) {
    ret->push_back(kSymbols[idx - 1]);
    ret->append(digit - 5, kSymbols[idx]);
  } else {  // digit == 9.
    ret->push_back(kSymbols[idx]);
    ret->push_back(kSymbols[idx - 2]);
  }
}
// @exclude

void small_test() {
  assert(!int_to_roman(1).compare("I"));
  assert(!int_to_roman(4).compare("IV"));
  assert(!int_to_roman(39).compare("XXXIX"));
  assert(!int_to_roman(3225).compare("MMMCCXXV"));
  assert(!int_to_roman(3999).compare("MMMCMXCIX"));
}

int main(int argc, char** argv) {
  small_test();
  default_random_engine gen((random_device())());
  int n;
  if (argc == 2) {
    n = stoi(argv[1]);
  } else {
    uniform_int_distribution<int> dis(1, 3999);
    n = dis(gen);
  }
  cout << "n = " << n << endl;
  cout << int_to_roman(n) << endl;
  return 0;
}
