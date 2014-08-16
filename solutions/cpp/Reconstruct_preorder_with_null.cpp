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

// @include
BinaryTreeNode<int>* ReconstructPreorder(const vector<int*>& preorder) {
  stack<BinaryTreeNode<int>*> s;
  for (auto it = preorder.crbegin(); it != preorder.crend(); ++it) {
    if (!*it) {
      s.emplace(nullptr);
    } else {  // Non-null.
      auto* l_node = s.top();
      s.pop();
      auto* r_node = s.top();
      s.pop();
      s.emplace(
          new BinaryTreeNode<int>{**it,
                                  unique_ptr<BinaryTreeNode<int>>(l_node),
                                  unique_ptr<BinaryTreeNode<int>>(r_node)});
    }
  }
  return s.top();
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
