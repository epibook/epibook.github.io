// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <stack>
#include <string>

using std::boolalpha;
using std::cout;
using std::endl;
using std::stack;
using std::string;

// @include
bool IsWellFormed(const string& s) {
  stack<char> left_chars;
  for (int i = 0; i < s.size(); ++i) {
    if (s[i] == '(' || s[i] == '{' || s[i] == '[') {
      left_chars.emplace(s[i]);
    } else {
      if (left_chars.empty()) {
        return false;  // Unmatched right char.
      }
      if ((s[i] == ')' && left_chars.top() != '(') ||
          (s[i] == '}' && left_chars.top() != '{') ||
          (s[i] == ']' && left_chars.top() != '[')) {
        return false;  // Mismatched chars.
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
