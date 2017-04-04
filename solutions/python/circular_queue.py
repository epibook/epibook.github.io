# @include
class Queue:
    SCALE_FACTOR = 2

    def __init__(self, capacity):
        self._entries = [None] * capacity
        self._head = self._tail = self._num_queue_elements = 0

    def enqueue(self, x):
        if self._num_queue_elements == len(self._entries):  # Needs to resize.
            # Makes the queue elements appear consecutively.
            self._entries = (
                self._entries[self._head:] + self._entries[:self._head])
            # Resets head and tail.
            self._head, self._tail = 0, self._num_queue_elements
            self._entries += [None] * (len(self._entries) * Queue.SCALE_FACTOR)

        self._entries[self._tail] = x
        self._tail = (self._tail + 1) % len(self._entries)
        self._num_queue_elements += 1

    def dequeue(self):
        if not self._num_queue_elements:
            raise IndexError('empty queue')
        self._num_queue_elements -= 1
        ret = self._entries[self._head]
        self._head = (self._head + 1) % len(self._entries)
        return ret

    def size(self):
        return self._num_queue_elements
# @exclude


def test():
    q = Queue(8)
    q.enqueue(1)
    q.enqueue(2)
    q.enqueue(3)
    q.enqueue(4)
    q.enqueue(5)
    q.enqueue(6)
    q.enqueue(7)
    q.enqueue(8)
    # Now head = 0 tail = 0

    assert 1 == q.dequeue()
    assert 2 == q.dequeue()
    assert 3 == q.dequeue()
    # head = 3 and tail = 0

    q.enqueue(11)
    q.enqueue(12)
    q.enqueue(13)
    # Ok till here. Now head = 3 and tail = 3

    q.enqueue(14)  # now the vector (data) is resized; but the head and tail.
    # (or elements) does not change accordingly.
    q.enqueue(15)
    q.enqueue(16)
    q.enqueue(17)
    q.enqueue(18)
    # The elements starting from head=3 are overwritten!

    assert 4 == q.dequeue()
    assert 5 == q.dequeue()
    assert 6 == q.dequeue()
    assert 7 == q.dequeue()
    assert 8 == q.dequeue()
    assert 11 == q.dequeue()
    assert 12 == q.dequeue()


def main():
    test()
    q = Queue(8)
    q.enqueue(1)
    q.enqueue(2)
    q.enqueue(3)
    assert 1 == q.dequeue()
    q.enqueue(4)
    assert 2 == q.dequeue()
    assert 3 == q.dequeue()
    assert 4 == q.dequeue()
    try:
        q.dequeue()
    except IndexError as e:
        print(e)
    # test resize.
    q.enqueue(4)
    q.enqueue(4)
    q.enqueue(4)
    q.enqueue(4)
    q.enqueue(4)
    q.enqueue(4)
    q.enqueue(4)
    q.enqueue(4)
    q.enqueue(4)
    assert q.size() == 9


if __name__ == '__main__':
    main()
