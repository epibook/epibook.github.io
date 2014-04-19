package com.epi;

import java.util.LinkedList;

import com.epi.BinaryTreePrototypeTemplate.BinaryTree;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class IsBinaryTreeAbstBfs {
  // @include
  public static class QNode<T> {
    public BinaryTree<T> node;
    public T lower, upper;

    public QNode(BinaryTree<T> node, T lower, T upper) {
      this.node = node;
      this.lower = lower;
      this.upper = upper;
    }
  }

  public static <T extends Comparable<T>> boolean isBst(BinaryTree<T> n, T min,
      T max) {
    LinkedList<QNode<T>> q = new LinkedList<QNode<T>>();
    q.addLast(new QNode<T>(n, min, max));

    while (!q.isEmpty()) {
      if (q.getFirst().node != null) {
        if (q.getFirst().node.getData().compareTo(q.getFirst().lower) < 0
            || q.getFirst().node.getData().compareTo(q.getFirst().upper) > 0) {
          return false;
        }

        q.addLast(new QNode<T>(q.getFirst().node.getLeft(), q.getFirst().lower,
            q.getFirst().node.getData()));
        q.addLast(new QNode<T>(q.getFirst().node.getRight(), q.getFirst().node
            .getData(), q.getFirst().upper));
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
    BinaryTree<Integer> root = new BinaryTree<Integer>(3);
    root.setLeft(new BinaryTree<Integer>(2));
    root.getLeft().setLeft(new BinaryTree<Integer>(1));
    root.setRight(new BinaryTree<Integer>(5));
    root.getRight().setLeft(new BinaryTree<Integer>(4));
    root.getRight().setRight(new BinaryTree<Integer>(6));
    // should output true.
    assert isBst(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    System.out.println(isBst(root, Integer.MIN_VALUE, Integer.MAX_VALUE));
    // 10
    // 2 5
    // 1 4 6
    root.setData(10);
    // should output false.
    assert !isBst(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    System.out.println(isBst(root, Integer.MIN_VALUE, Integer.MAX_VALUE));
    // should output true.
    assert isBst(null, Integer.MIN_VALUE, Integer.MAX_VALUE);
    System.out.println(isBst(null, Integer.MIN_VALUE, Integer.MAX_VALUE));
  }
}
