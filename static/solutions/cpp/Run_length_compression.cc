// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <cctype>
#include <cstdlib>
#include <iostream>
#include <string>

using std::cout;
using std::endl;
using std::string;
using std::to_string;

// @include
string Decoding(const string &s) {
  int count = 0;
  string result;
  for (const char &c : s) {
    if (isdigit(c)) {
      count = count * 10 + c - '0';
    } else {  // c is a letter of alphabet.
      result.append(count, c);  // Appends count copies of c to result.
      count = 0;
    }
  }
  return result;
}

string Encoding(const string &s) {
  string result;
  for (int i = 1, count = 1; i <= s.size(); ++i) {
    if (i == s.size() || s[i] != s[i - 1]) {
      // Found new character so write the count of previous character.
      result += to_string(count) + s[i - 1];
      count = 1;
    } else {  // s[i] == s[i - 1].
      ++count;
    }
  }
  return result;
}
// @exclude

int main(int argc, char *argv[]) {
  if (argc == 3) {
    cout << Encoding(argv[1]) << ' ' << Decoding(argv[2]) << endl;
  }
  assert(string("4a1b3c2a") == Encoding("aaaabcccaa"));
  assert(string("eeeffffee") == Decoding("3e4f2e"));
  assert(string("aaaaaaaaaaffffee") == Decoding("10a4f2e"));
  return 0;
}
