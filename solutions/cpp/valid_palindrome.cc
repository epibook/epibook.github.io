// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <cctype>
#include <iostream>
#include <string>

using std::cout;
using std::endl;
using std::string;

// @include
bool IsPalindrome(const string& s) {
  // i moves forward, and j moves backward.
  int i = 0, j = s.size() - 1;
  while (i < j) {
    // i and j both skip non-alphanumeric characters.
    while (!isalnum(s[i]) && i < j) {
      ++i;
    }
    while (!isalnum(s[j]) && i < j) {
      --j;
    }
    if (tolower(s[i++]) != tolower(s[j--])) {
      return false;
    }
  }
  return true;
}
// @exclude

int main(int argc, char** argv) {
  assert(IsPalindrome("A man, a plan, a canal: Panama"));
  assert(!IsPalindrome("race a car"));
  assert(IsPalindrome("Able was I, ere I saw Elba!"));
  assert(!IsPalindrome("Ray a Ray"));
  assert(IsPalindrome(""));
  return 0;
}
