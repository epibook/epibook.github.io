# Rendering_calendar.cpp 4a4c5f91493e5e482eaa79892816c1ccefa084f4
import sys
import random


# @include
# interval is a tuple (start_time, end_time)
# endpoint is a tuple (start_time, 0) or (end_time, 1)
#   so that if times are equal, start_time comes first
def find_max_simultaneous_events(A):
    # Builds an array of all endpoints.
    E = []
    for i in A:
        E.append((i[0], 0))
        E.append((i[1], 1))

    # Sorts the endpoint array according to the time, ties
    # by putting start times before end times.
    E.sort()

    # Track the number of simultaneous events, record the maximum
    # number of simultaneous events.
    max_num_simultaneous_events = 0
    num_simultaneous_events = 0
    for e in E:
        if e[1] == 0:  # start_time
            num_simultaneous_events += 1
            max_num_simultaneous_events = max(
                num_simultaneous_events, max_num_simultaneous_events)
        else:  # end_time
            num_simultaneous_events -= 1

    return max_num_simultaneous_events
# @exclude


def simple_test():
    A = [(1, 5), (2, 7), (4, 5), (6, 10), (8, 9), (9, 17), (11, 13), (12, 15), (14, 15)]
    assert find_max_simultaneous_events(A) == 3


def main():
    simple_test()
    if len(sys.argv) == 2:
        n = int(sys.argv[1])
    else:
        n = random.randint(1, 100000)

    A = []
    for i in range(n):
        temp_start = random.randint(0, 99999)
        temp_finish = random.randint(temp_start + 1, temp_start + 10000)
        A.append((temp_start, temp_finish))

    ans = find_max_simultaneous_events(A)
    print(ans)


if __name__ == '__main__':
    main()
