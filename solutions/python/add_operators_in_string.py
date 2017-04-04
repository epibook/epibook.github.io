import functools


# @include
def expression_synthesis(digits, target):
    def directed_expression_synthesis(digits, current_term):
        def evaluate():
            intermediate_operands = []
            operand_it = iter(operands)
            intermediate_operands.append(next(operand_it))
            # Evaluates '*' first.
            for oper in operators:
                if oper == '*':
                    product = intermediate_operands[-1] * next(operand_it)
                    intermediate_operands[-1] = product
                else:  # oper == '+'.
                    intermediate_operands.append(next(operand_it))
            # Evaluates '+' second.
            return sum(intermediate_operands)

        current_term = current_term * 10 + digits[0]
        if len(digits) == 1:
            operands.append(current_term)
            if evaluate() == target:  # Found a match.
                # @exclude
                operand_it = iter(operands)
                print(next(operand_it), end='')
                for oper in operators:
                    print('', oper, next(operand_it), end='')
                print(' =', target)
                # @include
                return True
            del operands[-1]
            return False

        # No operator.
        if directed_expression_synthesis(digits[1:], current_term):
            return True
        # Tries multiplication operator '*'.
        operands.append(current_term)
        operators.append('*')
        if directed_expression_synthesis(digits[1:], 0):
            return True
        del operands[-1]
        del operators[-1]
        # Tries addition operator '+'.
        operands.append(current_term)
        # First check feasibility of plus operator.
        if target - evaluate() <= functools.reduce(lambda val, d: val * 10 + d,
                                                   digits[1:], 0):
            operators.append('+')
            if directed_expression_synthesis(digits[1:], 0):
                return True
            del operators[-1]
        del operands[-1]
        return False

    operands, operators = [], []
    return directed_expression_synthesis(digits, 0)


# @exclude


def main():
    A = [2, 3, 4]
    k = 4
    assert not expression_synthesis(A, k)
    A = [1, 2, 3, 4]
    k = 11
    assert expression_synthesis(A, k)
    A = [1, 2, 3, 2, 5, 3, 7, 8, 5, 9]
    k = 995
    assert expression_synthesis(A, k)
    A = [5, 2, 3, 4, 1]
    k = 20
    assert expression_synthesis(A, k)
    A = [1, 1, 2, 3]
    k = 124
    assert expression_synthesis(A, k)


if __name__ == '__main__':
    main()
