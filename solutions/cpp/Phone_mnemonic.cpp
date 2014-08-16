// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <array>
#include <iostream>
#include <random>
#include <string>

using std::array;
using std::cout;
using std::default_random_engine;
using std::endl;
using std::random_device;
using std::string;
using std::uniform_int_distribution;

void PhoneMnemonicHelper(const string &num, int d, string* ans);

// @include
void PhoneMnemonic(const string &num) {
  string ans(num.size(), 0);
  PhoneMnemonicHelper(num, 0, &ans);
}

const int kNumTelDigits = 10;

// The mapping from digit to corresponding charaters.
const array<string, kNumTelDigits> M = {{"0", "1", "ABC", "DEF", "GHI",
                                         "JKL", "MNO", "PQRS", "TUV",
                                         "WXYZ"}};

void PhoneMnemonicHelper(const string &num, int d, string* ans) {
  if (d == num.size()) {  // All digits are processed so we output answer.
    cout << *ans << endl;
  } else {
    // Try all corresponding characters for this digit.
    for (const char &c : M[num[d] - '0']) {
      (*ans)[d] = c;
      PhoneMnemonicHelper(num, d + 1, ans);
    }
  }
}
// @exclude

string RandString(int len) {
  default_random_engine gen((random_device())());
  string ret;
  while (len--) {
    uniform_int_distribution<char> dis('0', '9');
    ret += dis(gen);
  }
  return ret;
}

int main(int argc, char *argv[]) {
  string num;
  if (argc == 2) {
    num = argv[1];
  } else {
    num = RandString(10);
  }
  PhoneMnemonic(num);
  cout << "number = " << num << endl;
  return 0;
}
