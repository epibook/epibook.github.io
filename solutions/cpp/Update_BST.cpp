// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <memory>

using std::cout;
using std::endl;
using std::unique_ptr;

// @include
class BinarySearchTree {
 public:
  virtual ~BinarySearchTree() { Clear(); }

  bool Empty() const { return !root_.get(); }

  void Clear() { Clear(&root_); }

  bool Insert(int key) {
    if (Empty()) {
      root_ = unique_ptr<TreeNode>(new TreeNode{key, nullptr, nullptr});
    } else {
      TreeNode* curr = root_.get(), *par;
      while (curr) {
        par = curr;
        if (key == curr->data) {
          return false;  // no insertion for duplicate key.
        } else if (key < curr->data) {
          curr = curr->left.get();
        } else {  // key > curr->data.
          curr = curr->right.get();
        }
      }

      // Inserts key according to key and par.
      if (key < par->data) {
        par->left.reset(new TreeNode{key});
      } else {
        par->right.reset(new TreeNode{key});
      }
    }
    return true;
  }

  bool Erase(int key) {
    // Finds the node with key.
    TreeNode* curr = root_.get(), *par = nullptr;
    while (curr && curr->data != key) {
      par = curr;
      curr = key < curr->data ? curr->left.get() : curr->right.get();
    }

    // No node with key in this binary tree.
    if (!curr) {
      return false;
    }

    if (curr->right) {
      // Finds the minimum of the right subtree.
      TreeNode* r_curr = curr->right.get(), *r_par = curr;
      while (r_curr->left) {
        r_par = r_curr;
        r_curr = r_curr->left.get();
      }
      // Moves links to erase the node.
      r_curr->left.reset(curr->left.release());
      TreeNode* r_curr_right = r_curr->right.release();
      if (curr->right.get() != r_curr) {
        r_curr->right.reset(curr->right.release());
      }
      if (r_par->left.get() == r_curr) {
        r_curr = r_par->left.release();
        r_par->left.reset(r_curr_right);
      } else {  // r_par->right.get() == r_curr.
        r_curr = r_par->right.release();
        r_par->right.reset(r_curr_right);
      }
      ReplaceParentChildLink(par, curr, r_curr);

      // Updates root_ link if needed.
      if (root_.get() == curr) {
        root_.reset(r_curr);
      }
    } else {
      // Updates root_ link if needed.
      if (root_.get() == curr) {
        root_.reset(curr->left.release());
      }
      ReplaceParentChildLink(par, curr, curr->left.get());
    }
    return true;
  }
  // @exclude
  int GetRootVal() const { return root_->data; }
  // @include

 private:
  struct TreeNode {
    int data;
    unique_ptr<TreeNode> left, right;
  };

  void Clear(unique_ptr<TreeNode>* node) {
    if (node->get()) {
      if ((*node)->left.get()) {
        Clear(&((*node)->left));
      }
      if ((*node)->right.get()) {
        Clear(&((*node)->right));
      }
      node->reset(nullptr);
    }
  }

  // Replaces the link between par and child by new_link.
  void ReplaceParentChildLink(TreeNode* par,
                              TreeNode* child,
                              TreeNode* new_link) {
    if (!par) {
      return;
    }

    if (par->left.get() == child) {
      par->left.reset(new_link);
    } else {  // par->right.get() == child.
      par->right.reset(new_link);
    }
  }

  unique_ptr<TreeNode> root_ = nullptr;
};
// @exclude

int main(int argc, char* argv[]) {
  BinarySearchTree BST;
  assert(BST.Empty() == true);
  assert(BST.Insert(4) == true);
  assert(BST.Insert(5) == true);
  assert(BST.Insert(2) == true);
  assert(BST.Insert(3) == true);
  assert(BST.Insert(1) == true);
  assert(BST.Empty() == false);
  assert(BST.Erase(0) == false);
  assert(BST.Erase(2) == true);
  assert(BST.Erase(2) == false);
  assert(BST.Insert(4) == false);
  // should output 4
  assert(BST.GetRootVal() == 4);
  cout << BST.GetRootVal() << endl;
  assert(BST.Erase(4) == true);
  // should output 5
  assert(BST.GetRootVal() == 5);
  cout << BST.GetRootVal() << endl;
  assert(BST.Erase(5) == true);
  // should output 3
  assert(BST.GetRootVal() == 3);
  cout << BST.GetRootVal() << endl;
  assert(BST.Erase(3) == true);
  // should output 1
  assert(BST.GetRootVal() == 1);
  cout << BST.GetRootVal() << endl;
  assert(BST.Erase(1) == true);
  assert(BST.Empty() == true);
  return 0;
}
