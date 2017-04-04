import heapq


# @include
class Stack:

    def __init__(self):
        self._timestamp = 0
        self._max_heap = []

    def push(self, x):
        heapq.heappush(self._max_heap, (-self._timestamp, x))
        self._timestamp += 1

    def pop(self):
        if not self._max_heap:
            raise IndexError('empty stack')
        return heapq.heappop(self._max_heap)[1]

    def peek(self):
        return self._max_heap[0][1]
# @exclude


class Queue:

    def __init__(self):
        self._timestamp = 0
        self._min_heap = []

    def enqueue(self, x):
        heapq.heappush(self._min_heap, (self._timestamp, x))
        self._timestamp += 1

    def dequeue(self):
        if not self._min_heap:
            raise IndexError('empty queue')
        return heapq.heappop(self._min_heap)[1]

    def head(self):
        return self._min_heap[0][1]


def main():
    s = Stack()
    s.push(1)
    s.push(2)
    s.push(3)
    assert s.peek() == 3
    s.pop()
    assert s.peek() == 2
    s.pop()
    s.push(4)
    assert s.peek() == 4
    s.pop()
    s.pop()
    try:
        s.pop()
        assert False
    except IndexError as e:
        print('empty stack')
        print(e)

    s.push(0)
    s.push(-1)
    s.push(float('inf'))
    assert s.peek() == float('inf')
    s.pop()
    assert s.peek() == -1
    s.pop()
    assert s.peek() == 0
    s.pop()
    try:
        s.pop()
        assert False
    except IndexError as e:
        print('empty stack')
        print(e)
    s.push(0)
    assert s.peek() == 0

    q = Queue()
    q.enqueue(1)
    q.enqueue(2)
    assert q.head() == 1
    q.dequeue()
    assert q.head() == 2
    q.dequeue()
    try:
        q.dequeue()
        assert False
    except IndexError as e:
        print('empty queue')
        print(e)


if __name__ == '__main__':
    main()
