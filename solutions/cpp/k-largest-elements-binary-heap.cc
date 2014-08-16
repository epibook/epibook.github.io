// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <queue>
#include <utility>
#include <vector>

using std::cout;
using std::endl;
using std::pair;
using std::priority_queue;
using std::vector;

// @include
struct Compare {
  bool operator()(const pair<size_t, int>& a, const pair<size_t, int>& b) {
    return a.second < b.second;
  }
};

vector<int> KLargestInBinaryHeap(const vector<int>& A, int k) {
  if (k <= 0) {
    return {};
  }

  // Stores the (index, value)-pair in this max-heap. Ordered by value.
  priority_queue<pair<size_t, int>, vector<pair<size_t, int>>, Compare>
      max_heap;
  max_heap.emplace(0, A[0]);  // The largest element stores at index 0.
  vector<int> result;
  for (int i = 0; i < k; ++i) {
    size_t idx = max_heap.top().first;
    result.emplace_back(max_heap.top().second);
    max_heap.pop();

    if ((idx * 2) + 1 < A.size()) {
      max_heap.emplace((idx * 2) + 1, A[(idx * 2) + 1]);
    }
    if ((idx * 2) + 2 < A.size()) {
      max_heap.emplace((idx * 2) + 2, A[(idx * 2) + 2]);
    }
  }
  return result;
}
// @exclude

int main(int argc, char* argv[]) {
  vector<int> max_heap = {10, 2, 9, 2, 2, 8, 8, 2, 2, 2, 2, 7, 7, 7, 7};
  auto result = KLargestInBinaryHeap(max_heap, 3);
  vector<int> expected_result = {10, 9, 8};
  assert(result.size() == 3 &&
         equal(result.begin(), result.end(), expected_result.begin()));
  result = KLargestInBinaryHeap(max_heap, 5);
  expected_result = {10, 9, 8, 8, 7};
  assert(result.size() == 5 &&
         equal(result.begin(), result.end(), expected_result.begin()));

  max_heap = {97, 84, 93, 83, 81, 90, 79, 83, 55, 42, 21, 73};
  result = KLargestInBinaryHeap(max_heap, 3);
  expected_result = {97, 93, 90};
  assert(result.size() == 3 &&
         equal(result.begin(), result.end(), expected_result.begin()));
  return 0;
}
