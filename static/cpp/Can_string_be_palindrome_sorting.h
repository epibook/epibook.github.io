// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#ifndef SOLUTIONS_CAN_STRING_BE_PALINDROME_SORTING_H_
#define SOLUTIONS_CAN_STRING_BE_PALINDROME_SORTING_H_

#include <algorithm>
#include <string>

using std::string;

namespace CanStringBeAPalindromeSorting {

// @include
bool CanFormPalindrome(string* s) {
  sort(s->begin(), s->end());
  int odd_count = 0, num_curr_char = 1;

  for (int i = 1; i < s->size() && odd_count <= 1; ++i) {
    if ((*s)[i] != (*s)[i - 1]) {
      if (num_curr_char % 2) {
        ++odd_count;
      }
      num_curr_char = 1;
    } else {
      ++num_curr_char;
    }
  }
  if (num_curr_char % 2) {
    ++odd_count;
  }

  // A string can be permuted as a palindrome if the number of odd time
  // chars <= 1.
  return odd_count <= 1;
}
// @exclude

}  // namespace CanStringBeAPalindromeSorting

#endif  // SOLUTIONS_CAN_STRING_BE_PALINDROME_SORTING_H_
