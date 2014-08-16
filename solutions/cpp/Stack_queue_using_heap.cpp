// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <queue>
#include <stdexcept>
#include <vector>
#include <utility>

using std::cout;
using std::endl;
using std::exception;
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
  void Push(int x) { H.emplace(order_++, x); }

  int Pop() {
    int ret = H.top().second;
    H.pop();
    return ret;
  }

  const int& Peek() const { return H.top().second; }

 private:
  int order_ = 0;
  // Uses a pair where first is the order_ and the second is the element.
  priority_queue<pair<int, int>, vector<pair<int, int>>, Compare> H;
};

class Queue {
 public:
  void Enqueue(int x) { H.emplace(order_--, x); }

  int Dequeue() {
    int ret = H.top().second;
    H.pop();
    return ret;
  }

  const int& Head() const { return H.top().second; }

 private:
  int order_ = 0;
  // Uses a pair where first is the order_ and the second is the element.
  priority_queue<pair<int, int>, vector<pair<int, int>>, Compare> H;
};
// @exclude

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
  }
  catch (const exception& e) {
    cout << "empty stack" << endl;
    cout << e.what() << endl;
  }

  Queue q;
  q.Enqueue(1);
  q.Enqueue(2);
  assert(q.Head() == 1);
  q.Dequeue();
  assert(q.Head() == 2);
  q.Dequeue();
  try {
    q.Dequeue();
  }
  catch (const exception& e) {
    cout << "empty queue" << endl;
    cout << e.what() << endl;
  }
  return 0;
}
