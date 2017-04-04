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

void PhoneMnemonicHelper(const string&, int, string*, vector<string>*);

// @include
vector<string> PhoneMnemonic(const string& phone_number) {
  string partial_mnemonic(phone_number.size(), 0);
  vector<string> mnemonics;
  PhoneMnemonicHelper(phone_number, 0, &partial_mnemonic, &mnemonics);
  return mnemonics;
}

const int kNumTelDigits = 10;

// The mapping from digit to corresponding characters.
const array<string, kNumTelDigits> kMapping = {
    {"0", "1", "ABC", "DEF", "GHI", "JKL", "MNO", "PQRS", "TUV", "WXYZ"}};

void PhoneMnemonicHelper(const string& phone_number, int digit,
                         string* partial_mnemonic,
                         vector<string>* mnemonics) {
  if (digit == phone_number.size()) {
    // All digits are processed, so add partial_mnemonic to mnemonics.
    // (We add a copy since subsequent calls modify partial_mnemonic.)
    mnemonics->emplace_back(*partial_mnemonic);
  } else {
    // Try all possible characters for this digit.
    for (char c : kMapping[phone_number[digit] - '0']) {
      (*partial_mnemonic)[digit] = c;
      PhoneMnemonicHelper(phone_number, digit + 1, partial_mnemonic,
                          mnemonics);
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

int main(int argc, char* argv[]) {
  string num;
  if (argc == 2) {
    num = argv[1];
  } else {
    num = RandString(10);
  }
  auto result = PhoneMnemonic(num);
  cout << "number = " << num << endl;
  for (const string& str : result) {
    cout << str << endl;
  }
  return 0;
}
