// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include "./Queue_with_max_alternative.h"

#include <cassert>
#include <iostream>
#include <limits>

using std::cout;
using std::endl;
using std::exception;
using std::numeric_limits;

void SimpleTest() {
  QueueWithMaxAlternative::QueueWithMax<int> Q;
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

// Just for testing.
int main(int argc, char* argv[]) {
  SimpleTest();
  QueueWithMaxAlternative::QueueWithMax<int> Q;
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
    assert(false);
  } catch (const exception& e) {
    cout << e.what() << endl;  // throw
  }
  try {
    Q.Dequeue();
    assert(true);
  } catch (const exception& e) {
    cout << e.what() << endl;  // throw
  }
  return 0;
}
