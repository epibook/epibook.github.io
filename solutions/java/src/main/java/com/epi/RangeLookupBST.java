package com.epi;

import com.epi.BinarySearchTreePrototypeTemplate.BSTNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RangeLookupBST {
  // @include
  private static class Interval {
    public int left, right;

    public Interval(int left, int right) {
      this.left = left;
      this.right = right;
    }
  }

  public static List<Integer> rangeLookupInBST(BSTNode<Integer> tree,
                                             Interval interval) {
    List<Integer> result = new ArrayList<>();
    rangeLookupInBSTHelper(tree, interval, result);
    return result;
  }

  public static void rangeLookupInBSTHelper(BSTNode<Integer> tree,
                                          Interval interval,
                                          List<Integer> result) {
    if (tree == null) {
      return;
    }
    if (interval.left <= tree.getData() && tree.getData() <= interval.right) {
      // tree.getData() lies in the interval.
      rangeLookupInBSTHelper(tree.getLeft(), interval, result);
      result.add(tree.getData());
      rangeLookupInBSTHelper(tree.getRight(), interval, result);
    } else if (interval.left > tree.getData()) {
      rangeLookupInBSTHelper(tree.getRight(), interval, result);
    } else { // interval.right >= tree.getData()
      rangeLookupInBSTHelper(tree.getLeft(), interval, result);
    }
  }
  // @exclude

  public static void main(String[] args) {
    //          19
    //     7          43
    //   3   11    23   47
    // 2  5    17   37    53
    //        13  29  41
    //             31
    BSTNode<Integer> tree = new BSTNode<>(19);
    tree.setLeft(new BSTNode<>(7));
    tree.getLeft().setLeft(new BSTNode<>(3));
    tree.getLeft().getLeft().setLeft(new BSTNode<>(2));
    tree.getLeft().getLeft().setRight(new BSTNode<>(5));
    tree.getLeft().setRight(new BSTNode<>(11));
    tree.getLeft().getRight().setRight(new BSTNode<>(17));
    tree.getLeft().getRight().getRight().setLeft(new BSTNode<>(13));
    tree.setRight(new BSTNode<>(43));
    tree.getRight().setLeft(new BSTNode<>(23));
    tree.getRight().getLeft().setRight(new BSTNode<>(37));
    tree.getRight().getLeft().getRight().setLeft(new BSTNode<>(29));
    tree.getRight().getLeft().getRight().getLeft().setRight(new BSTNode<>(31));
    tree.getRight().getLeft().getRight().setRight(new BSTNode<>(41));
    tree.getRight().setRight(new BSTNode<>(47));
    tree.getRight().getRight().setRight(new BSTNode<>(53));
    Interval interval = new Interval(16, 31);
    List<Integer> result = rangeLookupInBST(tree, interval);
    Collections.sort(result);
    List<Integer> goldenResult = Arrays.asList(17, 19, 23, 29, 31);
    assert result.equals(goldenResult);

    interval = new Interval(38, 39);
    result = rangeLookupInBST(tree, interval);
    assert (0 == result.size());

    interval = new Interval(38, 42);
    result = rangeLookupInBST(tree, interval);
    assert((1 == result.size()) && (41 == result.get(0)));

    interval = new Interval(-1, 1);
    result = rangeLookupInBST(tree, interval);
    assert(0 == result.size());

    interval = new Interval(Integer.MAX_VALUE-1, Integer.MAX_VALUE);
    result = rangeLookupInBST(tree, interval);
    assert(0 == result.size());
  }
}
