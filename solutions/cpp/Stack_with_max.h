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
  bool Empty() const { return element_with_cached_max_.empty(); }

  int Max() const {
    if (!Empty()) {
      return element_with_cached_max_.top().second;
    }
    throw length_error("Max(): empty stack");
  }

  int Pop() {
    if (Empty()) {
      throw length_error("Pop(): empty stack");
    }
    int pop_element = element_with_cached_max_.top().first;
    element_with_cached_max_.pop();
    return pop_element;
  }

  void Push(int x) {
    element_with_cached_max_.emplace(
        x, std::max(x, Empty() ? x : element_with_cached_max_.top().second));
  }

 private:
  // Stores (element, cached maximum) pair.
  stack<pair<int, int>> element_with_cached_max_;
};
// @exclude
#endif  // SOLUTIONS_STACK_WITH_MAX_H_
