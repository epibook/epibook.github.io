# @include
class Stack:

    def __init__(self):
        self.__element_with_cached_max = []

    def empty(self):
        return len(self.__element_with_cached_max) == 0

    def max(self):
        if not self.empty():
            return self.__element_with_cached_max[-1][1]
        raise IndexError('max(): empty stack')

    def pop(self):
        if self.empty():
            raise IndexError('pop(): empty stack')
        return self.__element_with_cached_max.pop()[0]

    def push(self, x):
        self.__element_with_cached_max.append(
            (x, max(x, x if self.empty() else self.__element_with_cached_max[-1][1])))
# @exclude


def main():
    s = Stack()
    s.push(1)
    s.push(2)
    assert s.max() == 2
    print(s.max())  # 2
    print(s.pop())  # 2
    assert s.max() == 1
    print(s.max())  # 1
    s.push(3)
    s.push(2)
    assert s.max() == 3
    print(s.max())  # 3
    s.pop()
    assert s.max() == 3
    print(s.max())  # 3
    s.pop()
    assert s.max() == 1
    print(s.max())  # 1
    s.pop()
    try:
        s.max()
        s.pop()
        s.pop()
        s.pop()
        s.pop()
    except IndexError as e:
        print(e)


if __name__ == '__main__':
    main()
