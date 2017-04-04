import collections
import sys
import random


# @include
def examine_buildings_with_sunset(it):
    BuildingWithHeight = collections.namedtuple('BuildingWithHeight',
                                                ('id', 'height'))
    candidates = []
    for building_idx, building_height in enumerate(it):
        while candidates and building_height >= candidates[-1].height:
            candidates.pop()
        candidates.append(BuildingWithHeight(building_idx, building_height))
    return [candidate.id for candidate in reversed(candidates)]
# @exclude


def main():
    for _ in range(1000):
        n = int(sys.argv[1]) if len(sys.argv) == 2 else random.randint(1, 10000)
        heights = [random.randint(1, 2 * n) for i in range(n)]
        res = examine_buildings_with_sunset(iter(heights))
        print(res[0], heights[res[0]])
        for i in range(1, len(res)):
            print(res[i], heights[res[i]])
            assert heights[res[i - 1]] < heights[res[i]]


if __name__ == '__main__':
    main()
