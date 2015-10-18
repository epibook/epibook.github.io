// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <limits>
#include <queue>
#include <stdexcept>
#include <vector>
#include <utility>

using std::cout;
using std::endl;
using std::exception;
using std::function;
using std::length_error;
using std::numeric_limits;
using std::pair;
using std::priority_queue;
using std::vector;

// @include
struct Compare {
  bool operator()(const pair<int, int>& lhs, const pair<int, int>& rhs) {
    return lhs.first < rhs.first;
  }
};

class Stack {
 public:
  void Push(int x) { max_heap_.emplace(timestamp_++, x); }

  int Pop() {
    if (max_heap_.empty()) {
      throw length_error("empty stack");
    }
    int val = max_heap_.top().second;
    max_heap_.pop();
    return val;
  }

  int Peek() const { return max_heap_.top().second; }

 private:
  int timestamp_ = 0;
  // Stores (timestamp, element)-pair. Pairs are ordered by timestamp.
  priority_queue<pair<int, int>, vector<pair<int, int>>, Compare> max_heap_;
};
// @exclude

class Queue {
 public:
  void Enqueue(int x) { max_heap_.emplace(order_--, x); }

  int Dequeue() {
    if (max_heap_.empty()) {
      throw length_error("empty queue");
    }
    int ret = max_heap_.top().second;
    max_heap_.pop();
    return ret;
  }

  int Head() const { return max_heap_.top().second; }

 private:
  int order_ = 0;
  // Uses a pair where first is the order_ and the second is the element.
  priority_queue<pair<int, int>, vector<pair<int, int>>, Compare> max_heap_;
};

int main(int argc, char* argv[]) {
  Stack s;
  s.Push(1);
  s.Push(2);
  s.Push(3);
  assert(s.Peek() == 3);
  s.Pop();
  assert(s.Peek() == 2);
  s.Pop();
  s.Push(4);
  assert(s.Peek() == 4);
  s.Pop();
  s.Pop();
  try {
    s.Pop();
    assert(false);
  } catch (const exception& e) {
    cout << "empty stack" << endl;
    cout << e.what() << endl;
  }

  s.Push(0);
  s.Push(-1);
  s.Push(numeric_limits<int>::max());
  assert(s.Peek() == numeric_limits<int>::max());
  s.Pop();
  assert(s.Peek() == -1);
  s.Pop();
  assert(s.Peek() == 0);
  s.Pop();
  try {
    s.Pop();
    assert(false);
  } catch (const exception& e) {
    cout << "empty stack" << endl;
    cout << e.what() << endl;
  }
  s.Push(0);
  assert(s.Peek() == 0);

  Queue q;
  q.Enqueue(1);
  q.Enqueue(2);
  assert(q.Head() == 1);
  q.Dequeue();
  assert(q.Head() == 2);
  q.Dequeue();
  try {
    q.Dequeue();
    assert(false);
  } catch (const exception& e) {
    cout << "empty queue" << endl;
    cout << e.what() << endl;
  }
  return 0;
}
