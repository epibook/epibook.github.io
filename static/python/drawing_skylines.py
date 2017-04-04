import sys
import random


# @include
class Rectangle:

    def __init__(self, left, right, height):
        self.left = left
        self.right = right
        self.height = height
# @exclude

    def __repr__(self):
        return '%d..%d:%d' % (self.left, self.right, self.height)


# @include


def compute_skyline(buildings):
    return compute_skyline_in_interval(buildings, 0, len(buildings))


def compute_skyline_in_interval(buildings, left_endpoint, right_endpoint):
    if right_endpoint - left_endpoint <= 1:  # 0 or 1 skyline, just copy it.
        return buildings[left_endpoint:right_endpoint]
    mid = left_endpoint + ((right_endpoint - left_endpoint) // 2)
    left_skyline = compute_skyline_in_interval(buildings, left_endpoint, mid)
    right_skyline = compute_skyline_in_interval(buildings, mid, right_endpoint)
    return merge_skylines(left_skyline, right_skyline)


def merge_skylines(left_skyline, right_skyline):
    i = j = 0
    merged = []

    while i < len(left_skyline) and j < len(right_skyline):
        if left_skyline[i].right < right_skyline[j].left:
            merged.append(left_skyline[i])
            i += 1
        elif right_skyline[j].right < left_skyline[i].left:
            merged.append(right_skyline[j])
            j += 1
        elif left_skyline[i].left <= right_skyline[j].left:
            i, j = merge_intersect_skylines(merged, left_skyline[i], i,
                                            right_skyline[j], j)
        else:  # left_skyline[i].left > right_skyline[j].left.
            j, i = merge_intersect_skylines(merged, right_skyline[j], j,
                                            left_skyline[i], i)

    merged += left_skyline[i:]
    merged += right_skyline[j:]
    return merged


def merge_intersect_skylines(merged, a, a_idx, b, b_idx):
    if a.right <= b.right:
        if a.height > b.height:
            if b.right != a.right:
                merged.append(a)
                a_idx += 1
                b.left = a.right
            else:
                b_idx += 1
        elif a.height == b.height:
            b.left = a.left
            a_idx += 1
        else:  # a.height < b.height.
            if a.left != b.left:
                merged.append(Rectangle(a.left, b.left, a.height))
            a_idx += 1
    else:  # a.right > b.right.
        if a.height >= b.height:
            b_idx += 1
        else:
            if a.left != b.left:
                merged.append(Rectangle(a.left, b.left, a.height))
            a.left = b.right
            merged.append(b)
            b_idx += 1
    return a_idx, b_idx
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
        if len(sys.argv) == 2:
            n = int(sys.argv[1])
        else:
            n = random.randint(1, 5000)
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
