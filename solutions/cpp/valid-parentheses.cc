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
bool IsValidParentheses(const string& s) {
  stack<char> left_parens;
  for (size_t i = 0; i < s.size(); ++i) {
    if (s[i] == '(' || s[i] == '{' || s[i] == '[') {
      left_parens.emplace(s[i]);
    } else {
      if (left_parens.empty()) {
        return false;  // No left paren.
      }
      if ((s[i] == ')' && left_parens.top() != '(') ||
          (s[i] == '}' && left_parens.top() != '{') ||
          (s[i] == ']' && left_parens.top() != '[')) {
        return false;  // Unmatched left paren.
      }
      left_parens.pop();
    }
  }
  return left_parens.empty();
}
// @exclude

void small_test() {
  assert(IsValidParentheses("()"));
  assert(IsValidParentheses("()[]{}"));
  assert(IsValidParentheses("[()[]]{}"));
  assert(IsValidParentheses("(()[]{()[]{}{}})"));
  assert(!IsValidParentheses("([)]"));
  assert(!IsValidParentheses("["));
  assert(!IsValidParentheses("(()[]{()[]{})({}})"));
}

int main(int argc, char** argv) {
  small_test();
  if (argc == 2) {
    cout << boolalpha << IsValidParentheses(argv[1]) << endl;
  }
  return 0;
}
