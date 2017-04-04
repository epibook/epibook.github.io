# Celebrity_finding.cpp 848813e190b1b85a8e75107fe8513c3be38ad1a9
import sys
import random


# @include
def celebrity_finding(F):
    # Start checking the relation from F[0][1].
    i = 0
    j = 1
    for j in range(len(F)):
        if F[i][j]:
            i = j  # All candidates j' < j are not celebrity candidates.
        # if F[i][j] == False i is still a celebrity candidate but j is not.
    return i


# @exclude


def main():
    for _ in range(1000):
        if len(sys.argv) > 1:
            n = int(sys.argv[1])
        else:
            n = random.randint(1, 1000)
        graph = []
        for i in range(n):
            graph.append([bool(random.randint(0, 1)) for j in range(n)])
            graph[i][i] = False
        celebrity = random.randrange(n)
        for i in graph:
            i[celebrity] = True
        graph[celebrity] = [False] * n
        print(celebrity_finding(graph))
        assert celebrity == celebrity_finding(graph)


if __name__ == '__main__':
    main()
