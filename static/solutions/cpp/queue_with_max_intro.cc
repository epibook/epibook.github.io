// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <limits>
#include <list>
#include <stdexcept>
#include <vector>

using std::cout;
using std::endl;
using std::exception;
using std::length_error;
using std::list;
using std::numeric_limits;
using std::vector;

// @include
class Queue {
 public:
  void Enqueue(int x) { data_.emplace_back(x); }

  int Dequeue() {
    if (data_.empty()) {
      throw length_error("empty queue");
    }
    const int val = data_.front();
    data_.pop_front();
    return val;
  }

  int Max() const {
    if (data_.empty()) {
      throw length_error("Cannot perform Max() on empty queue.");
    }
    return *max_element(data_.begin(), data_.end());
  }

 private:
  list<int> data_;
};
// @exclude

void SimpleTest() {
  Queue Q;
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
  Queue Q;
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
