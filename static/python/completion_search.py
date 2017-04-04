import random
import sys


# @include
def find_salary_cap(target_payroll, current_salaries):
    current_salaries.sort()
    unadjusted_salary_sum = 0.0
    for i, current_salary in enumerate(current_salaries):
        adjusted_people = len(current_salaries) - i
        adjusted_salary_sum = current_salary * adjusted_people
        if unadjusted_salary_sum + adjusted_salary_sum >= target_payroll:
            return (target_payroll - unadjusted_salary_sum) / adjusted_people
        unadjusted_salary_sum += current_salary
    # No solution, since target_payroll > existing payroll.
    return -1.0
# @exclude


def small_test():
    A = [20, 30, 40, 90, 100]
    T = 210
    assert find_salary_cap(T, A) == 60
    T = 280
    assert find_salary_cap(T, A) == 100
    T = 50
    assert find_salary_cap(T, A) == 10
    T = 281
    assert find_salary_cap(T, A) == -1.0


def main():
    small_test()
    for _ in range(10000):
        if len(sys.argv) == 2:
            n = int(sys.argv[1])
            tar = random.randrange(100000)
        elif len(sys.argv) == 3:
            n = int(sys.argv[1])
            tar = int(sys.argv[2])
        else:
            n = random.randint(1, 1000)
            tar = random.randrange(100000)
        A = [random.randrange(10000) for _ in range(n)]
        print('A = ', end='')
        print(*A)
        print('tar =', tar)
        ret = find_salary_cap(tar, A)
        A.sort()
        if ret != -1.0:
            print('ret =', ret)
            sum = 0.0
            for i in range(n):
                if A[i] > ret:
                    sum += ret
                else:
                    sum += A[i]
            tar -= sum
            print('sum =', sum)
            assert tar < 1.0e-8


if __name__ == '__main__':
    main()
