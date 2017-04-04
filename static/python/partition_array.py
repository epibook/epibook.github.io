import sys
import collections
import string
import random

# @include
Person = collections.namedtuple('Person', ('age', 'name'))


def group_by_age(people):
    age_to_count = collections.Counter([person.age for person in people])
    age_to_offset, offset = {}, 0
    for age, count in age_to_count.items():
        age_to_offset[age] = offset
        offset += count

    while age_to_offset:
        from_age = next(iter(age_to_offset))
        from_idx = age_to_offset[from_age]
        to_age = people[from_idx].age
        to_idx = age_to_offset[people[from_idx].age]
        people[from_idx], people[to_idx] = people[to_idx], people[from_idx]
        # Use age_to_count to see when we are finished with a particular age.
        age_to_count[to_age] -= 1
        if age_to_count[to_age]:
            age_to_offset[to_age] = to_idx + 1
        else:
            del age_to_offset[to_age]
# @exclude


def rand_string(length):
    return ''.join(random.choice(string.ascii_lowercase) for _ in range(length))


def simple_test():
    people = [
        Person(20, 'foo'), Person(10, 'bar'), Person(20, 'widget'),
        Person(20, 'something')
    ]
    group_by_age(people)
    if people[0].age == 10:
        assert people[1].age == 20 and people[2].age == 20 and people[
            3].age == 20
    else:
        assert people[1].age == 20 and people[2].age == 20 and people[
            3].age == 10


def main():
    simple_test()
    for _ in range(1000):
        size = int(sys.argv[1]) if len(sys.argv) in [2, 3] else random.randint(
            1, 10)
        k = int(sys.argv[2]) if len(sys.argv) == 3 else random.randint(1, size)
        people = [
            Person(
                random.randint(0, k - 1), rand_string(random.randint(1, 10)))
            for _ in range(size)
        ]
        age_set_size = len(set([p.age for p in people]))
        group_by_age(people)
        # Check the correctness of sorting.
        assert 1 + sum(1 if people[i].age != people[i + 1].age else 0
                       for i in range(len(people) - 1)) == age_set_size


if __name__ == '__main__':
    main()
