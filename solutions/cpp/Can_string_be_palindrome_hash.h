// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#ifndef SOLUTIONS_CAN_STRING_BE_PALINDROME_HASH_H_
#define SOLUTIONS_CAN_STRING_BE_PALINDROME_HASH_H_

#include <string>
#include <unordered_map>
#include <utility>

using std::pair;
using std::string;
using std::unordered_map;

namespace CanStringBeAPalindromeHash {

// @include
bool CanStringBeAPalindrome(const string& s) {
  unordered_map<char, int> hash;
  // Inserts each char into hash.
  for_each(s.begin(), s.end(), [&hash](const char & c) { ++hash[c]; });

  // A string can be permuted as a palindrome if the number of odd time
  // chars <= 1.
  int odd_count = 0;
  for (const pair<char, int>& p : hash) {
    if (p.second & 1 && ++odd_count > 1) {
      break;
    }
  }
  return odd_count <= 1;
}
// @exclude

}  // namespace CanStringBeAPalindromeHash

#endif  // SOLUTIONS_CAN_STRING_BE_PALINDROME_HASH_H_
