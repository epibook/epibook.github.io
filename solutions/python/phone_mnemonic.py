import sys
import random
import string
import itertools

# @include
# The mapping from digit to corresponding characters.
MAPPING = ('0', '1', 'ABC', 'DEF', 'GHI', 'JKL', 'MNO', 'PQRS', 'TUV', 'WXYZ')


def phone_mnemonic(phone_number):
    def phone_mnemonic_helper(digit):
        if digit == len(phone_number):
            # All digits are processed, so add partial_mnemonic to mnemonics.
            # (We add a copy since subsequent calls modify partial_mnemonic.)
            mnemonics.append(''.join(partial_mnemonic))
        else:
            # Try all possible characters for this digit.
            for c in MAPPING[int(phone_number[digit])]:
                partial_mnemonic[digit] = c
                phone_mnemonic_helper(digit + 1)

    mnemonics, partial_mnemonic = [], [0] * len(phone_number)
    phone_mnemonic_helper(0)
    return mnemonics


# @exclude


# Pythonic solution
def phone_mnemonic_pythonic(phone_number):
    return [
        ''.join(mnemonic)
        for mnemonic in itertools.product(*(MAPPING[int(digit)]
                                            for digit in phone_number))
    ]


def phone_mnemonic_pythonic_another(phone_number):
    TABLE = {
        '0': '0', '1': '1', '2': 'ABC', '3': 'DEF', '4': 'GHI', '5': 'JKL', '6':
        'MNO', '7': 'PQRS', '8': 'TUV', '9': 'WXYZ'
    }
    return [
        a + b
        for a in TABLE.get(phone_number[:1], '')
        for b in phone_mnemonic_pythonic_another(phone_number[1:]) or ['']
    ]


def rand_string(length):
    return ''.join(random.choice(string.digits) for _ in range(length))


def main():
    num = sys.argv[1] if len(sys.argv) == 2 else rand_string(10)
    result = phone_mnemonic(num)
    print('number =', num)
    for s in result:
        print(s)
    assert result == phone_mnemonic_pythonic(num)
    assert result == phone_mnemonic_pythonic_another(num)


if __name__ == '__main__':
    main()
