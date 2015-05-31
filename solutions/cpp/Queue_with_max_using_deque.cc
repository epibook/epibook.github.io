// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include "./Queue_with_max_using_deque.h"

#include <cassert>
#include <iostream>

using std::cout;
using std::endl;
using std::exception;

// Just for testing.
int main(int argc, char* argv[]) {
  QueueWithMax<int> Q;
  Q.Enqueue(1);
  Q.Enqueue(2);
  assert(2 == Q.Max());
  assert(1 == Q.Dequeue());  // 1
  assert(2 == Q.Max());
  assert(2 == Q.Dequeue());  // 2
  Q.Enqueue(3);
  assert(3 == Q.Max());
  assert(3 == Q.Dequeue());  // 3
  try {
    Q.Max();
  } catch (const exception& e) {
    cout << e.what() << endl;  // throw
  }
  try {
    Q.Dequeue();
  } catch (const exception& e) {
    cout << e.what() << endl;  // throw
  }
  return 0;
}
