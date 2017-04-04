// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#ifndef SOLUTIONS_CAN_STRING_BE_PALINDROME_HASH_H_
#define SOLUTIONS_CAN_STRING_BE_PALINDROME_HASH_H_

#include <string>
#include <unordered_map>
#include <utility>

using std::string;
using std::unordered_map;

namespace CanStringBeAPalindromeHash {

// @include
bool CanFormPalindrome(const string& s) {
  unordered_map<char, int> char_frequencies;
  // Compute the frequency of each char in s.
  for (char c : s) {
    ++char_frequencies[c];
  }

  // A string can be permuted as a palindrome if and only if the number of
  // chars whose frequencies is odd is at most 1.
  int odd_frequency_count = 0;
  return none_of(begin(char_frequencies), end(char_frequencies),
                 [&odd_frequency_count](const auto& p) {
                   return (p.second % 2) && ++odd_frequency_count > 1;
                 });
}
// @exclude

}  // namespace CanStringBeAPalindromeHash

#endif  // SOLUTIONS_CAN_STRING_BE_PALINDROME_HASH_H_
