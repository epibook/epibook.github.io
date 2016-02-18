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
using std::make_unique;
using std::random_device;
using std::stack;
using std::uniform_int_distribution;
using std::unique_ptr;
using std::vector;

unique_ptr<BinaryTreeNode<int>> ReconstructPreorderHelper(const vector<int*>&,
                                                          int*);

// @include
unique_ptr<BinaryTreeNode<int>> ReconstructPreorder(
    const vector<int*>& preorder) {
  int subtree_idx_pointer = 0;
  return ReconstructPreorderHelper(preorder, &subtree_idx_pointer);
}

// Reconstructs the subtree that is rooted at subtreeIdx.
unique_ptr<BinaryTreeNode<int>> ReconstructPreorderHelper(
    const vector<int*>& preorder, int* subtree_idx_pointer) {
  int& subtree_idx = *subtree_idx_pointer;
  int* subtree_key = preorder[subtree_idx];
  ++subtree_idx;
  if (subtree_key == nullptr) {
    return nullptr;
  }
  // Note that ReconstructPreorderHelper updates subtree_idx. So the order of
  // following two calls are critical.
  auto left_subtree = ReconstructPreorderHelper(preorder, subtree_idx_pointer);
  auto right_subtree =
      ReconstructPreorderHelper(preorder, subtree_idx_pointer);
  return make_unique<BinaryTreeNode<int>>(BinaryTreeNode<int>{
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

static void simpleTest() {
  int A[] = {1, 2, 3};
  vector<int*> preorder = {A, nullptr, nullptr};
  auto result = ReconstructPreorder(preorder);
  assert(result->data == 1);
  assert(result->left == nullptr);
  assert(result->right == nullptr);

  preorder = {A, nullptr, A + 1, nullptr, nullptr};
  result = ReconstructPreorder(preorder);
  assert(result->data == 1);
  assert(result->left == nullptr);
  assert(result->right->data == 2);

  preorder = {A, nullptr, A + 1, A + 2, nullptr, nullptr, nullptr};
  result = ReconstructPreorder(preorder);
  assert(result->data == 1);
  assert(result->left == nullptr);
  assert(result->right->data == 2);
  assert(result->right->left->data == 3);
  assert(result->right->right == nullptr);
}

int main(int argc, char* argv[]) {
  simpleTest();
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
