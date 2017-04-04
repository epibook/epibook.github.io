// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#ifndef SOLUTIONS_CPP_MERGE_SORTED_ARRAYS_H_
#define SOLUTIONS_CPP_MERGE_SORTED_ARRAYS_H_

#include <algorithm>
#include <iterator>
#include <queue>
#include <utility>
#include <vector>

using std::greater;
using std::next;
using std::pair;
using std::priority_queue;
using std::vector;

// @include
struct IteratorCurrentAndEnd {
  bool operator>(const IteratorCurrentAndEnd& that) const {
    return *current > *that.current;
  }

  vector<int>::const_iterator current;
  vector<int>::const_iterator end;
};

vector<int> MergeSortedArrays(const vector<vector<int>>& sorted_arrays) {
  priority_queue<IteratorCurrentAndEnd, vector<IteratorCurrentAndEnd>,
                 greater<>>
      min_heap;

  for (const vector<int>& sorted_array : sorted_arrays) {
    if (!sorted_array.empty()) {
      min_heap.emplace(
          IteratorCurrentAndEnd{sorted_array.cbegin(), sorted_array.cend()});
    }
  }

  vector<int> result;
  while (!min_heap.empty()) {
    auto smallest_array = min_heap.top();
    min_heap.pop();
    if (smallest_array.current != smallest_array.end) {
      result.emplace_back(*smallest_array.current);
      min_heap.emplace(IteratorCurrentAndEnd{next(smallest_array.current),
                                             smallest_array.end});
    }
  }
  return result;
}
// @exclude
#endif  // SOLUTIONS_CPP_MERGE_SORTED_ARRAYS_H_
