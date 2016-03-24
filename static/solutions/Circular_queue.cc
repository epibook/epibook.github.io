// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <stdexcept>
#include <vector>

using std::cout;
using std::endl;
using std::exception;
using std::length_error;
using std::vector;

// @include
class Queue {
 public:
  explicit Queue(size_t capacity) : entries_(capacity) {}

  void Enqueue(int x) {
    if (num_queue_elements == entries_.size()) {  // Needs to resize.
      // Makes the queue elements appear consecutively.
      rotate(entries_.begin(), entries_.begin() + head_, entries_.end());
      head_ = 0, tail_ = num_queue_elements;  // Resets head and tail.
      entries_.resize(entries_.size() * kScaleFactor);
    }

    entries_[tail_] = x;
    tail_ = (tail_ + 1) % entries_.size(), ++num_queue_elements;
  }

  int Dequeue() {
    if (!num_queue_elements) {
      throw length_error("empty queue");
    }
    --num_queue_elements;
    int ret = entries_[head_];
    head_ = (head_ + 1) % entries_.size();
    return ret;
  }

  size_t size() const { return num_queue_elements; }

 private:
  const int kScaleFactor = 2;
  size_t head_ = 0, tail_ = 0, num_queue_elements = 0;
  vector<int> entries_;
};
// @exclude

void Test() {
  Queue q(8);
  q.Enqueue(1);
  q.Enqueue(2);
  q.Enqueue(3);
  q.Enqueue(4);
  q.Enqueue(5);
  q.Enqueue(6);
  q.Enqueue(7);
  q.Enqueue(8);
  // Now head = 0 and tail = 0

  assert(1 == q.Dequeue());
  assert(2 == q.Dequeue());
  assert(3 == q.Dequeue());
  // Now head = 3 and tail = 0

  q.Enqueue(11);
  q.Enqueue(12);
  q.Enqueue(13);
  // Ok till here. Now head = 3 and tail = 3

  q.Enqueue(14);  // now the vector (data) is resized; but the head and tail.
  // (or elements) does not change accordingly.
  q.Enqueue(15);
  q.Enqueue(16);
  q.Enqueue(17);
  q.Enqueue(18);
  // The elements starting from head=3 are over-written!

  assert(4 == q.Dequeue());
  assert(5 == q.Dequeue());
  assert(6 == q.Dequeue());
  assert(7 == q.Dequeue());
  assert(8 == q.Dequeue());
  assert(11 == q.Dequeue());
  assert(12 == q.Dequeue());
}

int main(int argc, char* argv[]) {
  Test();
  Queue q(8);
  q.Enqueue(1);
  q.Enqueue(2);
  q.Enqueue(3);
  assert(1 == q.Dequeue());
  q.Enqueue(4);
  assert(2 == q.Dequeue());
  assert(3 == q.Dequeue());
  assert(4 == q.Dequeue());
  try {
    q.Dequeue();
  } catch (const exception& e) {
    cout << e.what() << endl;
  }
  // test resize().
  q.Enqueue(4);
  q.Enqueue(4);
  q.Enqueue(4);
  q.Enqueue(4);
  q.Enqueue(4);
  q.Enqueue(4);
  q.Enqueue(4);
  q.Enqueue(4);
  q.Enqueue(4);
  assert(q.size() == 9);
  return 0;
}
