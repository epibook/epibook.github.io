import collections
import sys
import random

# @include
Rectangle = collections.namedtuple('Rectangle', ('left', 'right', 'height'))


def compute_skyline(buildings):
    min_left = min(buildings, key=lambda b: b.left).left
    max_right = max(buildings, key=lambda b: b.right).right

    heights = [0] * (max_right - min_left + 1)
    for building in buildings:
        for i in range(building.left, building.right + 1):
            heights[i - min_left] = max(heights[i - min_left], building.height)

    result = []
    left = 0
    for i in range(1, len(heights)):
        if heights[i] != heights[i - 1]:
            result.append(
                Rectangle(left + min_left, i - 1 + min_left, heights[i - 1]))
            left = i
    return result + [Rectangle(left + min_left, max_right, heights[-1])]
# @exclude


def check_answer(ans):
    # Just check there is no overlap.
    for i in range(len(ans)):
        assert ans[i].left <= ans[i].right
        if i > 0:
            assert ans[i - 1].right <= ans[i].left
            assert ans[i -
                       1].right != ans[i].left or ans[i -
                                                      1].height != ans[i].height


def main():
    for _ in range(2000):
        n = int(sys.argv[1]) if len(sys.argv) == 2 else random.randint(1, 5000)
        A = []
        for i in range(n):
            left = random.randrange(1000)
            right = random.randint(left, left + 200)
            height = random.randrange(100)
            A.append(Rectangle(left, right, height))
        ans = compute_skyline(A)
        print('n =', n)
        check_answer(ans)


if __name__ == '__main__':
    main()
