import sys
import operator
import collections
import random
import string
import heapq


# @include
def find_student_with_highest_best_of_three_scores(name_score_data):
    student_scores = collections.defaultdict(list)
    for line in name_score_data:
        name, score = line.split()
        if len(student_scores[name]) < 3:
            heapq.heappush(student_scores[name], int(score))
        else:
            heapq.heappushpop(student_scores[name], int(score))
    return max([(sum(scores), name) for name, scores in student_scores.items()
                if len(scores) == 3],
               key=operator.itemgetter(0),
               default='no such student')[1]
# @exclude


def rand_string(length):
    return ''.join(random.choice(string.ascii_lowercase) for _ in range(length))


def simple_test():
    with open('scores.txt', 'w') as ofs:
        ofs.write('''adnan 100
amit 99
adnan 98
thl 90
adnan 10
amit 100
thl 99
thl 95
dd 100
dd 100
adnan 95''')
    with open('scores.txt') as name_score_data:
        result = find_student_with_highest_best_of_three_scores(name_score_data)
    print('result =', result)
    assert result == 'adnan'


def main():
    simple_test()
    n = int(sys.argv[1]) if len(sys.argv) == 2 else random.randint(1, 10000)

    with open('scores.txt', 'w') as ofs:
        for i in range(n):
            test_num = random.randint(0, 20)
            name = rand_string(random.randint(5, 10))
            for _ in range(test_num):
                print(name, random.randint(0, 100), file=ofs)
    with open('scores.txt') as name_score_data:
        name = find_student_with_highest_best_of_three_scores(name_score_data)
        name_score_data.seek(0)
    print('top student is', name)


if __name__ == '__main__':
    main()
