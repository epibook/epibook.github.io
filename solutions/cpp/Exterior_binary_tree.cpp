// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <memory>
#include <vector>

#include "./Binary_tree_prototype.h"

using std::cout;
using std::endl;
using std::unique_ptr;
using std::vector;

void LeftBoundaryBinaryTree(const unique_ptr<BinaryTreeNode<int>>& T,
                            bool is_boundary);
void RightBoundaryBinaryTree(const unique_ptr<BinaryTreeNode<int>>& T,
                             bool is_boundary);

vector<int> result;

// @include
void ExteriorBinaryTree(const unique_ptr<BinaryTreeNode<int>>& root) {
  if (root) {
    cout << root->data << ' ';
    // @exclude
    result.emplace_back(root->data);
    // @include
    LeftBoundaryBinaryTree(root->left, true);
    RightBoundaryBinaryTree(root->right, true);
  }
}

void LeftBoundaryBinaryTree(const unique_ptr<BinaryTreeNode<int>>& T,
                            bool is_boundary) {
  if (T) {
    if (is_boundary || (!T->left && !T->right)) {
      cout << T->data << ' ';
      // @exclude
      result.emplace_back(T->data);
      // @include
    }
    LeftBoundaryBinaryTree(T->left, is_boundary);
    LeftBoundaryBinaryTree(T->right, is_boundary && !T->left);
  }
}

void RightBoundaryBinaryTree(const unique_ptr<BinaryTreeNode<int>>& T,
                             bool is_boundary) {
  if (T) {
    RightBoundaryBinaryTree(T->left, is_boundary && !T->right);
    RightBoundaryBinaryTree(T->right, is_boundary);
    if (is_boundary || (!T->left && !T->right)) {
      cout << T->data << ' ';
      // @exclude
      result.emplace_back(T->data);
      // @include
    }
  }
}
// @exclude

int main(int argc, char* argv[]) {
  //      3
  //    2   5
  //  1  0 4 6
  //   -1 -2
  unique_ptr<BinaryTreeNode<int>> root = unique_ptr<BinaryTreeNode<int>>(
      new BinaryTreeNode<int>{3, nullptr, nullptr});
  root->left = unique_ptr<BinaryTreeNode<int>>(
      new BinaryTreeNode<int>{2, nullptr, nullptr});
  root->left->right = unique_ptr<BinaryTreeNode<int>>(
      new BinaryTreeNode<int>{0, nullptr, nullptr});
  root->left->right->left = unique_ptr<BinaryTreeNode<int>>(
      new BinaryTreeNode<int>{-1, nullptr, nullptr});
  root->left->right->right = unique_ptr<BinaryTreeNode<int>>(
      new BinaryTreeNode<int>{-2, nullptr, nullptr});
  root->left->left = unique_ptr<BinaryTreeNode<int>>(
      new BinaryTreeNode<int>{1, nullptr, nullptr});
  root->right = unique_ptr<BinaryTreeNode<int>>(
      new BinaryTreeNode<int>{5, nullptr, nullptr});
  root->right->left = unique_ptr<BinaryTreeNode<int>>(
      new BinaryTreeNode<int>{4, nullptr, nullptr});
  root->right->right = unique_ptr<BinaryTreeNode<int>>(
      new BinaryTreeNode<int>{6, nullptr, nullptr});
  // should output 3 2 1 -1 -2 4 6 5
  vector<int> golden_res = {3, 2, 1, -1, -2, 4, 6, 5};
  ExteriorBinaryTree(root);
  assert(result.size() == golden_res.size());
  assert(equal(result.begin(), result.end(), golden_res.begin()));
  return 0;
}
