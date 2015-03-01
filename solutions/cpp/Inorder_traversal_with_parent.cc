// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <memory>
#include <vector>

#include "./Binary_tree_with_parent_prototype.h"

using std::cout;
using std::endl;
using std::unique_ptr;
using std::vector;

vector<int> result;

// @include
void InorderTraversal(const unique_ptr<BinaryTreeNode<int>>& tree) {
  BinaryTreeNode<int>* prev = nullptr, *curr = tree.get();
  while (curr != nullptr) {
    BinaryTreeNode<int>* next;
    if (prev == nullptr || prev->left.get() == curr ||
        prev->right.get() == curr) {
      // We came down to curr from prev.
      if (curr->left != nullptr) {  // Keep going left.
        next = curr->left.get();
      } else {
        cout << curr->data << endl;
        // @exclude
        result.emplace_back(curr->data);
        // @include
        // Done with left, so go right if right is not empty.
        // Otherwise, go up.
        next = (curr->right != nullptr) ? curr->right.get() : curr->parent; 
      }
    } else if (curr->left.get() == prev) {
      // We came up to curr from its left child.
      cout << curr->data << endl;
      // @exclude
      result.emplace_back(curr->data);
      // @include
      // Done with left, so go right if right is not empty. Otherwise, go up.
      next = (curr->right != nullptr) ? curr->right.get() : curr->parent; 
    } else {  // Done with both children, so move up.
      next = curr->parent;
    }

    prev = curr;
    curr = next;
  }
}
// @exclude

int main(int argc, char* argv[]) {
  //      3
  //    2   5
  //  1    4 6
  unique_ptr<BinaryTreeNode<int>> root =
      unique_ptr<BinaryTreeNode<int>>(new BinaryTreeNode<int>{3, nullptr, nullptr});
  root->parent = nullptr;
  root->left =
      unique_ptr<BinaryTreeNode<int>>(new BinaryTreeNode<int>{2, nullptr, nullptr});
  root->left->parent = root.get();
  root->left->left =
      unique_ptr<BinaryTreeNode<int>>(new BinaryTreeNode<int>{1, nullptr, nullptr});
  root->left->left->parent = root->left.get();
  root->right =
      unique_ptr<BinaryTreeNode<int>>(new BinaryTreeNode<int>{5, nullptr, nullptr});
  root->right->parent = root.get();
  root->right->left =
      unique_ptr<BinaryTreeNode<int>>(new BinaryTreeNode<int>{4, nullptr, nullptr});
  root->right->left->parent = root->right.get();
  root->right->right =
      unique_ptr<BinaryTreeNode<int>>(new BinaryTreeNode<int>{6, nullptr, nullptr});
  root->right->right->parent = root->right.get();

  // Should output 1 2 3 4 5 6.
  InorderTraversal(root);
  vector<int> golden_res = {1, 2, 3, 4, 5, 6};
  assert(golden_res.size() == result.size());
  assert(equal(golden_res.begin(), golden_res.end(), result.begin()));
  return 0;
}
