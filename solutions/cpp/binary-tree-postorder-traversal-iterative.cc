// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <memory>
#include <stack>
#include <vector>

#include "./Binary_tree_prototype.h"
#include "./Binary_tree_utils.h"

using std::cout;
using std::endl;
using std::equal;
using std::stack;
using std::unique_ptr;
using std::vector;

// @include
// We use stack and previous node pointer to simulate postorder traversal.
vector<int> PostorderTraversal(const unique_ptr<BinaryTreeNode<int>>& root) {
  if (!root) {  // Empty tree.
    return {};
  }

  stack<BinaryTreeNode<int>*> s;
  BinaryTreeNode<int>* prev = nullptr;
  s.emplace(root.get());
  vector<int> res;
  while (!s.empty()) {
    auto curr = s.top();
    if (!prev || prev->left.get() == curr || prev->right.get() == curr) {
      // Going down.
      if (curr->left) {  // Visit left.
        s.emplace(curr->left.get());
      } else if (curr->right) {  // Visit right.
        s.emplace(curr->right.get());
      } else {  // Leaf node, then process current node.
        res.emplace_back(curr->data);
        s.pop();
      }
    } else if (curr->left.get() == prev) {
      // Going up, finished visiting left.
      if (curr->right) {  // Visit right.
        s.emplace(curr->right.get());
      } else {  // No right child, then process current node.
        res.emplace_back(curr->data);
        s.pop();
      }
    } else {  // curr->right.get() == prev.
      // Going up, finished visiting left and right.
      res.emplace_back(curr->data);
      s.pop();
    }
    prev = curr;
  }
  return res;
}
// @exclude

int main(int argc, char** argv) {
  //      3
  //    2   5
  //  1    4 6
  unique_ptr<BinaryTreeNode<int>> root = unique_ptr<BinaryTreeNode<int>>(
      new BinaryTreeNode<int>{3, nullptr, nullptr});
  root->left = unique_ptr<BinaryTreeNode<int>>(
      new BinaryTreeNode<int>{2, nullptr, nullptr});
  root->left->left = unique_ptr<BinaryTreeNode<int>>(
      new BinaryTreeNode<int>{1, nullptr, nullptr});
  root->right = unique_ptr<BinaryTreeNode<int>>(
      new BinaryTreeNode<int>{5, nullptr, nullptr});
  root->right->left = unique_ptr<BinaryTreeNode<int>>(
      new BinaryTreeNode<int>{4, nullptr, nullptr});
  root->right->right = unique_ptr<BinaryTreeNode<int>>(
      new BinaryTreeNode<int>{6, nullptr, nullptr});
  auto res = PostorderTraversal(root), golden_res = generate_postorder(root);
  assert(res.size() == golden_res.size() &&
         equal(res.cbegin(), res.cend(), golden_res.cbegin()));
  return 0;
}
