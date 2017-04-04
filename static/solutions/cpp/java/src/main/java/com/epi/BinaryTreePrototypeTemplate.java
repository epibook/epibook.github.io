package com.epi;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class BinaryTreePrototypeTemplate {
  // @include
  public static class BinaryTreeNode<T> {
    public T data;
    public BinaryTreeNode<T> left, right;
    // @exclude

    public BinaryTreeNode() {}

    public BinaryTreeNode(T data) { this.data = data; }

    public BinaryTreeNode(T data, BinaryTreeNode<T> left,
                          BinaryTreeNode<T> right) {
      this.data = data;
      this.left = left;
      this.right = right;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }

      BinaryTreeNode that = (BinaryTreeNode)o;
      if (data != null ? !data.equals(that.data) : that.data != null) {
        return false;
      }
      if (left != null ? !left.equals(that.left) : that.left != null) {
        return false;
      }
      if (right != null ? !right.equals(that.right) : that.right != null) {
        return false;
      }
      return true;
    }

    // clang-format off
    @Override
    public int hashCode() { return Objects.hash(data, left, right); }
    // clang-format on

    // Based on https://leetcode.com/faq/#binary-tree
    @Override
    public String toString() {
      int nodeId = 0;
      List<Deque<BinaryTreeNode<T>>> levels = new ArrayList<>();

      Deque<BinaryTreeNode<T>> currLevel = new LinkedList<>();
      currLevel.add(this);
      Deque<BinaryTreeNode<T>> nextLevel = new LinkedList<>();
      while (true) {
        levels.add(currLevel);
        for (BinaryTreeNode<T> iter : currLevel) {
          if (iter.left != null) {
            nextLevel.add(iter.left);
          }
          if (iter.right != null) {
            nextLevel.add(iter.right);
          }
        }
        if (nextLevel.isEmpty()) {
          break;
        } else {
          currLevel = nextLevel;
          nextLevel = new LinkedList<>();
        }
      }

      List<String> result = new ArrayList<>();
      result.add(this.data == null ? ("" + nodeId++) : this.data.toString());
      for (int i = 0; i < levels.size() - 1; i++) {
        Deque<BinaryTreeNode<T>> thisLevel = levels.get(i);
        for (BinaryTreeNode<T> node : thisLevel) {
          result.add(node.left != null
                         ? (node.left.data == null ? ("" + nodeId++)
                                                   : node.left.data.toString())
                         : "#");
          result.add(node.right != null ? (node.right.data == null
                                               ? ("" + nodeId++)
                                               : node.right.data.toString())
                                        : "#");
        }
      }

      int numTrailingHashes = 0;
      for (int i = result.size() - 1; i >= 0; i--) {
        if (result.get(i).equals("#")) {
          numTrailingHashes++;
        } else {
          break;
        }
      }

      StringBuilder sb = new StringBuilder();
      sb.append("{");
      for (int i = 0; i < result.size() - numTrailingHashes; i++) {
        sb.append(result.get(i));
        if (i < result.size() - numTrailingHashes - 1) {
          sb.append(",");
        }
      }
      sb.append("}");
      return sb.toString();
    }

    private static void testToString() {
      BinaryTreeNode A = new BinaryTreeNode<>(1);
      A.right = new BinaryTreeNode<>(2);
      A.right.left = new BinaryTreeNode<>(3);
      System.out.println(A);
      assert(A.toString().equals("{1,#,2,3}"));

      BinaryTreeNode<Integer> B = new BinaryTreeNode<>(1);
      B.left = new BinaryTreeNode<>(2);
      B.right = new BinaryTreeNode<>(3);
      B.right.left = new BinaryTreeNode<>(4);
      B.right.left.right = new BinaryTreeNode<>(5);
      System.out.println(B);
      assert(B.toString().equals("{1,2,3,#,#,4,#,#,5}"));

      BinaryTreeNode<Integer> C = new BinaryTreeNode<>(1, new BinaryTreeNode(2),
                                                       new BinaryTreeNode(3));
      System.out.println(C);
      assert(C.toString().equals("{1,2,3}"));

      BinaryTreeNode<Integer> D = new BinaryTreeNode<>(5, new BinaryTreeNode(4),
                                                       new BinaryTreeNode(7));

      D.left.left = new BinaryTreeNode<>(3, new BinaryTreeNode<>(-1), null);

      D.right.left = new BinaryTreeNode<>(2, new BinaryTreeNode<>(9), null);
      System.out.println(D);
      assert(D.toString().equals("{5,4,7,3,#,2,#,-1,#,9}"));
    }
    // @include
  }
  // @exclude
}
