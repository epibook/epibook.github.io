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

BSTNode<int>* BuildBSTFromSortedArrayHelper(const vector<int>& A,
                                            size_t start, size_t end);

// @include
BSTNode<int>* BuildBSTFromSortedArray(const vector<int>& A) {
  return BuildBSTFromSortedArrayHelper(A, 0, A.size());
}

// Build BST based on subarray A[start : end - 1].
BSTNode<int>* BuildBSTFromSortedArrayHelper(const vector<int>& A,
                                            size_t start, size_t end) {
  if (start >= end) {
    return nullptr;
  }
  size_t mid = start + ((end - start) / 2);
  return new BSTNode<int>{
      A[mid], unique_ptr<BSTNode<int>>(
                  BuildBSTFromSortedArrayHelper(A, start, mid)),
      unique_ptr<BSTNode<int>>(
          BuildBSTFromSortedArrayHelper(A, mid + 1, end))};
}
// @exclude

template <typename T>
void TraversalCheck(const unique_ptr<BSTNode<T>>& root, T* target) {
  if (root) {
    TraversalCheck(root->left, target);
    assert(*target == root->data);
    ++*target;
    TraversalCheck(root->right, target);
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
    unique_ptr<BSTNode<int>> root =
        unique_ptr<BSTNode<int>>(BuildBSTFromSortedArray(A));
    int target = 0;
    TraversalCheck<int>(root, &target);
  }
  return 0;
}
