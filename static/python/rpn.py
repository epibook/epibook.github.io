# @include
def evaluate(RPN_expression):
    intermediate_results = []
    DELIMITER = ','
    OPERATORS = {
        '+': lambda y, x: x + y,
        '-': lambda y, x: x - y,
        '*': lambda y, x: x * y,
        '/': lambda y, x: int(x / y)
    }

    for token in RPN_expression.split(DELIMITER):
        if token in OPERATORS:
            intermediate_results.append(
                OPERATORS[token](
                    intermediate_results.pop(),
                    intermediate_results.pop()))
        else:  # token is a number.
            intermediate_results.append(int(token))
    return intermediate_results[-1]
# @exclude


def main():
    assert 0 == evaluate('2,-10,/')
    assert -5 == evaluate('-10,2,/')
    assert 5 == evaluate('-10,-2,/')
    assert -5 == evaluate('5,10,-')
    assert 6 == evaluate('-10,-16,-')
    assert 12 == evaluate('10,2,+')
    assert 15 == evaluate('1,2,+,3,4,*,+')
    assert 42 == evaluate('1,2,3,4,5,+,*,+,+,3,4,*,+')
    assert -6 == evaluate('1,2,3,4,5,+,*,+,+,3,4,*,+,-7,/')
    assert 0 == evaluate('1,-22,/')


if __name__ == '__main__':
    main()
