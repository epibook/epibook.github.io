package com.epi;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class UpdateBST {
  // @include
  public static class BinarySearchTree {
    private static class TreeNode {
      public Integer data;
      public TreeNode left, right;

      public TreeNode(Integer data, TreeNode left, TreeNode right) {
        this.data = data;
        this.left = left;
        this.right = right;
      }
    }

    private TreeNode root;

    public boolean empty() {
      return root == null;
    }

    public void clear() {
      root = null;
    }

    public boolean insert(Integer key) {
      if (empty()) {
        root = new TreeNode(key, null, null);
      } else {
        TreeNode curr = root;
        TreeNode par = curr;
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
          par.left = new TreeNode(key, null, null);
        } else {
          par.right = new TreeNode(key, null, null);
        }
      }
      return true;
    }

    public boolean erase(Integer key) {
      // Find the node with key.
      TreeNode curr = root;
      TreeNode par = null;
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
        TreeNode rCurr = curr.right;
        TreeNode rPar = curr;
        while (rCurr.left != null) {
          rPar = rCurr;
          rCurr = rCurr.left;
        }
        // Move links to erase the node.
        rCurr.left = curr.left;
        curr.left = null;
        TreeNode rCurrRight = rCurr.right;
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
    private void replaceParentChildLink(TreeNode par, TreeNode child,
                                        TreeNode newLink) {
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
    public Integer getRootVal() {
      return root.data;
    }
    // @include
  }
  // @exclude

  public static void main(String[] args) {
    BinarySearchTree bst = new BinarySearchTree();
    assert (bst.empty());
    assert (bst.insert(4));
    assert (bst.insert(5));
    assert (bst.insert(2));
    assert (bst.insert(3));
    assert (bst.insert(1));
    assert (!bst.empty());
    assert (!bst.erase(0));
    assert (bst.erase(2));
    assert (!bst.erase(2));
    assert (!bst.insert(4));
    // should output 4
    assert (bst.getRootVal() == 4);
    System.out.println(bst.getRootVal());
    assert (bst.erase(4));
    // should output 5
    assert (bst.getRootVal() == 5);
    System.out.println(bst.getRootVal());
    assert (bst.erase(5));
    // should output 3
    assert (bst.getRootVal() == 3);
    System.out.println(bst.getRootVal());
    assert (bst.erase(3));
    // should output 1
    assert (bst.getRootVal() == 1);
    System.out.println(bst.getRootVal());
    assert (bst.erase(1));
    assert (bst.empty());
  }
}
