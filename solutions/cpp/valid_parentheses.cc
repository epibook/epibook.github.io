// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <stack>
#include <string>
#include <unordered_map>

using std::boolalpha;
using std::cout;
using std::endl;
using std::unordered_map;
using std::stack;
using std::string;

// @include
bool IsWellFormed(const string& s) {
  stack<char> left_chars;
  const unordered_map<char, char> kLookup = {
      {'(', ')'}, {'{', '}'}, {'[', ']'}};
  for (int i = 0; i < s.size(); ++i) {
    if (kLookup.count(s[i])) {
      left_chars.emplace(s[i]);
    } else {
      if (left_chars.empty() || kLookup.at(left_chars.top()) != s[i]) {
        // Unmatched right char or mismatched chars.
        return false;
      }
      left_chars.pop();
    }
  }
  return left_chars.empty();
}
// @exclude

void small_test() {
  assert(IsWellFormed("()"));
  assert(IsWellFormed("()[]{}"));
  assert(IsWellFormed("[()[]]{}"));
  assert(IsWellFormed("(()[]{()[]{}{}})"));
  assert(!IsWellFormed("([)]"));
  assert(!IsWellFormed("["));
  assert(!IsWellFormed("(()[]{()[]{})({}})"));
}

int main(int argc, char** argv) {
  small_test();
  if (argc == 2) {
    cout << boolalpha << IsWellFormed(argv[1]) << endl;
  }
  return 0;
}
