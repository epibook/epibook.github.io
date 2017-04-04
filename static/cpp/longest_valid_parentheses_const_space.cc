// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <random>
#include <stack>
#include <string>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::max;
using std::stack;
using std::string;
using std::random_device;
using std::uniform_int_distribution;
using std::vector;

int ParseFromLeft(const string&);
int ParseFromRight(const string&);
int Parse(const string&, char, int, int, int);

// @include
int LongestValidParentheses(const string& s) {
  return max(ParseFromLeft(s), ParseFromRight(s));
}

int ParseFromLeft(const string& s) {
  return Parse(s, '(', 0, s.size() - 1, 1);
}

int ParseFromRight(const string& s) {
  return Parse(s, ')', s.size() - 1, 0, -1);
}

int Parse(const string& s, char paren, int begin, int end, int dir) {
  int max_length = 0, num_parens_so_far = 0, length = 0;
  for (int i = dir * begin; i <= end; ++i) {
    if (s[dir * i] == paren) {
      ++num_parens_so_far, ++length;
    } else {  // s[dir * i] != paren
      if (num_parens_so_far <= 0) {
        num_parens_so_far = length = 0;
      } else {
        --num_parens_so_far, ++length;
        if (num_parens_so_far == 0) {
          max_length = max(max_length, length);
        }
      }
    }
  }
  return max_length;
}
// @exclude

int CheckAnswer(const string& s) {
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

void SmallTest() {
  assert(LongestValidParentheses(")(((())()(()(") == 6);
  assert(LongestValidParentheses("((())()(()(") == 6);
  assert(LongestValidParentheses(")(") == 0);
  assert(LongestValidParentheses("()") == 2);
  assert(LongestValidParentheses("") == 0);
  assert(LongestValidParentheses("()()())") == 6);
}

string RandString(int length) {
  default_random_engine gen((random_device())());
  string result;
  while (length--) {
    uniform_int_distribution<int> left_or_right(0, 1);
    result += left_or_right(gen) ? '(' : ')';
  }
  return result;
}

int main(int argc, char** argv) {
  SmallTest();
  if (argc == 2) {
    string s = argv[1];
    cout << "s = " << s << endl;
    cout << LongestValidParentheses(s) << endl;
  } else {
    default_random_engine gen((random_device())());
    uniform_int_distribution<int> dis(0, 100000);
    string s = RandString(dis(gen));
    assert(CheckAnswer(s) == LongestValidParentheses(s));
  }
  return 0;
}
