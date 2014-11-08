// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <list>
#include <memory>

#include "./Binary_tree_prototype.h"

using std::cout;
using std::endl;
using std::list;
using std::unique_ptr;

list<const unique_ptr<BinaryTreeNode<int>>*> LeftBoundaryAndLeaves(
    const unique_ptr<BinaryTreeNode<int>>&, bool);
list<const unique_ptr<BinaryTreeNode<int>>*> RightBoundaryAndLeaves(
    const unique_ptr<BinaryTreeNode<int>>&, bool);
bool IsLeaf(const unique_ptr<BinaryTreeNode<int>>&);

// @include
list<const unique_ptr<BinaryTreeNode<int>>*> ExteriorBinaryTree(
    const unique_ptr<BinaryTreeNode<int>>& tree) {
  list<const unique_ptr<BinaryTreeNode<int>>*> exterior;
  if (tree != nullptr) {
    exterior.emplace_back(&tree);
    exterior.splice(exterior.end(), LeftBoundaryAndLeaves(tree->left, true));
    exterior.splice(exterior.end(),
                    RightBoundaryAndLeaves(tree->right, true));
  }
  return exterior;
}

// Computes the nodes from the root to the leftmost leaf followed by all the
// leaves in subtree_root.
list<const unique_ptr<BinaryTreeNode<int>>*> LeftBoundaryAndLeaves(
    const unique_ptr<BinaryTreeNode<int>>& subtree_root, bool is_boundary) {
  list<const unique_ptr<BinaryTreeNode<int>>*> result;
  if (subtree_root != nullptr) {
    if (is_boundary || IsLeaf(subtree_root)) {
      result.emplace_back(&subtree_root);
    }
    result.splice(result.end(),
                  LeftBoundaryAndLeaves(subtree_root->left, is_boundary));
    result.splice(
        result.end(),
        LeftBoundaryAndLeaves(subtree_root->right,
                              is_boundary && subtree_root->left == nullptr));
  }
  return result;
}

// Computes the leaves in left-to-right order followed by the rightmost leaf
// to the root path in subtree_root.
list<const unique_ptr<BinaryTreeNode<int>>*> RightBoundaryAndLeaves(
    const unique_ptr<BinaryTreeNode<int>>& subtree_root, bool is_boundary) {
  list<const unique_ptr<BinaryTreeNode<int>>*> result;
  if (subtree_root != nullptr) {
    result.splice(
        result.end(),
        RightBoundaryAndLeaves(subtree_root->left,
                               is_boundary &&
                                   subtree_root->right == nullptr));
    result.splice(result.end(),
                  RightBoundaryAndLeaves(subtree_root->right, is_boundary));
    if (is_boundary || IsLeaf(subtree_root)) {
      result.emplace_back(&subtree_root);
    }
  }
  return result;
}

bool IsLeaf(const unique_ptr<BinaryTreeNode<int>>& node) {
  return node->left == nullptr && node->right == nullptr;
}
// @exclude

int main(int argc, char* argv[]) {
  //        3
  //    2      5
  //  1  0    4 6
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
  list<int> golden_res = {3, 2, 1, -1, -2, 4, 6, 5};
  auto L = ExteriorBinaryTree(root);
  list<int> output;
  // should output 3 2 1 -1 -2 4 6 5
  for (const auto* l : L) {
    output.push_back((*l)->data);
    cout << (*l)->data << endl;
  }
  assert(output.size() == golden_res.size());
  assert(equal(output.begin(), output.end(), golden_res.begin()));
  return 0;
}
