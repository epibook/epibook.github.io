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

// @include
class Stack {
 public:
  bool Empty() const { return element_.empty(); }

  int Max() const {
    if (Empty()) {
      throw length_error("Max(): empty stack");
    }
    return cached_max_with_count_.top().max;
  }

  int Pop() {
    if (Empty()) {
      throw length_error("Pop(): empty stack");
    }
    int pop_element = element_.top();
    element_.pop();
    const int current_max = cached_max_with_count_.top().max;
    if (pop_element == current_max) {
      int& max_frequency = cached_max_with_count_.top().count;
      --max_frequency;
      if (max_frequency == 0) {
        cached_max_with_count_.pop();
      }
    }
    return pop_element;
  }

  void Push(int x) {
    element_.emplace(x);
    if (cached_max_with_count_.empty()) {
      cached_max_with_count_.emplace(MaxWithCount{x, 1});
    } else {
      const int current_max = cached_max_with_count_.top().max;
      if (x == current_max) {
        int& max_frequency = cached_max_with_count_.top().count;
        ++max_frequency;
      } else if (x > current_max) {
        cached_max_with_count_.emplace(MaxWithCount{x, 1});
      }
    }
  }

 private:
  stack<int> element_;

  struct MaxWithCount {
    int max, count;
  };
  stack<MaxWithCount> cached_max_with_count_;
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
  } catch (const exception& e) {
    cout << e.what() << endl;
  }
  return 0;
}
