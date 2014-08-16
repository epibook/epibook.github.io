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
using std::random_device;
using std::stack;
using std::stoi;
using std::string;
using std::uniform_int_distribution;
using std::vector;

void GenerateParenthesesHelper(int remained, int left_parens, string* s,
                               vector<string>* result);

// @include
vector<string> GenerateParentheses(int n) {
  string s;
  vector<string> result;
  GenerateParenthesesHelper(2 * n, 0, &s, &result);
  return result;
}

void GenerateParenthesesHelper(int remained, int left_parens, string* s,
                               vector<string>* result) {
  if (!remained) {
    result->emplace_back(*s);
    return;
  }

  if (left_parens < remained) {  // Able to insert '('.
    s->push_back('(');
    GenerateParenthesesHelper(remained - 1, left_parens + 1, s, result);
    s->pop_back();
  }
  if (left_parens > 0) {  // Able to insert ')'.
    s->push_back(')');
    GenerateParenthesesHelper(remained - 1, left_parens - 1, s, result);
    s->pop_back();
  }
}
// @exclude

void SmallTest() {
  auto result = GenerateParentheses(1);
  assert(result.size() == 1 && !result.front().compare("()"));
  result = GenerateParentheses(2);
  assert(result.size() == 2 &&
         ((!result.front().compare("(())") && !result.back().compare("()()")) ||
          (!result.front().compare("()()") && !result.back().compare("(())"))));
}

int main(int argc, char** argv) {
  SmallTest();
  default_random_engine gen((random_device())());
  int n;
  if (argc == 2) {
    n = stoi(argv[1]);
  } else {
    uniform_int_distribution<int> dis_n(0, 10);
    n = dis_n(gen);
  }
  cout << "n = " << n << endl;
  auto result = GenerateParentheses(n);
  for (const string& s : result) {
    cout << s << " ";
  }
  cout << endl;
  return 0;
}
