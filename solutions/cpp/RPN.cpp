// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <sstream>
#include <stack>
#include <string>

using std::stack;
using std::string;
using std::stringstream;

// @include
int Eval(const string& s) {
  stack<int> eval_stack;
  stringstream ss(s);
  string symbol;

  while (getline(ss, symbol, ',')) {
    if (symbol == "+" || symbol == "-" || symbol == "*" || symbol == "/") {
      int y = eval_stack.top();
      eval_stack.pop();
      int x = eval_stack.top();
      eval_stack.pop();
      switch (symbol.front()) {
        case '+':
          eval_stack.emplace(x + y);
          break;
        case '-':
          eval_stack.emplace(x - y);
          break;
        case '*':
          eval_stack.emplace(x * y);
          break;
        case '/':
          eval_stack.emplace(x / y);
          break;
      }
    } else {  // symbol is a number.
      eval_stack.emplace(stoi(symbol));
    }
  }
  return eval_stack.top();
}
// @exclude

int main(int argc, char* argv[]) {
  assert(0 == Eval("2,-10,/"));
  assert(-5 == Eval("-10,2,/"));
  assert(5 == Eval("-10,-2,/"));
  assert(-5 == Eval("5,10,-"));
  assert(6 == Eval("-10,-16,-"));
  assert(12 == Eval("10,2,+"));
  assert(15 == Eval("1,2,+,3,4,*,+"));
  assert(42 == Eval("1,2,3,4,5,+,*,+,+,3,4,*,+"));
  return 0;
}
