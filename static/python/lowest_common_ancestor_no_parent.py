from binary_tree_prototype import BinaryTreeNode
import collections


# @include
def lca(tree, node0, node1):
    Status = collections.namedtuple('Status', ('num_target_nodes', 'ancestor'))

    # Returns an object consisting of an int and a node. The int field is 0,
    # 1, or 2 depending on how many of {node0, node1} are present in tree. If
    # both are present in tree, when ancestor is assigned to a non-null value,
    # it is the LCA.
    def lca_helper(tree, node0, node1):
        if not tree:
            return Status(0, None)

        left_result = lca_helper(tree.left, node0, node1)
        if left_result.num_target_nodes == 2:
            # Found both nodes in the left subtree.
            return left_result
        right_result = lca_helper(tree.right, node0, node1)
        if right_result.num_target_nodes == 2:
            # Found both nodes in the right subtree.
            return right_result
        num_target_nodes = (
            left_result.num_target_nodes + right_result.num_target_nodes + int(
                tree in (node0, node1)))
        return Status(num_target_nodes, tree if num_target_nodes == 2 else None)

    return lca_helper(tree, node0, node1).ancestor
# @exclude


def main():
    #      3
    #    2   5
    #  1    4 6
    tree = BinaryTreeNode(3)
    tree.left = BinaryTreeNode(2)
    tree.left.left = BinaryTreeNode(1)
    tree.right = BinaryTreeNode(5)
    tree.right.left = BinaryTreeNode(4)
    tree.right.right = BinaryTreeNode(6)
    # should output 3
    x = lca(tree, tree.left, tree.right)
    assert x.data == 3
    print(x.data)
    # should output 5
    x = lca(tree, tree.right.left, tree.right.right)
    assert x.data == 5
    print(x.data)
    # should output 5
    x = lca(tree, tree.right, tree.right.right)
    assert x.data == 5
    print(x.data)
    # should output 3
    x = lca(tree, tree, tree.left.left)
    assert x.data == 3
    print(x.data)
    # should output 2
    x = lca(tree, tree.left, tree.left.left)
    assert x.data == 2
    print(x.data)


if __name__ == '__main__':
    main()
