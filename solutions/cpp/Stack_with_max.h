// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#ifndef SOLUTIONS_STACK_WITH_MAX_H_
#define SOLUTIONS_STACK_WITH_MAX_H_

#include <algorithm>
#include <stack>
#include <stdexcept>
#include <utility>

using std::length_error;
using std::pair;
using std::stack;

// @include
class Stack {
 public:
  bool Empty() const { return s_.empty(); }

  int Max() const {
    if (!Empty()) {
      return s_.top().second;
    }
    throw length_error("empty stack");
  }

  int Pop() {
    if (Empty()) {
      throw length_error("empty stack");
    }
    int ret = s_.top().first;
    s_.pop();
    return ret;
  }

  void Push(int x) {
    s_.emplace(x, std::max(x, Empty() ? x : s_.top().second));
  }

 private:
  stack<pair<int, int>> s_;
};
// @exclude
#endif  // SOLUTIONS_STACK_WITH_MAX_H_
