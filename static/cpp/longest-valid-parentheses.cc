// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <stack>
#include <string>
#include <vector>

using std::cout;
using std::endl;
using std::max;
using std::stack;
using std::string;
using std::vector;

// @include
int LongestValidParentheses(const string& s) {
  int max_length = 0, end = -1;
  stack<int> left_parentheses_indices;
  for (int i = 0; i < s.size(); ++i) {
    if (s[i] == '(') {
      left_parentheses_indices.emplace(i);
    } else if (left_parentheses_indices.empty()) {
      end = i;
    } else {
      left_parentheses_indices.pop();
      int start = left_parentheses_indices.empty()
                      ? end
                      : left_parentheses_indices.top();
      max_length = max(max_length, i - start);
    }
  }
  return max_length;
}
// @exclude

void SmallTest() {
  assert(LongestValidParentheses(")(((())()(()(") == 6);
  assert(LongestValidParentheses("((())()(()(") == 6);
  assert(LongestValidParentheses(")(") == 0);
  assert(LongestValidParentheses("()") == 2);
  assert(LongestValidParentheses("") == 0);
  assert(LongestValidParentheses("()()())") == 6);
}

int main(int argc, char** argv) {
  SmallTest();
  string s;
  if (argc == 2) {
    s = argv[1];
  }
  cout << "s = " << s << endl;
  cout << LongestValidParentheses(s) << endl;
  return 0;
}
