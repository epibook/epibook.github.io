// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <random>
#include <string>

#include "./Can_string_be_palindrome_hash.h"
#include "./Can_string_be_palindrome_sorting.h"

using std::boolalpha;
using std::cout;
using std::default_random_engine;
using std::endl;
using std::random_device;
using std::string;
using std::uniform_int_distribution;

string RandString(int len) {
  string ret;
  default_random_engine gen((random_device())());
  while (len--) {
    uniform_int_distribution<int> dis('a', 'z');
    ret += dis(gen);
  }
  return ret;
}

int main(int argc, char *argv[]) {
  default_random_engine gen((random_device())());
  for (int times = 0; times < 1000; ++times) {
    string s;
    if (argc == 2) {
      s = argv[1];
    } else {
      uniform_int_distribution<int> dis(1, 10);
      s = RandString(dis(gen));
    }
    cout << s << endl;
    assert(CanStringBeAPalindromeHash::CanFormPalindrome(s) ==
           CanStringBeAPalindromeSorting::CanFormPalindrome(&s));
  }
  return 0;
}
