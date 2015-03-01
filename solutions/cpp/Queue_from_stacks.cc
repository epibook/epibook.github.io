// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <stack>
#include <stdexcept>

using std::cout;
using std::endl;
using std::exception;
using std::length_error;
using std::stack;

// @include
class Queue {
 public:
  void Enqueue(int x) { A_.emplace(x); }

  int Dequeue() {
    if (B_.empty()) {
      // Transfers the elements in A_ to B_.
      while (!A_.empty()) {
        B_.emplace(A_.top());
        A_.pop();
      }
    }

    if (B_.empty()) {  // B_ is still empty!
      throw length_error("empty queue");
    }
    int ret = B_.top();
    B_.pop();
    return ret;
  }

 private:
  stack<int> A_, B_;
};
// @exclude

int main(int argc, char* argv[]) {
  Queue Q;
  Q.Enqueue(1);
  Q.Enqueue(2);
  assert(1 == Q.Dequeue());  // 1
  assert(2 == Q.Dequeue());  // 2
  Q.Enqueue(3);
  assert(3 == Q.Dequeue());  // 3
  try {
    Q.Dequeue();
  }
  catch (const exception& e) {
    cout << e.what() << endl;  // throw
  }
  return 0;
}
