// Copyright (c) 2016 Elements of Programming Interviews. All rights reserved.

#ifndef SOLUTIONS_CPP_RECONSTRUCT_PREORDER_WITH_NULL_H_
#define SOLUTIONS_CPP_RECONSTRUCT_PREORDER_WITH_NULL_H_

#include <memory>
#include <vector>

#include "./Binary_tree_prototype.h"

using std::make_unique;
using std::unique_ptr;
using std::vector;

unique_ptr<BinaryTreeNode<int>> ReconstructPreorderHelper(const vector<int*>&,
                                                          int*);

// @include
unique_ptr<BinaryTreeNode<int>> ReconstructPreorder(
    const vector<int*>& preorder) {
  int subtree_idx_pointer = 0;
  return ReconstructPreorderHelper(preorder, &subtree_idx_pointer);
}

// Reconstructs the subtree that is rooted at subtreeIdx.
unique_ptr<BinaryTreeNode<int>> ReconstructPreorderHelper(
    const vector<int*>& preorder, int* subtree_idx_pointer) {
  int& subtree_idx = *subtree_idx_pointer;
  int* subtree_key = preorder[subtree_idx];
  ++subtree_idx;
  if (subtree_key == nullptr) {
    return nullptr;
  }
  // Note that ReconstructPreorderHelper updates subtree_idx. So the order of
  // following two calls are critical.
  auto left_subtree =
      ReconstructPreorderHelper(preorder, subtree_idx_pointer);
  auto right_subtree =
      ReconstructPreorderHelper(preorder, subtree_idx_pointer);
  return make_unique<BinaryTreeNode<int>>(BinaryTreeNode<int>{
      *subtree_key, move(left_subtree), move(right_subtree)});
}
// @exclude

#endif  // SOLUTIONS_CPP_RECONSTRUCT_PREORDER_WITH_NULL_H_
