// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#ifndef SOLUTIONS_BST_PARENT_PROTOTYPE_H_
#define SOLUTIONS_BST_PARENT_PROTOTYPE_H_

#include <iostream>
#include <memory>

using std::unique_ptr;

// @include
template <typename T>
struct BSTNode {
  T data;
  unique_ptr<BSTNode<T>> left, right;
  BSTNode<T>* parent;
};
// @exclude
#endif  // SOLUTIONS_BST_PARENT_PROTOTYPE_H_
