package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTree;

import java.util.LinkedList;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class IsBinaryTreeABSTBFS {
  // @include
  public static class QNode {
    public BinaryTree<Integer> node;
    public Integer lower, upper;

    public QNode(BinaryTree<Integer> node, Integer lower, Integer upper) {
      this.node = node;
      this.lower = lower;
      this.upper = upper;
    }
  }

  public static boolean isBST(BinaryTree<Integer> r) {
    LinkedList<QNode> q = new LinkedList<>();
    q.addLast(new QNode(r, Integer.MIN_VALUE, Integer.MAX_VALUE));

    while (!q.isEmpty()) {
      if (q.getFirst().node != null) {
        if (q.getFirst().node.getData().compareTo(q.getFirst().lower) < 0
            || q.getFirst().node.getData().compareTo(q.getFirst().upper) > 0) {
          return false;
        }

        q.addLast(new QNode(q.getFirst().node.getLeft(), q.getFirst().lower,
            q.getFirst().node.getData()));
        q.addLast(new QNode(q.getFirst().node.getRight(),
            q.getFirst().node .getData(), q.getFirst().upper));
      }
      q.removeFirst();
    }
    return true;
  }
  // @exclude

  public static void main(String[] args) {
    // 3
    // 2 5
    // 1 4 6
    BinaryTree<Integer> root = new BinaryTree<>(3);
    root.setLeft(new BinaryTree<>(2));
    root.getLeft().setLeft(new BinaryTree<>(1));
    root.setRight(new BinaryTree<>(5));
    root.getRight().setLeft(new BinaryTree<>(4));
    root.getRight().setRight(new BinaryTree<>(6));
    // should output true.
    assert isBST(root);
    System.out.println(isBST(root));
    // 10
    // 2 5
    // 1 4 6
    root.setData(10);
    // should output false.
    assert !isBST(root);
    System.out.println(isBST(root));
    // should output true.
    assert isBST(null);
    System.out.println(isBST(null));
  }
}
