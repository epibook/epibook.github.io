// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <string>

using std::cout;
using std::endl;
using std::string;

bool IsMatchHere(const string &, int, const string &, int);

// @include
bool IsMatch(const string &regex, const string &s) {
  // Case (2.): regex starts with '^'.
  if (regex.front() == '^') {
    return IsMatchHere(regex, 1, s, 0);
  }

  for (int i = 0; i <= s.size(); ++i) {
    if (IsMatchHere(regex, 0, s, i)) {
      return true;
    }
  }
  return false;
}

bool IsMatchHere(const string &regex, int regex_offset, const string &s,
                 int s_offset) {
  if (regex_offset == regex.size()) {
    // Case (1.): Empty regex matches all strings.
    return true;
  }

  if (regex_offset == regex.size() - 1 && regex[regex_offset] == '$') {
    // Case (2.): Reach the end of regex, and last char is '$'.
    return s_offset == s.size();
  }

  if (regex.size() - regex_offset >= 2 && regex[regex_offset + 1] == '*') {
    // Case (3.): A '*' match.
    // Iterate through s, checking '*' condition, if '*' condition holds,
    // performs the remaining checks.
    for (int i = s_offset + 1;
         i < s.size() &&
         (regex[regex_offset] == '.' || regex[regex_offset] == s[i - 1]);
         ++i) {
      if (IsMatchHere(regex, regex_offset + 2, s, i)) {
        return true;
      }
    }
    // See '*' matches zero character in s[s_offset : s.size() - 1].
    return IsMatchHere(regex, regex_offset + 2, s, s_offset);
  }

  // Case (4.): regex begins with single character match.
  return s_offset < s.size() &&
         (regex[regex_offset] == '.' || regex[regex_offset] == s[s_offset]) &&
         IsMatchHere(regex, regex_offset + 1, s, s_offset + 1);
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
  assert(IsMatch(".*", "asdsdsa"));
  assert(IsMatch("9$", "xxxxW19"));

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
