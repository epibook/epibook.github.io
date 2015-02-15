// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <memory>
#include <random>
#include <vector>

#include "./BST_prototype.h"

using std::cout;
using std::default_random_engine;
using std::endl;
using std::random_device;
using std::uniform_int_distribution;
using std::unique_ptr;
using std::vector;

unique_ptr<BSTNode<int>> BuildMinHeightBSTFromSortedArrayHelper(
    const vector<int>&, int, int);

// @include
unique_ptr<BSTNode<int>> BuildMinHeightBSTFromSortedArray(
    const vector<int>& A) {
  return BuildMinHeightBSTFromSortedArrayHelper(A, 0, A.size());
}

// Build a min-height BST over the entries in A[start : end - 1].
unique_ptr<BSTNode<int>> BuildMinHeightBSTFromSortedArrayHelper(
    const vector<int>& A, int start, int end) {
  if (start >= end) {
    return nullptr;
  }
  int mid = start + ((end - start) / 2);
  return unique_ptr<BSTNode<int>>(new BSTNode<int>{A[mid],
      BuildMinHeightBSTFromSortedArrayHelper(A, start, mid),
      BuildMinHeightBSTFromSortedArrayHelper(A, mid + 1, end)});
}
// @exclude

template <typename T>
void TraversalCheck(const unique_ptr<BSTNode<T>>& tree, T* target) {
  if (tree) {
    TraversalCheck(tree->left, target);
    assert(*target == tree->data);
    ++*target;
    TraversalCheck(tree->right, target);
  }
}

int main(int argc, char* argv[]) {
  default_random_engine gen((random_device())());
  for (int times = 0; times < 1000; ++times) {
    vector<int> A;
    int n;
    if (argc == 2) {
      n = atoi(argv[1]);
    } else {
      uniform_int_distribution<int> dis(1, 1000);
      n = dis(gen);
    }
    for (int i = 0; i < n; ++i) {
      A.emplace_back(i);
    }
    unique_ptr<BSTNode<int>> tree = BuildMinHeightBSTFromSortedArray(A);
    int target = 0;
    TraversalCheck<int>(tree, &target);
  }
  return 0;
}
