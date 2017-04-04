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
    if (interval.left <= tree.data && tree.data <= interval.right) {
      // tree.data lies in the interval.
      rangeLookupInBSTHelper(tree.left, interval, result);
      result.add(tree.data);
      rangeLookupInBSTHelper(tree.right, interval, result);
    } else if (interval.left > tree.data) {
      rangeLookupInBSTHelper(tree.right, interval, result);
    } else { // interval.right >= tree.data
      rangeLookupInBSTHelper(tree.left, interval, result);
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
    tree.left = new BSTNode<>(7);
    tree.left.left = new BSTNode<>(3);
    tree.left.left.left = new BSTNode<>(2);
    tree.left.left.right = new BSTNode<>(5);
    tree.left.right = new BSTNode<>(11);
    tree.left.right.right = new BSTNode<>(17);
    tree.left.right.right.left = new BSTNode<>(13);
    tree.right = new BSTNode<>(43);
    tree.right.left = new BSTNode<>(23);
    tree.right.left.right = new BSTNode<>(37);
    tree.right.left.right.left = new BSTNode<>(29);
    tree.right.left.right.left.right = new BSTNode<>(31);
    tree.right.left.right.right = new BSTNode<>(41);
    tree.right.right = new BSTNode<>(47);
    tree.right.right.right = new BSTNode<>(53);
    Interval interval = new Interval(16, 31);
    List<Integer> result = rangeLookupInBST(tree, interval);
    Collections.sort(result);
    List<Integer> goldenResult = Arrays.asList(17, 19, 23, 29, 31);
    assert result.equals(goldenResult);

    interval = new Interval(38, 39);
    result = rangeLookupInBST(tree, interval);
    assert(0 == result.size());

    interval = new Interval(38, 42);
    result = rangeLookupInBST(tree, interval);
    assert((1 == result.size()) && (41 == result.get(0)));

    interval = new Interval(-1, 1);
    result = rangeLookupInBST(tree, interval);
    assert(0 == result.size());

    interval = new Interval(Integer.MAX_VALUE - 1, Integer.MAX_VALUE);
    result = rangeLookupInBST(tree, interval);
    assert(0 == result.size());
  }
}
