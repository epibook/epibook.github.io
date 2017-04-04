// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <limits>
#include <stack>
#include <stdexcept>

using std::cout;
using std::endl;
using std::exception;
using std::length_error;
using std::numeric_limits;
using std::stack;

// @include
class Queue {
 public:
  void Enqueue(int x) { enqueue_.emplace(x); }

  int Dequeue() {
    if (dequeue_.empty()) {
      // Transfers the elements in enqueue_ to dequeue_.
      while (!enqueue_.empty()) {
        dequeue_.emplace(enqueue_.top());
        enqueue_.pop();
      }
    }

    if (dequeue_.empty()) {  // dequeue_ is still empty!
      throw length_error("empty queue");
    }
    int ret = dequeue_.top();
    dequeue_.pop();
    return ret;
  }

 private:
  stack<int> enqueue_, dequeue_;
};
// @exclude

int main(int argc, char* argv[]) {
  Queue Q;
  Q.Enqueue(1);
  Q.Enqueue(2);
  assert(1 == Q.Dequeue());
  assert(2 == Q.Dequeue());
  Q.Enqueue(3);
  assert(3 == Q.Dequeue());
  try {
    Q.Dequeue();
    assert(false);
  } catch (const exception& e) {
    cout << e.what() << endl;
  }
  Q.Enqueue(-1);
  Q.Enqueue(123);
  Q.Enqueue(numeric_limits<int>::max());
  Q.Enqueue(numeric_limits<int>::min());
  Q.Enqueue(0);
  assert(-1 == Q.Dequeue());
  Q.Enqueue(0);
  assert(123 == Q.Dequeue());
  assert(numeric_limits<int>::max() == Q.Dequeue());
  assert(numeric_limits<int>::min() == Q.Dequeue());
  assert(0 == Q.Dequeue());
  assert(0 == Q.Dequeue());
  try {
    Q.Dequeue();
    assert(false);
  } catch (const exception& e) {
    cout << e.what() << endl;
  }
  return 0;
}
