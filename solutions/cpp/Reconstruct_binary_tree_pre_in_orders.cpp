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
using std::random_device;
using std::uniform_int_distribution;
using std::unique_ptr;
using std::unordered_map;
using std::vector;

unique_ptr<BinaryTreeNode<int>> ReconstructPreInOrdersHelper(
    const vector<int>& pre, size_t pre_s, size_t pre_e, const vector<int>& in,
    size_t in_s, size_t in_e,
    const unordered_map<int, size_t>& in_entry_idx_map);

// @include
unique_ptr<BinaryTreeNode<int>> ReconstructPreInOrders(
    const vector<int>& pre, const vector<int>& in) {
  unordered_map<int, size_t> in_entry_idx_map;
  for (size_t i = 0; i < in.size(); ++i) {
    in_entry_idx_map.emplace(in[i], i);
  }
  return ReconstructPreInOrdersHelper(pre, 0, pre.size(), in, 0, in.size(),
                                      in_entry_idx_map);
}

// Reconstructs the binary tree from pre[pre_s : pre_e - 1] and
// in[in_s : in_e - 1].
unique_ptr<BinaryTreeNode<int>> ReconstructPreInOrdersHelper(
    const vector<int>& pre, size_t pre_s, size_t pre_e,
    const vector<int>& in, size_t in_s, size_t in_e,
    const unordered_map<int, size_t>& in_entry_idx_map) {
  if (pre_e > pre_s && in_e > in_s) {
    auto idx = in_entry_idx_map.at(pre[pre_s]);
    auto left_tree_size = idx - in_s;

    return unique_ptr<BinaryTreeNode<int>>(new BinaryTreeNode<int>{
        pre[pre_s],
        // Recursively builds the left subtree.
        ReconstructPreInOrdersHelper(
            pre, pre_s + 1, pre_s + 1 + left_tree_size,
            in, in_s, idx, in_entry_idx_map),
        // Recursively builds the right subtree.
        ReconstructPreInOrdersHelper(
            pre, pre_s + 1 + left_tree_size, pre_e,
            in, idx + 1, in_e, in_entry_idx_map)});
  }
  return nullptr;
}
// @exclude

int main(int argc, char *argv[]) {
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
    auto res = ReconstructPreInOrders(pre, in);
    assert(is_two_binary_trees_equal<int>(root, res));
    delete_binary_tree(&root);
    delete_binary_tree(&res);
  }
  return 0;
}
