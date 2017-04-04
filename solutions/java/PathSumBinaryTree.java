import com.epi.BinaryTreePrototypeTemplate.BinaryTreeNode;

public class PathSumBinaryTree {
  // @include
  public static boolean hasPathSum(BinaryTreeNode<Integer> tree,
                                   int remainingWeight) {
    if (tree == null) {
      return false;
    } else if (tree.left == null && tree.right == null) { // Leaf.
      return remainingWeight == tree.data;
    }
    // Non-leaf.
    return hasPathSum(tree.left, remainingWeight - tree.data)
        || hasPathSum(tree.right, remainingWeight - tree.data);
  }
  // @exclude

  public static void main(String[] args) {
    // 3
    // 2 5
    // 1 4 6
    BinaryTreeNode<Integer> tree = new BinaryTreeNode<>(3);
    assert(hasPathSum(tree, 3));
    tree.left = new BinaryTreeNode<>(2);
    assert(hasPathSum(tree, 5));
    tree.left.left = new BinaryTreeNode<>(1);
    assert(hasPathSum(tree, 6));
    tree.right = new BinaryTreeNode<>(5);
    assert(hasPathSum(tree, 8));
    assert(!hasPathSum(tree, 7));
    tree.right.left = new BinaryTreeNode<>(4);
    assert(hasPathSum(tree, 12));
    assert(!hasPathSum(tree, 1));
    assert(!hasPathSum(tree, 3));
    assert(!hasPathSum(tree, 5));
    tree.right.right = new BinaryTreeNode<>(6);
    assert(hasPathSum(tree, 6));
    assert(!hasPathSum(tree, 7));
    assert(hasPathSum(tree, 14));
    assert(!hasPathSum(tree, -1));
    assert(!hasPathSum(tree, Integer.MAX_VALUE));
    assert(!hasPathSum(tree, Integer.MIN_VALUE));
  }
}
