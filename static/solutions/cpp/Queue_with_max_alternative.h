// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#ifndef SOLUTIONS_QUEUE_WITH_MAX_ALTERNATIVE_H_
#define SOLUTIONS_QUEUE_WITH_MAX_ALTERNATIVE_H_

#include <algorithm>
#include <deque>
#include <queue>
#include <stdexcept>

using std::deque;
using std::length_error;
using std::queue;

namespace QueueWithMaxAlternative {

// @include
template <typename T>
class QueueWithMax {
 public:
  void Enqueue(const T& x) {
    entries_.emplace(x);
    // Eliminate dominated elements in candidates_for_max_.
    while (!candidates_for_max_.empty()) {
      if (candidates_for_max_.back() >= x) {
        break;
      }
      candidates_for_max_.pop_back();
    }
    candidates_for_max_.emplace_back(x);
  }

  T Dequeue() {
    if (!entries_.empty()) {
      T result = entries_.front();
      if (result == candidates_for_max_.front()) {
        candidates_for_max_.pop_front();
      }
      entries_.pop();
      return result;
    }
    throw length_error("empty queue");
  }

  const T& Max() const {
    if (!candidates_for_max_.empty()) {
      return candidates_for_max_.front();
    }
    throw length_error("empty queue");
  }
  // @exclude
  T& Head() { return entries_.front(); }

  const T& Head() const { return entries_.front(); }
  // @include

 private:
  queue<T> entries_;
  deque<T> candidates_for_max_;
};
// @exclude
}

#endif  // SOLUTIONS_QUEUE_WITH_MAX_ALTERNATIVE_H_
