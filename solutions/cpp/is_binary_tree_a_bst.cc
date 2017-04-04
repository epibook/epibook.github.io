// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <limits>
#include <memory>
#include <random>

#include "./Binary_tree_prototype.h"
#include "./Binary_tree_utils.h"

using std::boolalpha;
using std::cout;
using std::default_random_engine;
using std::endl;
using std::make_unique;
using std::numeric_limits;
using std::random_device;
using std::uniform_int_distribution;
using std::unique_ptr;

bool AreKeysInRange(const unique_ptr<BinaryTreeNode<int>>&, int, int);

// @include
bool IsBinaryTreeBST(const unique_ptr<BinaryTreeNode<int>>& tree) {
  return AreKeysInRange(tree, numeric_limits<int>::min(),
                        numeric_limits<int>::max());
}

bool AreKeysInRange(const unique_ptr<BinaryTreeNode<int>>& tree,
                    int low_range, int high_range) {
  if (tree == nullptr) {
    return true;
  } else if (tree->data < low_range || tree->data > high_range) {
    return false;
  }

  return AreKeysInRange(tree->left, low_range, tree->data) &&
         AreKeysInRange(tree->right, tree->data, high_range);
}
// @exclude

bool InorderTraversal(const unique_ptr<BinaryTreeNode<int>>& tree,
                      BinaryTreeNode<int>** prev) {
  if (!tree) {
    return true;
  } else if (!InorderTraversal(tree->left, prev)) {
    return false;
  } else if (*prev && (*prev)->data > tree->data) {
    return false;
  }
  *prev = tree.get();
  return InorderTraversal(tree->right, prev);
}

bool IsBinaryTreeBSTAlternative(const unique_ptr<BinaryTreeNode<int>>& tree) {
  BinaryTreeNode<int>* prev = nullptr;
  return InorderTraversal(tree, &prev);
}

void Test() {
  //      3
  //    2   5
  //  1    4 6
  auto tree = make_unique<BinaryTreeNode<int>>(BinaryTreeNode<int>{3});
  tree->left = make_unique<BinaryTreeNode<int>>(BinaryTreeNode<int>{2});
  tree->left->left = make_unique<BinaryTreeNode<int>>(BinaryTreeNode<int>{1});
  tree->right = make_unique<BinaryTreeNode<int>>(BinaryTreeNode<int>{5});
  tree->right->left =
      make_unique<BinaryTreeNode<int>>(BinaryTreeNode<int>{4});
  tree->right->right =
      make_unique<BinaryTreeNode<int>>(BinaryTreeNode<int>{6});
  // should output true.
  assert(IsBinaryTreeBST(tree) == true);
  assert(IsBinaryTreeBSTAlternative(tree) == true);
  //      10
  //    2   5
  //  1    4 6
  tree->data = 10;
  // should output false.
  assert(!IsBinaryTreeBST(tree));
  assert(!IsBinaryTreeBSTAlternative(tree));
  // should output true.
  assert(IsBinaryTreeBST(nullptr) == true);
  assert(IsBinaryTreeBSTAlternative(nullptr) == true);
}

int main(int argc, char* argv[]) {
  default_random_engine gen((random_device())());
  for (int times = 0; times < 1000; ++times) {
    int n;
    if (argc == 2) {
      n = atoi(argv[1]);
    } else {
      uniform_int_distribution<int> dis(0, 5);
      n = dis(gen);
    }
    unique_ptr<BinaryTreeNode<int>> root =
        GenerateRandBinaryTree<int>(n, false);
    assert(IsBinaryTreeBST(root) == IsBinaryTreeBSTAlternative(root));
    DeleteBinaryTree(&root);
  }
  return 0;
}
