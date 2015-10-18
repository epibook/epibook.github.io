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
  void Enqueue(int x) { enq_.emplace(x); }

  int Dequeue() {
    if (deq_.empty()) {
      // Transfers the elements in enq_ to deq_.
      while (!enq_.empty()) {
        deq_.emplace(enq_.top());
        enq_.pop();
      }
    }

    if (deq_.empty()) {  // deq_ is still empty!
      throw length_error("empty queue");
    }
    int ret = deq_.top();
    deq_.pop();
    return ret;
  }

 private:
  stack<int> enq_, deq_;
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
