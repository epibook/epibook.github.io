import sys
import random


# @include
# Given n, return all primes up to and including n.
def generate_primes(n):
    primes = []
    # is_prime[p] represents if p is prime or not. Initially, set each to
    # true, expecting 0 and 1. Then use sieving to eliminate nonprimes.
    is_prime = [False, False] + [True] * (n - 1)
    for p in range(2, n):
        if is_prime[p]:
            primes.append(p)
            # Sieve p's multiples.
            for i in range(p, n + 1, p):
                is_prime[i] = False
    return primes
# @exclude


# Can also test against output of primesieve program:
# unix% primesieve 1000 --count=1 -p1
def main():
    if len(sys.argv) == 2:
        n = int(sys.argv[1])
        print('n =', n)
        primes = generate_primes(n)
        print(*primes, sep='\n')
        return all(i % j for i in primes for j in range(2, i))
    else:
        for _ in range(100):
            n = random.randint(2, 100000)
            print('n =', n)
            primes = generate_primes(n)
            return all(i % j for i in primes for j in range(2, i))


if __name__ == '__main__':
    main()
