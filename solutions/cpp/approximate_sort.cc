// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <cassert>
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

static vector<int> result;

// @include
void SortApproximatelySortedData(istringstream* sequence, int k) {
  // @exclude
  result = {};
  // @include
  priority_queue<int, vector<int>, greater<>> min_heap;
  // Adds the first k elements into min_heap. Stop if there are fewer than k
  // elements.
  int x;
  // clang-format off
  for (int i = 0; i < k && *sequence >> x; ++i) {
    // clang-format on
    min_heap.push(x);
  }

  // For every new element, add it to min_heap and extract the smallest.
  while (*sequence >> x) {
    min_heap.push(x);
    cout << min_heap.top() << endl;
    // @exclude
    result.push_back(min_heap.top());
    // @include
    min_heap.pop();
  }

  // sequence is exhausted, iteratively extracts the remaining elements.
  while (!min_heap.empty()) {
    // @exclude
    result.push_back(min_heap.top());
    // @include
    cout << min_heap.top() << endl;
    min_heap.pop();
  }
}
// @exclude

// It should sort to 1, 2, 3, 4, 5, 6, 7, 8, 9.
void SimpleTest() {
  vector<int> A = {2, 1, 5, 4, 3, 9, 8, 7, 6};
  stringstream ss;
  for (int a : A) {
    ss << a << ' ';
  }
  istringstream sequence(ss.str());
  SortApproximatelySortedData(&sequence, 3);
  vector<int> golden_result = {1, 2, 3, 4, 5, 6, 7, 8, 9};
  assert(equal(result.begin(), result.end(), golden_result.begin(),
               golden_result.end()));
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
  SortApproximatelySortedData(&sequence, n - 1);
  sort(A.begin(), A.end());
  assert(equal(result.begin(), result.end(), A.begin(), A.end()));
  return 0;
}
