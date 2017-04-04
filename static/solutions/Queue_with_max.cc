// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <limits>
#include <stdexcept>

#include "./Stack_with_max.h"

using std::cout;
using std::endl;
using std::exception;
using std::length_error;
using std::max;
using std::numeric_limits;

// @include
class QueueWithMax {
 public:
  void Enqueue(int x) { enqueue_.Push(x); }

  int Dequeue() {
    if (dequeue_.Empty()) {
      while (!enqueue_.Empty()) {
        dequeue_.Push(enqueue_.Pop());
      }
    }
    if (!dequeue_.Empty()) {
      return dequeue_.Pop();
    }
    throw length_error("empty queue");
  }

  int Max() const {
    if (!enqueue_.Empty()) {
      return dequeue_.Empty() ? enqueue_.Max()
                              : max(enqueue_.Max(), dequeue_.Max());
    }
    if (!dequeue_.Empty()) {
      return dequeue_.Max();
    }
    throw length_error("empty queue");
  }

 private:
  Stack enqueue_, dequeue_;
};
// @exclude

void SimpleTest() {
  QueueWithMax Q;
  Q.Enqueue(11);
  Q.Enqueue(2);
  assert(11 == Q.Max());
  assert(11 == Q.Dequeue());
  assert(2 == Q.Max());
  assert(2 == Q.Dequeue());
  Q.Enqueue(3);
  assert(3 == Q.Max());
  assert(3 == Q.Dequeue());
  Q.Enqueue(numeric_limits<int>::max() - 1);
  Q.Enqueue(numeric_limits<int>::max());
  Q.Enqueue(-2);
  Q.Enqueue(-1);
  Q.Enqueue(-1);
  Q.Enqueue(numeric_limits<int>::min());
  assert(numeric_limits<int>::max() == Q.Max());
  assert(numeric_limits<int>::max() - 1 == Q.Dequeue());
  assert(numeric_limits<int>::max() == Q.Max());
  assert(numeric_limits<int>::max() == Q.Dequeue());
  assert(-1 == Q.Max());
  assert(-2 == Q.Dequeue());
  assert(-1 == Q.Max());
  assert(-1 == Q.Dequeue());
  assert(-1 == Q.Dequeue());
  assert(numeric_limits<int>::min() == Q.Max());
  assert(numeric_limits<int>::min() == Q.Dequeue());
  try {
    cout << "Q is empty, Max() call should except = " << Q.Max();
    assert(false);
  } catch (const exception& e) {
    cout << e.what() << endl;
  }
}

int main(int argc, char* argv[]) {
  SimpleTest();
  QueueWithMax Q;
  Q.Enqueue(1);
  Q.Enqueue(2);
  assert(2 == Q.Max());
  assert(1 == Q.Dequeue());
  assert(2 == Q.Max());
  assert(2 == Q.Dequeue());
  Q.Enqueue(3);
  assert(3 == Q.Max());
  assert(3 == Q.Dequeue());
  try {
    Q.Max();
  } catch (const exception& e) {
    cout << e.what() << endl;
  }
  try {
    Q.Dequeue();
  } catch (const exception& e) {
    cout << e.what() << endl;
  }
  return 0;
}
