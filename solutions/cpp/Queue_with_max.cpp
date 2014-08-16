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

// @include
class Queue {
 public:
  void Enqueue(int x) { A_.Push(x); }

  int Dequeue() {
    if (B_.Empty()) {
      while (!A_.Empty()) {
        B_.Push(A_.Pop());
      }
    }
    if (!B_.Empty()) {
      return B_.Pop();
    }
    throw length_error("empty queue");
  }

  int Max() const {
    if (!A_.Empty()) {
      return B_.Empty() ? A_.Max() : std::max(A_.Max(), B_.Max());
    } else {  // A_.Empty() == true.
      if (!B_.Empty()) {
        return B_.Max();
      }
      throw length_error("empty queue");
    }
  }

 private:
  Stack A_, B_;
};
// @exclude

int main(int argc, char* argv[]) {
  Queue Q;
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
  }
  catch (const exception& e) {
    cout << e.what() << endl;  // throw
  }
  try {
    Q.Dequeue();
  }
  catch (const exception& e) {
    cout << e.what() << endl;  // throw
  }
  return 0;
}
