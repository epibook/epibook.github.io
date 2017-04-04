import sys
import random


# @include
def minimum_total_waiting_time(service_times):
    # Sort the service times in increasing order.
    service_times.sort()
    total_waiting_time = 0
    for i, service_time in enumerate(service_times):
        num_remaining_queries = len(service_times) - (i + 1)
        total_waiting_time += service_time * num_remaining_queries
    return total_waiting_time
# @exclude


def minimum_total_waiting_time_pythonic(service_times):
    return sum(
        remaining_queries *
        time for remaining_queries,
        time in enumerate(
            sorted(service_times)[
                ::-
                1]))


def small_test():
    assert 10 == minimum_total_waiting_time([5, 1, 2, 3])


def main():
    small_test()
    if len(sys.argv) == 2:
        n = int(sys.argv[1])
    else:
        n = random.randint(1, 100)
    waiting_time = [random.randrange(1000) for i in range(n)]
    print(*waiting_time)
    total_time = minimum_total_waiting_time(waiting_time)
    print(total_time)
    assert total_time == minimum_total_waiting_time_pythonic(waiting_time)


if __name__ == '__main__':
    main()
