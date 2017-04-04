import sys


# @include
def get_valid_ip_address(s):
    def is_valid_part(s):
        # '00', '000', '01', etc. are not valid, but '0' is valid.
        return len(s) == 1 or (s[0] != '0' and int(s) <= 255)

    result, parts = [], [None] * 4
    for i in range(1, min(4, len(s))):
        parts[0] = s[:i]
        if is_valid_part(parts[0]):
            for j in range(1, min(len(s) - i, 4)):
                parts[1] = s[i:i + j]
                if is_valid_part(parts[1]):
                    for k in range(1, min(len(s) - i - j, 4)):
                        parts[2], parts[3] = s[i + j:i + j + k], s[i + j + k:]
                        if is_valid_part(parts[2]) and is_valid_part(parts[3]):
                            result.append('.'.join(parts))
    return result
# @exclude


def main():
    if len(sys.argv) == 2:
        result = get_valid_ip_address(sys.argv[1])
        for s in result:
            print(s)
    res1 = get_valid_ip_address('255255255255')
    assert res1 == ['255.255.255.255']
    res2 = get_valid_ip_address('19216811')
    assert len(res2) == 9
    res3 = get_valid_ip_address('1111')
    assert res3 == ['1.1.1.1']
    res4 = get_valid_ip_address('11000')
    assert len(res4) == 2
    sorted(res4)
    assert res4 == ['1.10.0.0', '11.0.0.0']


if __name__ == '__main__':
    main()
