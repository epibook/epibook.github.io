# @include
def compute_top_k_variance(students, scores, k):
    '''
    students and scores are equal length lists of strings and floats,
    respectively. The function computes for each string that appears at least
    k times in the list the variance of the top k scores that correspond to it.
    Strings that appear fewer than k times are not considered.
    '''
    counts = {}
    for i in range(len(students)):
        if students[i] not in counts:
            counts[students[i]] = 1
        else:
            counts[students[i]] += 1

    all_scores = {}
    for key in counts:
        if counts[key] >= k:
            all_scores[key] = []

    for i in range(len(students)):
        if students[i] in all_scores:
            all_scores[students[i]].append(scores[i])

    top_k_scores = {}
    for key in all_scores:
        sorted_scores = sorted(all_scores[key])
        top_k_scores[key] = []
        for i in range(k):
            top_k_scores[key].append(sorted_scores[len(sorted_scores) - 1 - i])

    result = {}
    for key in top_k_scores:
        total = 0
        for score in top_k_scores[key]:
            total += score
        mean = total / k
        variance = 0
        for score in top_k_scores[key]:
            variance = variance + (score - mean) * (score - mean)
        result[key] = variance

    return result
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
        'a': 50.0, 'b': 0.0, 'c': 1250.0}

if __name__ == '__main__':
    main()
