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

unique_ptr<BinaryTreeNode<int>> ReconstructPostInordersHelper(
    const vector<int>&, size_t, size_t, size_t, size_t,
    const unordered_map<int, size_t>&);

// @include
unique_ptr<BinaryTreeNode<int>> ReconstructPostInorders(
    const vector<int>& post, const vector<int>& in) {
  unordered_map<int, size_t> in_entry_idx_map;
  for (size_t i = 0; i < in.size(); ++i) {
    in_entry_idx_map.emplace(in[i], i);
  }
  return ReconstructPostInordersHelper(post, 0, post.size(), 0, in.size(),
                                       in_entry_idx_map);
}

unique_ptr<BinaryTreeNode<int>> ReconstructPostInordersHelper(
    const vector<int>& post, size_t post_s, size_t post_e, size_t in_s,
    size_t in_e, const unordered_map<int, size_t>& in_entry_idx_map) {
  if (post_e > post_s && in_e > in_s) {
    auto idx = in_entry_idx_map.at(post[post_e - 1]);
    auto left_tree_size = idx - in_s;

    return make_unique<BinaryTreeNode<int>>(BinaryTreeNode<int>{
        post[post_e - 1],
        // Recursively builds the left subtree.
        ReconstructPostInordersHelper(post, post_s, post_s + left_tree_size,
                                      in_s, idx, in_entry_idx_map),
        // Recursively builds the right subtree.
        ReconstructPostInordersHelper(post, post_s + left_tree_size,
                                      post_e - 1, idx + 1, in_e,
                                      in_entry_idx_map)});
  }
  return nullptr;
}
// @exclude

int main(int argc, char* argv[]) {
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
    vector<int> post = generate_postorder(root);
    vector<int> in = generate_inorder(root);
    auto res = ReconstructPostInorders(post, in);
    assert(is_two_binary_trees_equal<int>(root, res));
    delete_binary_tree(&root);
    delete_binary_tree(&res);
  }
  return 0;
}
