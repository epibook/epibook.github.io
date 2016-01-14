// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <memory>
#include <random>
#include <vector>

#include "./BST_prototype.h"

using std::cout;
using std::default_random_engine;
using std::endl;
using std::make_unique;
using std::random_device;
using std::uniform_int_distribution;
using std::unique_ptr;
using std::vector;

void FindKLargestInBSTHelper(const unique_ptr<BSTNode<int>>&, int,
                             vector<int>*);

// @include
vector<int> FindKLargestInBST(const unique_ptr<BSTNode<int>>& tree, int k) {
  vector<int> k_largest_elements;
  FindKLargestInBSTHelper(tree, k, &k_largest_elements);
  return k_largest_elements;
}

void FindKLargestInBSTHelper(const unique_ptr<BSTNode<int>>& tree, int k,
                             vector<int>* k_largest_elements) {
  // Perform reverse inorder traversal.
  if (tree && k_largest_elements->size() < k) {
    FindKLargestInBSTHelper(tree->right, k, k_largest_elements);
    if (k_largest_elements->size() < k) {
      k_largest_elements->emplace_back(tree->data);
      FindKLargestInBSTHelper(tree->left, k, k_largest_elements);
    }
  }
}
// @exclude

int main(int argc, char* argv[]) {
  //    3
  //  2   5
  // 1   4 6
  unique_ptr<BSTNode<int>> tree = make_unique<BSTNode<int>>(BSTNode<int>{3});
  tree->left = make_unique<BSTNode<int>>(BSTNode<int>{2});
  tree->left->left = make_unique<BSTNode<int>>(BSTNode<int>{1});
  tree->right = make_unique<BSTNode<int>>(BSTNode<int>{5});
  tree->right->left = make_unique<BSTNode<int>>(BSTNode<int>{4});
  tree->right->right = make_unique<BSTNode<int>>(BSTNode<int>{6});
  default_random_engine gen((random_device())());
  int k;
  if (argc == 2) {
    k = atoi(argv[1]);
  } else {
    uniform_int_distribution<int> dis(1, 6);
    k = dis(gen);
  }
  cout << "k = " << k << endl;
  vector<int> ans = FindKLargestInBST(tree, k);
  for (int i = 0; i < ans.size(); ++i) {
    cout << ans[i] << endl;
  }
  for (int i = 1; i < ans.size(); ++i) {
    assert(ans[i - 1] >= ans[i]);
  }
  ans = FindKLargestInBST(tree, 2);
  assert(ans[0] == 6);
  assert(ans[1] == 5);

  //    3
  //  3   4
  // 1   4 6
  tree = make_unique<BSTNode<int>>(BSTNode<int>{3});
  tree->left = make_unique<BSTNode<int>>(BSTNode<int>{3});
  tree->left->left = make_unique<BSTNode<int>>(BSTNode<int>{1});
  tree->right = make_unique<BSTNode<int>>(BSTNode<int>{4});
  tree->right->left = make_unique<BSTNode<int>>(BSTNode<int>{4});
  tree->right->right = make_unique<BSTNode<int>>(BSTNode<int>{6});
  ans = FindKLargestInBST(tree, 3);
  assert(ans[0] == 6);
  assert(ans[1] == 4);
  assert(ans[1] == 4);

  return 0;
}
