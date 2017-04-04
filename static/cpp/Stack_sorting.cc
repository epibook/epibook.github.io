// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <limits>
#include <random>
#include <stack>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::numeric_limits;
using std::random_device;
using std::stack;
using std::uniform_int_distribution;
using std::vector;

void Insert(int, stack<int>*);

// @include
void Sort(stack<int>* S) {
  if (!S->empty()) {
    int val = S->top();
    S->pop();
    Sort(S);
    Insert(val, S);
  }
}

void Insert(int val, stack<int>* S) {
  if (S->empty() || S->top() <= val) {
    S->push(val);
  } else {
    int f = S->top();
    S->pop();
    Insert(val, S);
    S->push(f);
  }
}
// @exclude

void SimpleTest() {
  stack<int> S;
  S.push(1);
  Sort(&S);
  assert(S.top() == 1);
  S.push(0);
  Sort(&S);
  assert(S.top() == 1);
  S.pop();
  assert(S.top() == 0);
  S.pop();
  assert(S.empty());
  S.push(-1);
  S.push(1);
  S.push(0);
  Sort(&S);
  assert(S.top() == 1);
  S.pop();
  assert(S.top() == 0);
  S.pop();
  assert(S.top() == -1);
  S.pop();
  assert(S.empty());
}

int main(int argc, char* argv[]) {
  SimpleTest();
  default_random_engine gen((random_device())());
  for (int times = 0; times < 1000; ++times) {
    int n;
    if (argc == 2) {
      n = atoi(argv[1]);
    } else {
      uniform_int_distribution<int> dis(1, 10000);
      n = dis(gen);
    }
    stack<int> S;
    uniform_int_distribution<int> dis(0, 999999);
    for (int i = 0; i < n; ++i) {
      S.push(dis(gen));
    }
    Sort(&S);
    int pre = numeric_limits<int>::max();
    while (!S.empty()) {
      assert(pre >= S.top());
      cout << S.top() << endl;
      pre = S.top();
      S.pop();
    }
  }
  return 0;
}
