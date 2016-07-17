// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#ifndef SOLUTIONS_BINARY_TREE_UTILS_H_
#define SOLUTIONS_BINARY_TREE_UTILS_H_

#include <limits>
#include <list>
#include <memory>
#include <random>
#include <string>
#include <vector>

#include "./Binary_tree_prototype.h"

using std::default_random_engine;
using std::list;
using std::make_unique;
using std::numeric_limits;
using std::random_device;
using std::unique_ptr;
using std::string;
using std::uniform_int_distribution;
using std::vector;

template <typename T>
unique_ptr<BinaryTreeNode<T>> generate_rand_binary_tree(
    int n, bool is_unique = false) {
  default_random_engine gen((random_device())());
  list<unique_ptr<BinaryTreeNode<T>>*> l;
  uniform_int_distribution<int> dis(0, numeric_limits<int>::max());
  auto root = make_unique<BinaryTreeNode<T>>(
      BinaryTreeNode<T>{(is_unique ? n-- : dis(gen))});
  l.emplace_back(&(root->left));
  l.emplace_back(&(root->right));
  while (n--) {
    uniform_int_distribution<int> x_dis(0, l.size() - 1);
    int x = x_dis(gen);
    typename list<unique_ptr<BinaryTreeNode<T>>*>::iterator it = l.begin();
    advance(it, x);
    **it = make_unique<BinaryTreeNode<T>>(
        BinaryTreeNode<T>{(is_unique ? n : dis(gen))});
    l.emplace_back(&((**it)->left));
    l.emplace_back(&((**it)->right));
    l.erase(it);
  }
  return root;
}

template <typename T>
void delete_binary_tree(unique_ptr<BinaryTreeNode<T>>* n) {
  if (n) {
    if ((*n)->left.get()) {
      delete_binary_tree(&((*n)->left));
    }
    if ((*n)->right.get()) {
      delete_binary_tree(&((*n)->right));
    }
    n->reset(nullptr);
  }
}

template <typename T>
bool is_two_binary_trees_equal(const unique_ptr<BinaryTreeNode<T>>& r1,
                               const unique_ptr<BinaryTreeNode<T>>& r2) {
  if (r1 && r2) {
    return is_two_binary_trees_equal(r1->left, r2->left) &&
           is_two_binary_trees_equal(r1->right, r2->right) &&
           r1->data == r2->data;
  } else if (!r1 && !r2) {
    return true;
  } else {
    return false;
  }
}

template <typename T>
void generate_preorder_helper(const unique_ptr<BinaryTreeNode<T>>& r,
                              vector<T>* ret) {
  if (r) {
    ret->emplace_back(r->data);
    generate_preorder_helper(r->left, ret);
    generate_preorder_helper(r->right, ret);
  }
}

template <typename T>
vector<T> generate_preorder(const unique_ptr<BinaryTreeNode<T>>& r) {
  vector<T> ret;
  generate_preorder_helper(r, &ret);
  return ret;
}

template <typename T>
void generate_inorder_helper(const unique_ptr<BinaryTreeNode<T>>& r,
                             vector<T>* ret) {
  if (r) {
    generate_inorder_helper(r->left, ret);
    ret->emplace_back(r->data);
    generate_inorder_helper(r->right, ret);
  }
}

template <typename T>
vector<T> generate_inorder(const unique_ptr<BinaryTreeNode<T>>& r) {
  vector<T> ret;
  generate_inorder_helper(r, &ret);
  return ret;
}

template <typename T>
void generate_postorder_helper(const unique_ptr<BinaryTreeNode<T>>& r,
                               vector<T>* ret) {
  if (r) {
    generate_postorder_helper(r->left, ret);
    generate_postorder_helper(r->right, ret);
    ret->emplace_back(r->data);
  }
}

template <typename T>
vector<T> generate_postorder(const unique_ptr<BinaryTreeNode<T>>& r) {
  vector<T> ret;
  generate_postorder_helper(r, &ret);
  return ret;
}
#endif  // SOLUTIONS_BINARY_TREE_UTILS_H_
