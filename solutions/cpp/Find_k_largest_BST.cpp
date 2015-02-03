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
using std::random_device;
using std::uniform_int_distribution;
using std::unique_ptr;
using std::vector;

void FindKLargestInBSTHelper(const unique_ptr<BSTNode<int>>& root, int k,
                             vector<int>* k_largest_elements);

// @include
vector<int> FindKLargestInBST(const unique_ptr<BSTNode<int>>& root, int k) {
  vector<int> k_largest_elements;
  FindKLargestInBSTHelper(root, k, &k_largest_elements);
  return k_largest_elements;
}

void FindKLargestInBSTHelper(const unique_ptr<BSTNode<int>>& root, int k,
                             vector<int>* k_largest_elements) {
  // Perform reverse inorder traversal.
  if (root && k_largest_elements->size() < k) {
    FindKLargestInBSTHelper(root->right, k, k_largest_elements);
    if (k_largest_elements->size() < k) {
      k_largest_elements->emplace_back(root->data);
      FindKLargestInBSTHelper(root->left, k, k_largest_elements);
    }
  }
}
// @exclude

int main(int argc, char* argv[]) {
  //    3
  //  2   5
  // 1   4 6
  unique_ptr<BSTNode<int>> root = unique_ptr<BSTNode<int>>(new BSTNode<int>{3});
  root->left = unique_ptr<BSTNode<int>>(new BSTNode<int>{2});
  root->left->left = unique_ptr<BSTNode<int>>(new BSTNode<int>{1});
  root->right = unique_ptr<BSTNode<int>>(new BSTNode<int>{5});
  root->right->left = unique_ptr<BSTNode<int>>(new BSTNode<int>{4});
  root->right->right = unique_ptr<BSTNode<int>>(new BSTNode<int>{6});
  default_random_engine gen((random_device())());
  int k;
  if (argc == 2) {
    k = atoi(argv[1]);
  } else {
    uniform_int_distribution<int> dis(1, 6);
    k = dis(gen);
  }
  cout << "k = " << k << endl;
  vector<int> ans = FindKLargestInBST(root, k);
  for (int i = 0; i < ans.size(); ++i) {
    cout << ans[i] << endl;
  }
  for (int i = 1; i < ans.size(); ++i) {
    assert(ans[i - 1] >= ans[i]);
  }
  return 0;
}
