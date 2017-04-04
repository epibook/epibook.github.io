// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <cmath>
#include <iostream>
#include <limits>
#include <stdexcept>

using std::cout;
using std::endl;
using std::exception;
using std::length_error;
using std::numeric_limits;

// @include
class Queue {
 public:
  void enqueue(unsigned x) {
    if (size_ >= max_size_) {
      throw length_error("queue overflow");
    }
    val_ = val_ * 10 + x;
    ++size_;
  }

  unsigned dequeue() {
    if (!size_) {
      throw length_error("empty queue");
    }
    unsigned ret = val_ / pow(10.0, --size_);
    val_ -= pow(10.0, size_) * ret;
    return ret;
  }

 private:
  unsigned val_ = 0;
  size_t size_ = 0, max_size_ = floor(log10(numeric_limits<unsigned>::max()));
};
// @exclude

int main(int argc, char* argv[]) {
  Queue q;
  q.enqueue(0);
  q.enqueue(5);
  q.enqueue(0);
  q.enqueue(2);
  assert(0 == q.dequeue());
  assert(5 == q.dequeue());
  q.enqueue(3);
  assert(0 == q.dequeue());
  assert(2 == q.dequeue());
  assert(3 == q.dequeue());
  q.enqueue(0);
  q.enqueue(0);
  assert(0 == q.dequeue());
  assert(0 == q.dequeue());
  // Empty queue, it should throw.
  try {
    q.dequeue();
  } catch (const exception& e) {
    cout << e.what() << endl;
  }
  q.enqueue(0);
  q.enqueue(0);
  q.enqueue(0);
  q.enqueue(0);
  q.enqueue(5);
  q.enqueue(0);
  q.enqueue(2);
  q.enqueue(5);
  q.enqueue(0);
  // Queue overflow, it should throw.
  try {
    q.enqueue(2);
  } catch (const exception& e) {
    cout << e.what() << endl;
  }
  return 0;
}
