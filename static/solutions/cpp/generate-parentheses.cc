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

void DirectedGenerateBalancedParentheses(int, int, const string&,
                                         vector<string>*);

// @include
vector<string> GenerateBalancedParentheses(int num_pairs) {
  vector<string> result;
  DirectedGenerateBalancedParentheses(num_pairs, num_pairs, "", &result);
  return result;
}

void DirectedGenerateBalancedParentheses(int num_left_parens_needed,
                                         int num_right_parens_needed,
                                         const string& valid_prefix,
                                         vector<string>* result) {
  if (!num_left_parens_needed && !num_right_parens_needed) {
    result->emplace_back(valid_prefix);
    return;
  }

  if (num_left_parens_needed > 0) {  // Able to insert '('.
    DirectedGenerateBalancedParentheses(num_left_parens_needed - 1,
                                        num_right_parens_needed,
                                        valid_prefix + '(', result);
  }
  if (num_left_parens_needed < num_right_parens_needed) {
    // Able to insert ')'.
    DirectedGenerateBalancedParentheses(num_left_parens_needed,
                                        num_right_parens_needed - 1,
                                        valid_prefix + ')', result);
  }
}
// @exclude

void SmallTest() {
  auto result = GenerateBalancedParentheses(1);
  assert(result.size() == 1 && !result.front().compare("()"));
  result = GenerateBalancedParentheses(2);
  assert(
      result.size() == 2 &&
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
  auto result = GenerateBalancedParentheses(n);
  for (const string& s : result) {
    cout << s << " ";
  }
  cout << endl;
  return 0;
}
