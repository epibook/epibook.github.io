// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <random>
#include <string>
#include <unordered_map>

using std::boolalpha;
using std::cout;
using std::default_random_engine;
using std::endl;
using std::random_device;
using std::string;
using std::uniform_int_distribution;
using std::unordered_map;
using std::vector;

string RandString(int len) {
  string ret;
  default_random_engine gen((random_device())());
  while (len--) {
    uniform_int_distribution<int> dis(0, 26);
    int x = dis(gen);
    ret += (x < 26) ? 'a' + x : ' ';
  }
  return ret;
}

// @include
bool IsLetterConstructibleFromMagazine(const string& letter_text,
                                       const string& magazine_text) {
  unordered_map<char, int> char_frequency_for_letter;
  // Compute the frequencies for all chars in letter_text.
  for (char c : letter_text) {
    ++char_frequency_for_letter[c];
  }

  // Check if the characters in magazine_text can cover characters
  // in char_frequency_for_letter.
  for (char c : magazine_text) {
    auto it = char_frequency_for_letter.find(c);
    if (it != char_frequency_for_letter.cend()) {
      --it->second;
      if (it->second == 0) {
        char_frequency_for_letter.erase(it);
        if (char_frequency_for_letter.empty()) {
          // All characters for letter_text are matched.
          break;
        }
      }
    }
  }
  // Empty char_frequency_for_letter means every char in letter_text can be
  // covered by a character in magazine_text.
  return char_frequency_for_letter.empty();
}
// @exclude

void SimpleTest() {
  assert(!IsLetterConstructibleFromMagazine("123", "456"));
  assert(!IsLetterConstructibleFromMagazine("123", "12222222"));
  assert(IsLetterConstructibleFromMagazine("123", "1123"));
  assert(IsLetterConstructibleFromMagazine("123", "123"));
  assert(!IsLetterConstructibleFromMagazine("12323", "123"));
  assert(
      IsLetterConstructibleFromMagazine("GATTACA", "A AD FS GA T ACA TTT"));
  assert(!IsLetterConstructibleFromMagazine("a", ""));
  assert(IsLetterConstructibleFromMagazine("aa", "aa"));
  assert(IsLetterConstructibleFromMagazine("aa", "aaa"));
  assert(IsLetterConstructibleFromMagazine("", "123"));
  assert(IsLetterConstructibleFromMagazine("", ""));
}

int main(int argc, char* argv[]) {
  SimpleTest();
  default_random_engine gen((random_device())());
  string L, M;
  if (argc == 3) {
    L = argv[1], M = argv[2];
  } else {
    uniform_int_distribution<int> L_dis(1, 1000);
    uniform_int_distribution<int> M_dis(1, 100000);
    L = RandString(L_dis(gen)), M = RandString(M_dis(gen));
  }
  cout << L << endl;
  cout << M << endl;
  cout << boolalpha << IsLetterConstructibleFromMagazine(L, M) << endl;
  return 0;
}
