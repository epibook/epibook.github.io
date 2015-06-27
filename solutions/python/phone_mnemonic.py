# Phone_mnemonic.cc c49e1253eabe0a3ae66bcdfda69fd5c045b18077
import random
import sys
import string
import functools

# @include
def phone_mnemonic(phone_number):
    partial_mnemonic = [' '] * len(phone_number)
    mnemonics = []
    phone_mnemonic_helper(phone_number, 0, partial_mnemonic, mnemonics)
    return mnemonics

M = (('0', ), ('1', ), ('A', 'B', 'C', ), ('D', 'E', 'F', ),
     ('G', 'H', 'I', ), ('J', 'K', 'L', ), ('M', 'N', 'O', ),
     ('P', 'Q', 'R', 'S', ), ('T', 'U', 'V', ), ('W', 'X', 'Y', 'Z', ), )

def phone_mnemonic_helper(phone_number, digit, partial_mnemonic, mnemonics):
    if digit == len(phone_number):
        mnemonics.append(''.join(partial_mnemonic))
    else:
        for c in M[int(phone_number[digit])]:
            partial_mnemonic[digit] = c
            phone_mnemonic_helper(phone_number, digit + 1, partial_mnemonic,\
                                  mnemonics)

# @exclude
def main():
    if len(sys.argv) == 2:
        try:
            print(phone_mnemonic(sys.argv[1]))
        except ValueError:
            print("Invalid value. Use only numbers")
    else:
        for _ in range(1000):
            digit_length = random.randint(1, 10)
            phone_number = ''.join(random.choice(string.digits)\
                                   for _ in range(digit_length))
            num_possible_mnemonics = functools.reduce(lambda x, y: x*y,\
                        [len(M[int(number)]) for number in phone_number])
            mnemonics = phone_mnemonic(phone_number)
            assert len(mnemonics) == num_possible_mnemonics
            for __ in range(5):
                random_mnemonic = ''.join([M[int(number)]\
                                  [random.randint(0, len(M[int(number)])-1)]\
                                                 for number in phone_number])
                assert random_mnemonic in mnemonics

if __name__ == '__main__':
    main()
