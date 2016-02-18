// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <list>
#include <memory>

#include "./Binary_tree_prototype.h"

using std::cout;
using std::endl;
using std::list;
using std::make_unique;
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
    exterior.splice(exterior.end(), RightBoundaryAndLeaves(tree->right, true));
  }
  return exterior;
}

// Computes the nodes from the root to the leftmost leaf followed by all the
// leaves in subtree.
list<const unique_ptr<BinaryTreeNode<int>>*> LeftBoundaryAndLeaves(
    const unique_ptr<BinaryTreeNode<int>>& subtree, bool is_boundary) {
  list<const unique_ptr<BinaryTreeNode<int>>*> result;
  if (subtree != nullptr) {
    if (is_boundary || IsLeaf(subtree)) {
      result.emplace_back(&subtree);
    }
    result.splice(result.end(),
                  LeftBoundaryAndLeaves(subtree->left, is_boundary));
    result.splice(result.end(), LeftBoundaryAndLeaves(
                                    subtree->right,
                                    is_boundary && subtree->left == nullptr));
  }
  return result;
}

// Computes the leaves in left-to-right order followed by the rightmost leaf
// to the root path in subtree.
list<const unique_ptr<BinaryTreeNode<int>>*> RightBoundaryAndLeaves(
    const unique_ptr<BinaryTreeNode<int>>& subtree, bool is_boundary) {
  list<const unique_ptr<BinaryTreeNode<int>>*> result;
  if (subtree != nullptr) {
    result.splice(result.end(), RightBoundaryAndLeaves(
                                    subtree->left,
                                    is_boundary && subtree->right == nullptr));
    result.splice(result.end(),
                  RightBoundaryAndLeaves(subtree->right, is_boundary));
    if (is_boundary || IsLeaf(subtree)) {
      result.emplace_back(&subtree);
    }
  }
  return result;
}

bool IsLeaf(const unique_ptr<BinaryTreeNode<int>>& node) {
  return node->left == nullptr && node->right == nullptr;
}
// @exclude

list<int> CreateOutputList(
    const list<const unique_ptr<BinaryTreeNode<int>>*>& L) {
  list<int> output;
  for (const auto* l : L) {
    output.push_back((*l)->data);
  }
  return output;
}

int main(int argc, char* argv[]) {
  //        3
  //    2      5
  //  1  0    4 6
  //   -1 -2
  unique_ptr<BinaryTreeNode<int>> tree = make_unique<BinaryTreeNode<int>>(
      BinaryTreeNode<int>{3, nullptr, nullptr});
  auto L = ExteriorBinaryTree(tree);
  list<int> result = CreateOutputList(L);
  list<int> golden_result = {3};
  assert(result.size() == golden_result.size() &&
         equal(result.begin(), result.end(), golden_result.begin()));

  tree->left = make_unique<BinaryTreeNode<int>>(
      BinaryTreeNode<int>{2, nullptr, nullptr});
  L = ExteriorBinaryTree(tree);
  result = CreateOutputList(L);
  golden_result = {3, 2};
  assert(result.size() == golden_result.size() &&
         equal(result.begin(), result.end(), golden_result.begin()));

  tree->left->right = make_unique<BinaryTreeNode<int>>(
      BinaryTreeNode<int>{0, nullptr, nullptr});
  tree->left->right->left = make_unique<BinaryTreeNode<int>>(
      BinaryTreeNode<int>{-1, nullptr, nullptr});
  tree->left->right->right = make_unique<BinaryTreeNode<int>>(
      BinaryTreeNode<int>{-2, nullptr, nullptr});
  tree->left->left = make_unique<BinaryTreeNode<int>>(
      BinaryTreeNode<int>{1, nullptr, nullptr});
  tree->right = make_unique<BinaryTreeNode<int>>(
      BinaryTreeNode<int>{5, nullptr, nullptr});
  tree->right->left = make_unique<BinaryTreeNode<int>>(
      BinaryTreeNode<int>{4, nullptr, nullptr});
  tree->right->right = make_unique<BinaryTreeNode<int>>(
      BinaryTreeNode<int>{6, nullptr, nullptr});
  L = ExteriorBinaryTree(tree);
  result = CreateOutputList(L);
  golden_result = {3, 2, 1, -1, -2, 4, 6, 5};
  assert(result.size() == golden_result.size() &&
         equal(result.begin(), result.end(), golden_result.begin()));
  return 0;
}
