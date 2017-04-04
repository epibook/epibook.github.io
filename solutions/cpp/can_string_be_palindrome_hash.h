// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#ifndef SOLUTIONS_CPP_CAN_STRING_BE_PALINDROME_HASH_H_
#define SOLUTIONS_CPP_CAN_STRING_BE_PALINDROME_HASH_H_

#include <string>
#include <unordered_set>

using std::string;
using std::unordered_set;

namespace CanStringBeAPalindromeHash {

// @include
bool CanFormPalindrome(const string& s) {
  unordered_set<char> chars_with_odd_frequency;
  for (char c : s) {
    if (chars_with_odd_frequency.count(c)) {
      // c now has appeared an even number of times.
      chars_with_odd_frequency.erase(c);
    } else {
      // c now has appeared an odd number of times.
      chars_with_odd_frequency.insert(c);
    }
  }
  // A string can be permuted to form a palindrome if and only if the number
  // of chars whose frequencies is odd is at most 1.
  return chars_with_odd_frequency.size() <= 1;
}
// @exclude

}  // namespace CanStringBeAPalindromeHash

#endif  // SOLUTIONS_CPP_CAN_STRING_BE_PALINDROME_HASH_H_
