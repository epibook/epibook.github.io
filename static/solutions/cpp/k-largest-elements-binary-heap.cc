// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <functional>
#include <iostream>
#include <queue>
#include <utility>
#include <vector>

using std::cout;
using std::endl;
using std::function;
using std::pair;
using std::priority_queue;
using std::vector;

// @include
vector<int> KLargestInBinaryHeap(const vector<int>& A, int k) {
  if (k <= 0) {
    return {};
  }

  struct HeapEntry {
    int index, value;
  };
  priority_queue<HeapEntry, vector<HeapEntry>,
                 function<bool(HeapEntry, HeapEntry)>>
  candidate_max_heap([](const HeapEntry& a, const HeapEntry& b) {
    return a.value < b.value;
  });
  // The largest element in A is at index 0.
  candidate_max_heap.emplace(HeapEntry{0, A[0]});
  vector<int> result;
  for (int i = 0; i < k; ++i) {
    int candidate_idx = candidate_max_heap.top().index;
    result.emplace_back(candidate_max_heap.top().value);
    candidate_max_heap.pop();

    int left_child_idx = 2 * candidate_idx + 1;
    if (left_child_idx < A.size()) {
      candidate_max_heap.emplace(
          HeapEntry{left_child_idx, A[left_child_idx]});
    }
    int right_child_idx = 2 * candidate_idx + 2;
    if (right_child_idx < A.size()) {
      candidate_max_heap.emplace(
          HeapEntry{right_child_idx, A[right_child_idx]});
    }
  }
  return result;
}
// @exclude

int main(int argc, char* argv[]) {
  vector<int> max_heap = {10, 2, 9, 2, 2, 8, 8, 2, 2, 2, 2, 7, 7, 7, 7};
  auto result = KLargestInBinaryHeap(max_heap, 3);
  vector<int> expected_result = {10, 9, 8};
  assert(equal(result.begin(), result.end(), expected_result.begin(),
               expected_result.end()));
  result = KLargestInBinaryHeap(max_heap, 5);
  expected_result = {10, 9, 8, 8, 7};
  assert(equal(result.begin(), result.end(), expected_result.begin(),
               expected_result.end()));

  max_heap = {97, 84, 93, 83, 81, 90, 79, 83, 55, 42, 21, 73};
  result = KLargestInBinaryHeap(max_heap, 3);
  expected_result = {97, 93, 90};
  assert(equal(result.begin(), result.end(), expected_result.begin(),
               expected_result.end()));

  max_heap = {100, 1, 5, 0, 0};
  result = KLargestInBinaryHeap(max_heap, 1);
  expected_result = {100};
  assert(equal(result.begin(), result.end(), expected_result.begin(),
               expected_result.end()));
  return 0;
}
