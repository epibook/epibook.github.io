// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <memory>
#include <random>
#include <unordered_map>
#include <vector>

#include "./Binary_tree_prototype.h"
#include "./Binary_tree_utils.h"

using std::cout;
using std::default_random_engine;
using std::endl;
using std::make_unique;
using std::random_device;
using std::uniform_int_distribution;
using std::unique_ptr;
using std::unordered_map;
using std::vector;

unique_ptr<BinaryTreeNode<int>> BinaryTreeFromPreorderInorderHelper(
    const vector<int>&, size_t, size_t, size_t, size_t,
    const unordered_map<int, size_t>&);

// @include
unique_ptr<BinaryTreeNode<int>> BinaryTreeFromPreorderInorder(
    const vector<int>& preorder, const vector<int>& inorder) {
  unordered_map<int, size_t> node_to_inorder_idx;
  for (size_t i = 0; i < inorder.size(); ++i) {
    node_to_inorder_idx.emplace(inorder[i], i);
  }
  return BinaryTreeFromPreorderInorderHelper(
      preorder, 0, preorder.size(), 0, inorder.size(), node_to_inorder_idx);
}

// Builds the subtree with preorder[preorder_start : preorder_end - 1] and
// inorder[inorder_start : inorder_end - 1].
unique_ptr<BinaryTreeNode<int>> BinaryTreeFromPreorderInorderHelper(
    const vector<int>& preorder, size_t preorder_start, size_t preorder_end,
    size_t inorder_start, size_t inorder_end,
    const unordered_map<int, size_t>& node_to_inorder_idx) {
  if (preorder_end <= preorder_start || inorder_end <= inorder_start) {
    return nullptr;
  }
  size_t root_inorder_idx = node_to_inorder_idx.at(preorder[preorder_start]);
  size_t left_subtree_size = root_inorder_idx - inorder_start;

  return make_unique<BinaryTreeNode<int>>(BinaryTreeNode<int>{
      preorder[preorder_start],
      // Recursively builds the left subtree.
      BinaryTreeFromPreorderInorderHelper(
          preorder, preorder_start + 1,
          preorder_start + 1 + left_subtree_size, inorder_start,
          root_inorder_idx, node_to_inorder_idx),
      // Recursively builds the right subtree.
      BinaryTreeFromPreorderInorderHelper(
          preorder, preorder_start + 1 + left_subtree_size, preorder_end,
          root_inorder_idx + 1, inorder_end, node_to_inorder_idx)});
}
// @exclude

void SimpleTest() {
  auto res = BinaryTreeFromPreorderInorder({1}, {1});
  assert(res->data == 1);

  res = BinaryTreeFromPreorderInorder({2, 1}, {1, 2});
  assert(res->data == 2 && res->left->data == 1 && res->right == nullptr);

  int N = 100;
  vector<int> inorder, preorder;
  for (int i = 0; i < N; ++i) {
    inorder.emplace_back(i);
    preorder.emplace_back((N - 1) - i);
  }

  res = BinaryTreeFromPreorderInorder(preorder, inorder);
  assert(res->data == N - 1 && res->left->data == N - 2 &&
         res->right == nullptr);
}

int main(int argc, char* argv[]) {
  SimpleTest();
  default_random_engine gen((random_device())());
  for (int times = 0; times < 1000; ++times) {
    cout << times << endl;
    int n;
    if (argc == 2) {
      n = atoi(argv[1]);
    } else {
      uniform_int_distribution<int> dis(1, 10000);
      n = dis(gen);
    }
    unique_ptr<BinaryTreeNode<int>> root =
        generate_rand_binary_tree<int>(n, true);
    vector<int> pre = generate_preorder(root);
    vector<int> in = generate_inorder(root);
    auto res = BinaryTreeFromPreorderInorder(pre, in);
    assert(is_two_binary_trees_equal<int>(root, res));
    delete_binary_tree(&root);
    delete_binary_tree(&res);
  }
  return 0;
}
