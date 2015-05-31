// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <stdexcept>

#include "./Stack_with_max.h"

using std::cout;
using std::endl;
using std::exception;
using std::length_error;
using std::max;

// @include
class QueueWithMax {
 public:
  void Enqueue(int x) { enqueue_stack_.Push(x); }

  int Dequeue() {
    if (dequeue_stack_.Empty()) {
      while (!enqueue_stack_.Empty()) {
        dequeue_stack_.Push(enqueue_stack_.Pop());
      }
    }
    if (!dequeue_stack_.Empty()) {
      return dequeue_stack_.Pop();
    }
    throw length_error("empty queue");
  }

  int Max() const {
    if (!enqueue_stack_.Empty()) {
      return dequeue_stack_.Empty()
                 ? enqueue_stack_.Max()
                 : max(enqueue_stack_.Max(), dequeue_stack_.Max());
    } else {  // enqueue_stack_.Empty() == true.
      if (!dequeue_stack_.Empty()) {
        return dequeue_stack_.Max();
      }
      throw length_error("empty queue");
    }
  }

 private:
  Stack enqueue_stack_, dequeue_stack_;
};
// @exclude

int main(int argc, char* argv[]) {
  QueueWithMax Q;
  Q.Enqueue(1);
  Q.Enqueue(2);
  assert(2 == Q.Max());
  assert(1 == Q.Dequeue());  // 1
  assert(2 == Q.Max());
  assert(2 == Q.Dequeue());  // 2
  Q.Enqueue(3);
  assert(3 == Q.Max());
  assert(3 == Q.Dequeue());  // 3
  try {
    Q.Max();
  } catch (const exception& e) {
    cout << e.what() << endl;  // throw
  }
  try {
    Q.Dequeue();
  } catch (const exception& e) {
    cout << e.what() << endl;  // throw
  }
  return 0;
}
