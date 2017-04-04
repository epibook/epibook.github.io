// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#ifndef SOLUTIONS_BINARY_TREE_PROTOTYPE_H_
#define SOLUTIONS_BINARY_TREE_PROTOTYPE_H_

#include <memory>

using std::unique_ptr;

// @include
template <typename T>
struct BinaryTreeNode {
  T data;
  unique_ptr<BinaryTreeNode<T>> left, right;
};
// @exclude
#endif  // SOLUTIONS_BINARY_TREE_PROTOTYPE_H_
