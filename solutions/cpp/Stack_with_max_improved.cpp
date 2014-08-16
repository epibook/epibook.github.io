// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <stack>
#include <stdexcept>
#include <string>
#include <utility>

using std::cout;
using std::endl;
using std::exception;
using std::length_error;
using std::stack;
using std::pair;

// @include
class Stack {
 public:
  bool Empty() const { return s_.empty(); }

  int Max() const {
    if (!Empty()) {
      return aux_.top().first;
    }
    throw length_error("empty stack");
  }

  int Pop() {
    if (Empty()) {
      throw length_error("empty stack");
    }
    int ret = s_.top();
    s_.pop();
    if (ret == aux_.top().first) {
      --aux_.top().second;
      if (aux_.top().second == 0) {
        aux_.pop();
      }
    }
    return ret;
  }

  void Push(int x) {
    s_.emplace(x);
    if (!aux_.empty()) {
      if (x == aux_.top().first) {
        ++aux_.top().second;
      } else if (x > aux_.top().first) {
        aux_.emplace(x, 1);
      }
    } else {
      aux_.emplace(x, 1);
    }
  }

 private:
  stack<int> s_;
  stack<pair<int, int>> aux_;
};
// @exclude

int main(int argc, char* argv[]) {
  Stack s;
  s.Push(1);
  s.Push(2);
  assert(s.Max() == 2);
  cout << s.Max() << endl;  // 2
  cout << s.Pop() << endl;  // 2
  assert(s.Max() == 1);
  cout << s.Max() << endl;  // 1
  s.Push(3);
  s.Push(2);
  assert(s.Max() == 3);
  cout << s.Max() << endl;  // 3
  s.Pop();
  assert(s.Max() == 3);
  cout << s.Max() << endl;  // 3
  s.Pop();
  assert(s.Max() == 1);
  cout << s.Max() << endl;  // 1
  s.Pop();
  try {
    s.Max();
    s.Pop();
    s.Pop();
    s.Pop();
    s.Pop();
  }
  catch (const exception& e) {
    cout << e.what() << endl;
  }
  return 0;
}
