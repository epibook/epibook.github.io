// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <memory>

#include "./BST_prototype.h"

using std::unique_ptr;

// @include
BSTNode<int>* FindFirstEqualK(const unique_ptr<BSTNode<int>>& tree, int k) {
  if (!tree) {
    return nullptr;  // No match.
  } else if (tree->data == k) {
    // Recursively search the left subtree for first node containing k.
    auto* node = FindFirstEqualK(tree->left, k);
    return node ? node : tree.get();
  }
  // Search the left or right subtree based on relative values of tree->data
  // and k.
  return FindFirstEqualK(tree->data < k ? tree->right : tree->left, k);
}
// @exclude

int main(int argc, char* argv[]) {
  //    3
  //  2   6
  // 1   4 6
  auto root = unique_ptr<BSTNode<int>>(new BSTNode<int>{3});
  root->left = unique_ptr<BSTNode<int>>(new BSTNode<int>{2});
  root->left->left = unique_ptr<BSTNode<int>>(new BSTNode<int>{1});
  root->right = unique_ptr<BSTNode<int>>(new BSTNode<int>{6});
  root->right->left = unique_ptr<BSTNode<int>>(new BSTNode<int>{4});
  root->right->right = unique_ptr<BSTNode<int>>(new BSTNode<int>{6});
  assert(!FindFirstEqualK(root, 7));
  assert(FindFirstEqualK(root, 6)->data == 6 &&
         FindFirstEqualK(root, 6)->right->data == 6);

  //    3
  //  3   5
  // 2   5 6
  root = unique_ptr<BSTNode<int>>(new BSTNode<int>{3});
  root->left = unique_ptr<BSTNode<int>>(new BSTNode<int>{3});
  root->left->left = unique_ptr<BSTNode<int>>(new BSTNode<int>{2});
  root->right = unique_ptr<BSTNode<int>>(new BSTNode<int>{5});
  root->right->left = unique_ptr<BSTNode<int>>(new BSTNode<int>{5});
  root->right->right = unique_ptr<BSTNode<int>>(new BSTNode<int>{6});
  assert(!FindFirstEqualK(root, 7));
  assert(FindFirstEqualK(root, 3) == root->left.get());
  assert(FindFirstEqualK(root, 5) == root->right->left.get());
  assert(FindFirstEqualK(root, 6)->data == 6);
  return 0;
}
