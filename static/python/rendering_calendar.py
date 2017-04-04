import collections
import sys
import random

# @include
# Event is a tuple (start_time, end_time)
Event = collections.namedtuple('Event', ('start', 'finish'))

# Endpoint is a tuple (start_time, 0) or (end_time, 1) so that if times
# are equal, start_time comes first
Endpoint = collections.namedtuple('Endpoint', ('time', 'is_start'))


def find_max_simultaneous_events(A):
    # Builds an array of all endpoints.
    E = ([Endpoint(event.start, True) for event in A] +
         [Endpoint(event.finish, False) for event in A])
    # Sorts the endpoint array according to the time, breaking ties by putting
    # start times before end times.
    E.sort(key=lambda e: (e.time, not e.is_start))

    # Track the number of simultaneous events, record the maximum number of
    # simultaneous events.
    max_num_simultaneous_events, num_simultaneous_events = 0, 0
    for e in E:
        if e.is_start:
            num_simultaneous_events += 1
            max_num_simultaneous_events = max(num_simultaneous_events,
                                              max_num_simultaneous_events)
        else:
            num_simultaneous_events -= 1
    return max_num_simultaneous_events
# @exclude


def simple_test():
    A = [
        Event(1, 5), Event(2, 7), Event(4, 5), Event(6, 10), Event(8, 9),
        Event(9, 17), Event(11, 13), Event(12, 15), Event(14, 15)
    ]
    assert find_max_simultaneous_events(A) == 3

    A = [
        Event(1, 5), Event(2, 7), Event(4, 5), Event(6, 10), Event(8, 9),
        Event(9, 17), Event(11, 13), Event(12, 15), Event(14, 15), Event(9, 10)
    ]
    assert find_max_simultaneous_events(A) == 4


def main():
    simple_test()
    n = int(sys.argv[1]) if len(sys.argv) == 2 else random.randint(1, 100000)

    A = []
    for _ in range(n):
        temp_start = random.randint(0, 99999)
        temp_finish = random.randint(temp_start + 1, temp_start + 10000)
        A.append(Event(temp_start, temp_finish))

    ans = find_max_simultaneous_events(A)
    print(ans)


if __name__ == '__main__':
    main()
