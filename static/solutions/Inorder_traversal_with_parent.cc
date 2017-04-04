// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <memory>
#include <vector>

#include "./Binary_tree_with_parent_prototype.h"

using std::cout;
using std::endl;
using std::make_unique;
using std::unique_ptr;
using std::vector;

// @include
vector<int> InorderTraversal(const unique_ptr<BinaryTreeNode<int>>& tree) {
  BinaryTreeNode<int> *prev = nullptr, *curr = tree.get();
  vector<int> result;

  while (curr != nullptr) {
    BinaryTreeNode<int>* next;
    if (curr->parent == prev) {
      // We came down to curr from prev.
      if (curr->left != nullptr) {  // Keep going left.
        next = curr->left.get();
      } else {
        result.emplace_back(curr->data);
        // Done with left, so go right if right is not empty.
        // Otherwise, go up.
        next = (curr->right != nullptr) ? curr->right.get() : curr->parent;
      }
    } else if (curr->left.get() == prev) {
      // We came up to curr from its left child.
      result.emplace_back(curr->data);
      // Done with left, so go right if right is not empty. Otherwise, go up.
      next = (curr->right != nullptr) ? curr->right.get() : curr->parent;
    } else {  // Done with both children, so move up.
      next = curr->parent;
    }

    prev = curr;
    curr = next;
  }
  return result;
}
// @exclude

int main(int argc, char* argv[]) {
  //      3
  //    2   5
  //  1    4 6
  unique_ptr<BinaryTreeNode<int>> root = make_unique<BinaryTreeNode<int>>(
      BinaryTreeNode<int>{3, nullptr, nullptr});
  root->parent = nullptr;
  auto result = InorderTraversal(root);
  vector<int> golden_res = {3};
  assert(equal(golden_res.begin(), golden_res.end(), result.begin(),
               result.end()));

  root->left = make_unique<BinaryTreeNode<int>>(
      BinaryTreeNode<int>{2, nullptr, nullptr});
  root->left->parent = root.get();
  root->left->left = make_unique<BinaryTreeNode<int>>(
      BinaryTreeNode<int>{1, nullptr, nullptr});
  root->left->left->parent = root->left.get();
  result = InorderTraversal(root);
  golden_res = {1, 2, 3};
  assert(equal(golden_res.begin(), golden_res.end(), result.begin(),
               result.end()));

  root->right = make_unique<BinaryTreeNode<int>>(
      BinaryTreeNode<int>{5, nullptr, nullptr});
  root->right->parent = root.get();
  root->right->left = make_unique<BinaryTreeNode<int>>(
      BinaryTreeNode<int>{4, nullptr, nullptr});
  root->right->left->parent = root->right.get();
  root->right->right = make_unique<BinaryTreeNode<int>>(
      BinaryTreeNode<int>{6, nullptr, nullptr});
  root->right->right->parent = root->right.get();

  result = InorderTraversal(root);
  golden_res = {1, 2, 3, 4, 5, 6};
  assert(equal(golden_res.begin(), golden_res.end(), result.begin(),
               result.end()));
  return 0;
}
