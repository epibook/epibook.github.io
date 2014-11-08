// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <memory>
#include <random>
#include <stack>
#include <vector>

#include "./Binary_tree_prototype.h"
#include "./Binary_tree_utils.h"

using std::cout;
using std::default_random_engine;
using std::endl;
using std::random_device;
using std::stack;
using std::uniform_int_distribution;
using std::unique_ptr;
using std::vector;

unique_ptr<BinaryTreeNode<int>> ReconstructPreorderHelper(
    const vector<int*>& preorder, int* idx_pointer);

// @include
unique_ptr<BinaryTreeNode<int>> ReconstructPreorder(
    const vector<int*>& preorder) {
  int idx_pointer = 0;
  return ReconstructPreorderHelper(preorder, &idx_pointer);
}

unique_ptr<BinaryTreeNode<int>> ReconstructPreorderHelper(
    const vector<int*>& preorder, int* idx_pointer) {
  auto* subtree_key = preorder[*idx_pointer];
  ++*idx_pointer;
  if (subtree_key == nullptr) {
    return nullptr;
  }
  // Note that ReconstructPreorderHelper updates idx_pointer. So the order of
  // following two calls are critical.
  auto left_subtree = ReconstructPreorderHelper(preorder, idx_pointer);
  auto right_subtree = ReconstructPreorderHelper(preorder, idx_pointer);
  return unique_ptr<BinaryTreeNode<int>>(new BinaryTreeNode<int>{
      *subtree_key, move(left_subtree), move(right_subtree)});
}
// @exclude

template <typename T>
void GenPreorderWithNull(const unique_ptr<BinaryTreeNode<T>>& n,
                         vector<T*>* p) {
  if (!n) {
    p->emplace_back(nullptr);
    return;
  }

  p->emplace_back(&(n->data));
  GenPreorderWithNull(n->left, p);
  GenPreorderWithNull(n->right, p);
}

int main(int argc, char* argv[]) {
  default_random_engine gen((random_device())());
  // Random test 1000 times.
  for (int times = 0; times < 1000; ++times) {
    cout << times << endl;
    int n;
    if (argc == 2) {
      n = atoi(argv[1]);
    } else {
      uniform_int_distribution<int> dis(1, 10000);
      n = dis(gen);
    }
    unique_ptr<BinaryTreeNode<int>> root = generate_rand_binary_tree<int>(n);
    vector<int*> p;
    GenPreorderWithNull(root, &p);
    auto x = unique_ptr<BinaryTreeNode<int>>(ReconstructPreorder(p));
    assert(is_two_binary_trees_equal(root, x));
    delete_binary_tree(&root);
    delete_binary_tree(&x);
  }
  return 0;
}
