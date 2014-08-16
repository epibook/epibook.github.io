// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#ifndef SOLUTIONS_QUEUE_WITH_MAX_USING_DEQUE_H_
#define SOLUTIONS_QUEUE_WITH_MAX_USING_DEQUE_H_

#include <algorithm>
#include <deque>
#include <queue>
#include <stdexcept>

using std::deque;
using std::length_error;
using std::queue;

// @include
template <typename T>
class Queue {
 public:
  void Enqueue(const T& x) {
    Q_.emplace(x);
    while (!D_.empty() && D_.back() < x) {
      D_.pop_back();
    }
    D_.emplace_back(x);
  }

  T Dequeue() {
    if (!Q_.empty()) {
      T ret = Q_.front();
      if (ret == D_.front()) {
        D_.pop_front();
      }
      Q_.pop();
      return ret;
    }
    throw length_error("empty queue");
  }

  const T& Max() const {
    if (!D_.empty()) {
      return D_.front();
    }
    throw length_error("empty queue");
  }
  // @exclude
  T& Head() { return Q_.front(); }

  const T& Head() const { return Q_.front(); }
  // @include

 private:
  queue<T> Q_;
  deque<T> D_;
};
// @exclude
#endif  // SOLUTIONS_QUEUE_WITH_MAX_USING_DEQUE_H_
