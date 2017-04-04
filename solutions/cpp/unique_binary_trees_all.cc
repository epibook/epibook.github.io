// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <random>
#include <vector>

#include "./Binary_tree_prototype.h"

using std::cout;
using std::default_random_engine;
using std::endl;
using std::make_unique;
using std::random_device;
using std::stoi;
using std::uniform_int_distribution;
using std::vector;

unique_ptr<BinaryTreeNode<int>> Clone(const unique_ptr<BinaryTreeNode<int>>&);

// @include
vector<unique_ptr<BinaryTreeNode<int>>> GenerateAllBinaryTrees(
    int num_nodes) {
  vector<unique_ptr<BinaryTreeNode<int>>> result;
  if (num_nodes == 0) {  // Empty tree, add as an nullptr.
    result.emplace_back(nullptr);
  }

  for (int num_left_tree_nodes = 0; num_left_tree_nodes < num_nodes;
       ++num_left_tree_nodes) {
    int num_right_tree_nodes = num_nodes - 1 - num_left_tree_nodes;
    auto left_subtrees = GenerateAllBinaryTrees(num_left_tree_nodes);
    auto right_subtrees = GenerateAllBinaryTrees(num_right_tree_nodes);
    // Generates all combinations of left_subtrees and right_subtrees.
    for (auto& left : left_subtrees) {
      for (auto& right : right_subtrees) {
        result.emplace_back(make_unique<BinaryTreeNode<int>>(
            BinaryTreeNode<int>{0, Clone(left), Clone(right)}));
      }
    }
  }
  return result;
}

unique_ptr<BinaryTreeNode<int>> Clone(
    const unique_ptr<BinaryTreeNode<int>>& tree) {
  return tree ? make_unique<BinaryTreeNode<int>>(BinaryTreeNode<int>{
                    0, Clone(tree->left), Clone(tree->right)})
              : nullptr;
}
// @exclude

void SmallTest() {
  assert(GenerateAllBinaryTrees(1).size() == 1);
  assert(GenerateAllBinaryTrees(2).size() == 2);
  assert(GenerateAllBinaryTrees(3).size() == 5);
  assert(GenerateAllBinaryTrees(4).size() == 14);
  assert(GenerateAllBinaryTrees(5).size() == 42);
  assert(GenerateAllBinaryTrees(10).size() == 16796);
}

int main(int argc, char** argv) {
  SmallTest();
  default_random_engine gen((random_device())());
  int n;
  if (argc == 2) {
    n = stoi(argv[1]);
  } else {
    uniform_int_distribution<int> dis(1, 10);
    n = dis(gen);
  }
  cout << "n = " << n << endl;
  GenerateAllBinaryTrees(n);
  return 0;
}
