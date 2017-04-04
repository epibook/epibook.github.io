import collections
import sys
import random

# @include
PairedTasks = collections.namedtuple('PairedTasks', ('task_1', 'task_2'))


def optimum_task_assignment(task_durations):
    task_durations.sort()
    return [PairedTasks(task_durations[i], task_durations[~i])
            for i in range(len(task_durations) // 2)]
# @exclude


def main():
    n = int(sys.argv[1]) if len(sys.argv) == 2 else random.randint(1, 10000)
    A = [random.randrange(100) for i in range(n)]
    P = optimum_task_assignment(A)
    max = float('-inf')
    for p in P:
        if p[0] + p[1] > max:
            max = p[0] + p[1]
    print(max)


if __name__ == '__main__':
    main()
