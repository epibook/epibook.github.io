// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <random>
#include <string>
#include <vector>

#include "./Binary_tree_prototype.h"
#include "./Binary_tree_utils.h"

using std::cout;
using std::default_random_engine;
using std::endl;
using std::ostream_iterator;
using std::random_device;
using std::string;
using std::stoi;
using std::uniform_int_distribution;
using std::vector;

vector<unique_ptr<BinaryTreeNode<int>>> GenerateAllBinaryTreesHelper(int start,
                                                                     int end);

// @include
vector<unique_ptr<BinaryTreeNode<int>>> GenerateAllBinaryTrees(int n) {
  return GenerateAllBinaryTreesHelper(1, n);
}

vector<unique_ptr<BinaryTreeNode<int>>> GenerateAllBinaryTreesHelper(
    int start, int end) {
  vector<unique_ptr<BinaryTreeNode<int>>> result;
  if (start > end) {
    result.emplace_back(nullptr);
    return result;
  }

  for (int i = start; i <= end; ++i) {
    // Tries all possible combinations of left subtrees and right subtrees.
    auto left_res = GenerateAllBinaryTreesHelper(start, i - 1),
         right_res = GenerateAllBinaryTreesHelper(i + 1, end);
    for (auto& left : left_res) {
      for (auto& right : right_res) {
        result.emplace_back(
            new BinaryTreeNode<int>{i, move(left), move(right)});
      }
    }
  }
  return result;
}
// @exclude

int main(int argc, char** argv) {
  default_random_engine gen((random_device())());
  int n;
  if (argc == 2) {
    n = stoi(argv[1]);
  } else {
    uniform_int_distribution<int> dis(1, 10);
    n = dis(gen);
  }
  cout << "n = " << n << endl;
  auto result = GenerateAllBinaryTrees(n);
  for (const auto& tree : result) {
    auto sequence = generate_inorder(tree);
    assert(is_sorted(sequence.begin(), sequence.end()));
  }
  return 0;
}
