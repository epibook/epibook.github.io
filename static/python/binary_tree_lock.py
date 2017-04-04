# @include
class BinaryTreeNode:
    def __init__(self):
        self.left = self.right = self.parent = None
        self._locked, self._num_locked_descendants = False, 0

    def is_locked(self):
        return self._locked

    def lock(self):
        # We cannot lock if any of this node's descendants are locked.
        if self._num_locked_descendants > 0 or self._locked:
            return False

        # We cannot lock if any of this node's ancestors are locked.
        it = self.parent
        while it:
            if it._locked:
                return False
            it = it.parent

        # Lock this node and increments all its ancestors' descendant counts.
        self._locked = True
        it = self.parent
        while it:
            it._num_locked_descendants += 1
            it = it.parent
        return True

    def unlock(self):
        if self._locked:
            # Unlocks itself and decrements its ancestors' lock counts.
            self._locked = False
            it = self.parent
            while it:
                it._num_locked_descendants -= 1
                it = it.parent
# @exclude


def main():
    root = BinaryTreeNode()
    root.left = BinaryTreeNode()
    root.left.parent = root
    root.right = BinaryTreeNode()
    root.right.parent = root
    root.left.left = BinaryTreeNode()
    root.left.left.parent = root.left
    root.left.right = BinaryTreeNode()
    root.left.right.parent = root.left
    # Should output False.
    assert not root.is_locked()
    print(root.is_locked())
    assert root.lock()
    # Should output True.
    assert root.is_locked()
    assert not root.left.lock()
    print(root.is_locked())
    root.unlock()
    assert root.left.lock()
    assert not root.lock()
    # Should output False.
    assert not root.is_locked()
    print(root.is_locked())
    assert root.right.lock()
    # Should output True.
    assert root.right.is_locked()
    print(root.right.is_locked())
    root.left.unlock()
    assert not root.lock()
    root.right.unlock()
    assert root.lock()


if __name__ == '__main__':
    main()
