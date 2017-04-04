import sys
import time
import concurrent.futures


# @include
# Performs basic unit of work
def worker(lower, upper):
    for i in range(lower, upper + 1):
        assert collatz_check(i, set())
    print('(%d,%d)' % (lower, upper))


# @exclude


# @include
# Checks an individual number
def collatz_check(x, visited):
    if x == 1:
        return True
    elif x in visited:
        return False
    visited.add(x)
    if x & 1:  # odd number
        return collatz_check(3 * x + 1, visited)
    else:  # even number
        return collatz_check(x >> 1, visited)  # divide by 2
# @exclude


def main():
    N = 10000000
    RANGESIZE = 1000000
    NTHREADS = 4
    if len(sys.argv) > 1:
        N = int(sys.argv[1])
    if len(sys.argv) > 2:
        RANGESIZE = int(sys.argv[2])
    if len(sys.argv) > 3:
        NTHREADS = int(sys.argv[3])

    assert collatz_check(1, set())
    assert collatz_check(3, set())
    assert collatz_check(8, set())
    start_time = time.time()

    # @include
    # Uses the library thread pool for task assignment and load balancing
    executor = concurrent.futures.ProcessPoolExecutor(max_workers=NTHREADS)
    with executor:
        for i in range(N // RANGESIZE):
            executor.submit(worker, i * RANGESIZE + 1, (i + 1) * RANGESIZE)
# @exclude
    print('Finished all threads')
    running_time = (time.time() - start_time) * 1000
    print('time in milliseconds for checking to %d is %d (%d per ms)' %
          (N, running_time, N / running_time))

if __name__ == '__main__':
    main()
