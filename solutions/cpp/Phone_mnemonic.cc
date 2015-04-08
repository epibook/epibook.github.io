// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <array>
#include <iostream>
#include <random>
#include <string>
#include <vector>

using std::array;
using std::cout;
using std::default_random_engine;
using std::endl;
using std::random_device;
using std::string;
using std::vector;
using std::uniform_int_distribution;

vector<string> PhoneMnemonicHelper(const string&, int, string*);

// @include
vector<string> PhoneMnemonic(const string& phone_number) {
  string partial_mnemonic(phone_number.size(), 0);
  return PhoneMnemonicHelper(phone_number, 0, &partial_mnemonic);
}

const int kNumTelDigits = 10;

// The mapping from digit to corresponding characters.
const array<string, kNumTelDigits> M =
  {{"0", "1", "ABC", "DEF", "GHI", "JKL", "MNO", "PQRS", "TUV", "WXYZ"}};

vector<string> PhoneMnemonicHelper(const string& phone_number, int digit,
                                   string* partial_mnemonic) {
  if (digit == phone_number.size()) {
    // All digits are processed, so return partial_mnemonic.
    return {*partial_mnemonic};
  }

  // Try all possible characters for this digit.
  vector<string> result;
  for (const char &c : M[phone_number[digit] - '0']) {
    (*partial_mnemonic)[digit] = c;
    vector<string> new_result = PhoneMnemonicHelper(phone_number, digit + 1,
                                                    partial_mnemonic);
    result.insert(result.end(), new_result.begin(), new_result.end());
  }
  return result;
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
  auto result = PhoneMnemonic(num);
  cout << "number = " << num << endl;
  for (const auto& str : result) {
    cout << str << endl;
  }
  return 0;
}
