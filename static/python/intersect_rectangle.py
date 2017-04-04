import sys
import random
import collections

# @include
Rectangle = collections.namedtuple('Rectangle', ('x', 'y', 'width', 'height'))


def intersect_rectangle(R1, R2):
    def is_intersect(R1, R2):
        return (R1.x <= R2.x + R2.width and R1.x + R1.width >= R2.x and
                R1.y <= R2.y + R2.height and R1.y + R1.height >= R2.y)

    if not is_intersect(R1, R2):
        return Rectangle(0, 0, -1, -1)  # No intersection.
    return Rectangle(
        max(R1.x, R2.x),
        max(R1.y, R2.y),
        min(R1.x + R1.width, R2.x + R2.width) - max(R1.x, R2.x),
        min(R1.y + R1.height, R2.y + R2.height) - max(R1.y, R2.y))
# @exclude


def small_test():
    R1, R2 = Rectangle(0, 0, 2, 2), Rectangle(1, 1, 3, 3)
    assert intersect_rectangle(R1, R2) == Rectangle(1, 1, 1, 1)
    R1, R2 = Rectangle(0, 0, 1, 1), Rectangle(1, 1, 3, 3)
    assert intersect_rectangle(R1, R2) == Rectangle(1, 1, 0, 0)
    R1, R2 = Rectangle(0, 0, 1, 1), Rectangle(2, 2, 3, 3)
    assert intersect_rectangle(R1, R2) == Rectangle(0, 0, -1, -1)


def main():
    small_test()
    for _ in range(10000):
        if len(sys.argv) == 9:
            R1 = Rectangle(*map(int, sys.argv[1:5]))
            R2 = Rectangle(*map(int, sys.argv[5:9]))
        else:
            R1 = Rectangle(*(random.randint(1, 100) for i in range(4)))
            R2 = Rectangle(*(random.randint(1, 100) for i in range(4)))

        # Intersect rectangle.
        def is_intersect(R1, R2):
            return (R1.x <= R2.x + R2.width and R1.x + R1.width >= R2.x and
                    R1.y <= R2.y + R2.height and R1.y + R1.height >= R2.y)

        res = is_intersect(R1, R2)
        print(res)
        ans = intersect_rectangle(R1, R2)
        print('ans:', ans)
        assert res == False or (ans.width >= 0 and ans.height >= 0)


if __name__ == '__main__':
    main()
