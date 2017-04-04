// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <memory>
#include <stack>
#include <vector>

#include "./Binary_tree_prototype.h"

using std::cout;
using std::endl;
using std::make_unique;
using std::ostream_iterator;
using std::stack;
using std::unique_ptr;
using std::vector;

vector<int> result;

// @include
void print_binary_tree_zigzag_level_order(
    const unique_ptr<BinaryTreeNode<int>>& root) {
  stack<BinaryTreeNode<int> *> curr_level, next_level;
  bool left_to_right = true;
  curr_level.emplace(root.get());
  while (!curr_level.empty()) {
    auto curr_node = curr_level.top();
    curr_level.pop();
    if (curr_node) {
      cout << curr_node->data << " ";
      // @exclude
      result.emplace_back(curr_node->data);
      // @include
      if (left_to_right) {
        next_level.emplace(curr_node->left.get());
        next_level.emplace(curr_node->right.get());
      } else {
        next_level.emplace(curr_node->right.get());
        next_level.emplace(curr_node->left.get());
      }
    }
    if (curr_level.empty() && !next_level.empty()) {
      cout << endl;
      // @exclude
      result.emplace_back(-1);
      // @include
      // Toggles left_to_right.
      left_to_right = !left_to_right;
      curr_level.swap(next_level);
    }
  }
}
// @exclude

int main(int argc, char* argv[]) {
  //      3
  //    2   5
  //  1    4 6
  unique_ptr<BinaryTreeNode<int>> root = make_unique<BinaryTreeNode<int>>(
      BinaryTreeNode<int>{3, nullptr, nullptr});
  root->left = make_unique<BinaryTreeNode<int>>(
      BinaryTreeNode<int>{2, nullptr, nullptr});
  root->left->left = make_unique<BinaryTreeNode<int>>(
      BinaryTreeNode<int>{1, nullptr, nullptr});
  root->right = make_unique<BinaryTreeNode<int>>(
      BinaryTreeNode<int>{5, nullptr, nullptr});
  root->right->left = make_unique<BinaryTreeNode<int>>(
      BinaryTreeNode<int>{4, nullptr, nullptr});
  root->right->right = make_unique<BinaryTreeNode<int>>(
      BinaryTreeNode<int>{6, nullptr, nullptr});
  print_binary_tree_zigzag_level_order(root);
  vector<int> golden_res = {3, -1, 5, 2, -1, 1, 4, 6, -1};
  assert(equal(golden_res.begin(), golden_res.end(), result.begin(),
               result.end()));
  return 0;
}
