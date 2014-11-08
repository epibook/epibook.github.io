// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#ifndef SOLUTIONS_MERGE_SORTED_ARRAYS_H_
#define SOLUTIONS_MERGE_SORTED_ARRAYS_H_

#include <algorithm>
#include <queue>
#include <utility>
#include <vector>

using std::pair;
using std::priority_queue;
using std::vector;

// @include
struct Compare {
  bool operator()(const pair<int, int>& lhs, const pair<int, int>& rhs) {
    return lhs.first > rhs.first;
  }
};

vector<int> MergeSortedArrays(const vector<vector<int>>& sorted_arrays) {
  priority_queue<pair<int, int>, vector<pair<int, int>>, Compare> min_heap;
  // For each array in sorted_arrays, keeps index of next unprocessed element.
  vector<int> heads(sorted_arrays.size(), 0);

  // Puts each sorted_arrays' first element in min_heap.
  for (int i = 0; i < sorted_arrays.size(); ++i) {
    if (!sorted_arrays[i].empty()) {
      min_heap.emplace(sorted_arrays[i][0], i);
      heads[i] = 1;
    }
  }

  vector<int> result;
  while (!min_heap.empty()) {
    int smallest_entry = min_heap.top().first;
    auto& smallest_array = sorted_arrays[min_heap.top().second];
    auto& smallest_array_head = heads[min_heap.top().second];
    result.emplace_back(smallest_entry);
    if (smallest_array_head < smallest_array.size()) {
      // Add the next entry of smallest_array into min_heap.
      min_heap.emplace(smallest_array[smallest_array_head],
                       min_heap.top().second);
      ++smallest_array_head;
    }
    min_heap.pop();
  }
  return result;
}
// @exclude
#endif  // SOLUTIONS_MERGE_SORTED_ARRAYS_H_
