package com.epi;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class UpdateBST {
  // @include
  public static class BinarySearchTree<T extends Comparable<T>> {
    private static class TreeNode<T> {
      public T data;
      public TreeNode<T> left, right;

      public TreeNode(T data, TreeNode<T> left, TreeNode<T> right) {
        this.data = data;
        this.left = left;
        this.right = right;
      }
    }

    private TreeNode<T> root;

    public boolean empty() {
      return root == null;
    }

    public void clear() {
      root = null;
    }

    public boolean insert(T key) {
      if (empty()) {
        root = new TreeNode<T>(key, null, null);
      } else {
        TreeNode<T> curr = root;
        TreeNode<T> par = curr;
        while (curr != null) {
          par = curr;
          if (key.compareTo(curr.data) == 0) {
            return false; // no insertion for duplicate key.
          } else if (key.compareTo(curr.data) < 0) {
            curr = curr.left;
          } else { // key.compareTo(curr.data) > 0.
            curr = curr.right;
          }
        }

        // Insert key according to key and par.
        if (key.compareTo(par.data) < 0) {
          par.left = new TreeNode<T>(key, null, null);
        } else {
          par.right = new TreeNode<T>(key, null, null);
        }
      }
      return true;
    }

    public boolean erase(T key) {
      // Find the node with key.
      TreeNode<T> curr = root;
      TreeNode<T> par = null;
      while (curr != null && curr.data.compareTo(key) != 0) {
        par = curr;
        curr = key.compareTo(curr.data) < 0 ? curr.left : curr.right;
      }

      // No node with key in this binary tree.
      if (curr == null) {
        return false;
      }

      if (curr.right != null) {
        // Find the minimum of the right subtree.
        TreeNode<T> rCurr = curr.right;
        TreeNode<T> rPar = curr;
        while (rCurr.left != null) {
          rPar = rCurr;
          rCurr = rCurr.left;
        }
        // Move links to erase the node.
        rCurr.left = curr.left;
        curr.left = null;
        TreeNode<T> rCurrRight = rCurr.right;
        rCurr.right = null;
        if (curr.right != rCurr) {
          rCurr.right = curr.right;
          curr.right = null;
        }
        if (rPar.left == rCurr) {
          rCurr = rPar.left;
          rPar.left = null;
          rPar.left = rCurrRight;
        } else { // rPar.left != rCurr.
          rCurr = rPar.right;
          rPar.right = rCurrRight;
        }
        replaceParentChildLink(par, curr, rCurr);

        // Update root_ link if needed.
        if (root == curr) {
          root = rCurr;
        }
      } else {
        // Update root_ link if needed.
        if (root == curr) {
          root = curr.left;
          curr.left = null;
        }
        replaceParentChildLink(par, curr, curr.left);
      }
      return true;
    }

    // Replace the link between par and child by new_link.
    private void replaceParentChildLink(TreeNode<T> par, TreeNode<T> child,
        TreeNode<T> newLink) {
      if (par == null) {
        return;
      }

      if (par.left == child) {
        par.left = newLink;
      } else { // par->right.get() == child.
        par.right = newLink;
      }
    }

    // @exclude
    public T getRootVal() {
      return root.data;
    }
    // @include
  }

  // @exclude

  public static void main(String[] args) {
    BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
    assert (bst.empty() == true);
    assert (bst.insert(4) == true);
    assert (bst.insert(5) == true);
    assert (bst.insert(2) == true);
    assert (bst.insert(3) == true);
    assert (bst.insert(1) == true);
    assert (bst.empty() == false);
    assert (bst.erase(0) == false);
    assert (bst.erase(2) == true);
    assert (bst.erase(2) == false);
    assert (bst.insert(4) == false);
    // should output 4
    assert (bst.getRootVal() == 4);
    System.out.println(bst.getRootVal());
    assert (bst.erase(4) == true);
    // should output 5
    assert (bst.getRootVal() == 5);
    System.out.println(bst.getRootVal());
    assert (bst.erase(5) == true);
    // should output 3
    assert (bst.getRootVal() == 3);
    System.out.println(bst.getRootVal());
    assert (bst.erase(3) == true);
    // should output 1
    assert (bst.getRootVal() == 1);
    System.out.println(bst.getRootVal());
    assert (bst.erase(1) == true);
    assert (bst.empty() == true);
  }
}
