// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <string>

using std::cout;
using std::endl;
using std::string;

bool IsMatchHere(const string &r, size_t r_idx, const string &s, size_t s_idx);

// @include
bool IsMatch(const string &r, const string &s) {
  // Case (2.): r starts with '^'.
  if (r.front() == '^') {
    return IsMatchHere(r, 1, s, 0);
  }

  for (size_t i = 0; i <= s.size(); ++i) {
    if (IsMatchHere(r, 0, s, i)) {
      return true;
    }
  }
  return false;
}

bool IsMatchHere(const string &r, size_t r_idx, const string &s,
                 size_t s_idx) {
  // Case (1.).
  if (r_idx == r.size()) {
    return true;
  }

  // Case (2): r ends with '$'.
  if (r_idx == r.size() - 1 && r[r_idx] == '$') {
    return s_idx == s.size();
  }

  // Case (4.).
  if (r.size() - r_idx >= 2 && r[r_idx + 1] == '*') {
    // Don't use any character of r[r_idx].
    if (IsMatchHere(r, r_idx + 2, s, s_idx)) {
      return true;
    }

    // Tries all possible number of r[r_idx].
    while (s_idx < s.size() && (r[r_idx] == '.' || r[r_idx] == s[s_idx])) {
      if (IsMatchHere(r, r_idx + 2, s, ++s_idx)) {
        return true;
      }
    }
  }

  // Case (3.).
  return s_idx < s.size() && (r[r_idx] == '.' || r[r_idx] == s[s_idx]) &&
         IsMatchHere(r, r_idx + 1, s, s_idx + 1);
}
// @exclude

int main(int argc, char *argv[]) {
  assert(IsMatch(".", "a"));
  assert(IsMatch("a", "a"));
  assert(!IsMatch("a", "b"));
  assert(IsMatch("a.9", "aW9"));
  assert(!IsMatch("a.9", "aW19"));
  assert(IsMatch("^a.9", "aW9"));
  assert(!IsMatch("^a.9", "baW19"));
  assert(IsMatch(".*", "a"));
  assert(IsMatch(".*", ""));
  assert(IsMatch("c*", "c"));
  assert(!IsMatch("aa*", "c"));
  assert(IsMatch("ca*", "c"));
  assert(IsMatch(".*",  "asdsdsa"));
  assert(IsMatch("9$" , "xxxxW19"));

  assert(IsMatch(".*a", "ba"));

  assert(IsMatch(".*a", "ba"));
  assert(IsMatch("^a.*9$", "axxxxW19"));

  assert(IsMatch("^a.*W19$", "axxxxW19"));
  assert(IsMatch(".*a.*W19", "axxxxW19123"));
  assert(!IsMatch(".*b.*W19", "axxxxW19123"));
  assert(IsMatch("n.*", "n"));
  assert(IsMatch("a*n.*", "an"));
  assert(IsMatch("a*n.*", "ana"));
  assert(IsMatch("a*n.*W19", "anaxxxxW19123"));
  assert(IsMatch(".*a*n.*W19", "asdaaadnanaxxxxW19123"));
  assert(IsMatch(".*.*.a*n.*W19", "asdaaadnanaxxxxW19123"));
  return 0;
}
