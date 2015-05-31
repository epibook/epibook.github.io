// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <functional>
#include <iostream>
#include <queue>
#include <random>
#include <sstream>
#include <string>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::greater;
using std::istringstream;
using std::priority_queue;
using std::random_device;
using std::string;
using std::stringstream;
using std::uniform_int_distribution;
using std::vector;

// @include
void SortApproximatelySortedArray(istringstream* sequence, int k) {
  priority_queue<int, vector<int>, greater<int>> min_heap;
  // Adds the first k elements into min_heap. Stop if there are fewer than k
  // elements.
  int x;
  // @exclude
  // clang-format off
  // @include
  for (int i = 0; i < k && *sequence >> x; ++i) {
    // @exclude
    // clang-format on
    // @include
    min_heap.push(x);
  }

  // For every new element, add it to min_heap and extract the smallest.
  while (*sequence >> x) {
    min_heap.push(x);
    cout << min_heap.top() << endl;
    min_heap.pop();
  }

  // sequence is exhausted, iteratively extracts the remaining elements.
  while (!min_heap.empty()) {
    cout << min_heap.top() << endl;
    min_heap.pop();
  }
}
// @exclude

// It should print 1, 2, 3, 4, 5, 6, 7, ,8, 9.
void SimpleTest() {
  vector<int> A = {2, 1, 5, 4, 3, 9, 8, 7, 6};
  stringstream ss;
  for (int a : A) {
    ss << a << ' ';
  }
  istringstream sequence(ss.str());
  SortApproximatelySortedArray(&sequence, 3);
}

int main(int argc, char* argv[]) {
  SimpleTest();
  default_random_engine gen((random_device())());
  int n;
  if (argc == 2) {
    n = atoi(argv[1]);
  } else {
    uniform_int_distribution<int> n_dis(1, 100000);
    n = n_dis(gen);
  }
  cout << "n = " << n << endl;
  vector<int> A;
  uniform_int_distribution<int> dis(1, 999999);
  for (int i = 0; i < n; ++i) {
    A.push_back(dis(gen));
  }
  stringstream ss;
  for (int a : A) {
    ss << a << ' ';
  }
  istringstream sequence(ss.str());
  SortApproximatelySortedArray(&sequence, n - 1);
  return 0;
}
