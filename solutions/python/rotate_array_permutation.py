import fractions


# @include
def rotate_array(rotate_amount, A):
    def apply_cyclic_permutation(rotate_amount, offset):
        temp = A[offset]
        for i in range(1, cycle_length):
            idx = (offset + i * rotate_amount) % len(A)
            A[idx], temp = temp, A[idx]
        A[offset] = temp

    rotate_amount %= len(A)
    if rotate_amount == 0:
        return
    num_cycles = fractions.gcd(len(A), rotate_amount)
    cycle_length = len(A) // num_cycles
    for c in range(num_cycles):
        apply_cyclic_permutation(rotate_amount, c)
# @exclude
