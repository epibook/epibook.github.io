// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <vector>

using std::cout;
using std::endl;
using std::vector;

void CompareKthLargestHeapHelper(const vector<int>& max_heap, int k, int x,
                                 int idx, int* larger_x, int* equal_x);

// @include
typedef enum { SMALLER, EQUAL, LARGER } Ordering;

Ordering CompareKthLargestHeap(const vector<int>& max_heap, int k, int x) {
  int larger_x = 0, equal_x = 0;
  CompareKthLargestHeapHelper(max_heap, k, x, 0, &larger_x, &equal_x);
  return larger_x >= k ? LARGER : (larger_x + equal_x >= k ? EQUAL : SMALLER);
}

void CompareKthLargestHeapHelper(const vector<int>& max_heap, int k, int x,
                                 int idx, int* larger_x, int* equal_x) {
  // Check early termination.  Note that we cannot early terminate for
  // equal_x >= k because larger_x (which is currently smaller than k)
  // may still become >= k.
  if (*larger_x >= k || idx >= max_heap.size() || max_heap[idx] < x) {
    return;
  } else if (max_heap[idx] == x) {
    if (++*equal_x >= k) {
      return;
    }
  } else {  // max_heap[idx] > x.
    ++*larger_x;
  }
  CompareKthLargestHeapHelper(max_heap, k, x, 2 * idx + 1, larger_x, equal_x);
  CompareKthLargestHeapHelper(max_heap, k, x, 2 * idx + 2, larger_x, equal_x);
}
// @exclude

void SmallTest() {
  vector<int> max_heap = {10, 2, 9, 2, 2, 8, 8, 2, 2, 2, 2, 7, 7, 7, 7};
  assert(LARGER == CompareKthLargestHeap(max_heap, 3, 2));  // kth is larger.
  assert(LARGER == CompareKthLargestHeap(max_heap, 6, 2));  // kth is larger.
}

int main(int argc, char* argv[]) {
  SmallTest();
  //      5
  //    4  5
  //  4  4 4 3
  // 4
  vector<int> max_heap = {5, 4, 5, 4, 4, 4, 3, 4};
  int k, x;
  if (argc == 3) {
    k = atoi(argv[1]), x = atoi(argv[2]);
    Ordering res = CompareKthLargestHeap(max_heap, k, x);
    cout << (res == SMALLER ? "smaller" : (res == EQUAL ? "equal" : "larger"))
         << endl;
  } else {
    assert(SMALLER ==
           CompareKthLargestHeap(max_heap, 1, 6));  // expect smaller
    assert(EQUAL == CompareKthLargestHeap(max_heap, 1, 5));  // expect equal
    assert(EQUAL == CompareKthLargestHeap(max_heap, 6, 4));  // expect equal
    assert(EQUAL == CompareKthLargestHeap(max_heap, 3, 4));  // expect equal
    assert(SMALLER ==
           CompareKthLargestHeap(max_heap, 8, 4));  // expect smaller
    assert(LARGER == CompareKthLargestHeap(max_heap, 2, 4));  // expect larger
    assert(LARGER == CompareKthLargestHeap(max_heap, 2, 3));  // expect larger
  }
  return 0;
}
