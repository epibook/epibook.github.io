import collections
import functools
import heapq


# @include
def compute_top_k_variance(students, scores, k):
    all_scores = collections.defaultdict(list)
    for student, score in zip(students, scores):
        all_scores[student].append(score)

    top_k_scores = {
        student: heapq.nlargest(k, scores)
        for student, scores in all_scores.items() if len(scores) >= k
    }

    return {
        student: functools.reduce(
            lambda variance, score: variance + (score - mean)**2, scores, 0)
        for student, scores, mean in ((student, scores, sum(scores) / k) for
                                      student, scores in top_k_scores.items())
    }
# @exclude


def main():
    students = ['a', 'b', 'c'] * 2 + ['a', 'b']
    scores = [100, 100, 100, 90, 100, 100, 80, 100]
    k = 3
    assert compute_top_k_variance(students, scores, k) == {'a': 200.0, 'b': 0.0}
    students = ['a', 'b', 'c'] * 2 + ['a', 'b']
    scores = [100, 100, 0, 90, 100, 50, 80, 100]
    k = 2
    assert compute_top_k_variance(students, scores, k) == {
        'a': 50.0, 'b': 0.0, 'c': 1250.0
    }


if __name__ == '__main__':
    main()
