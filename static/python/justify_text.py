# @include
def justify_text(words, L):
    curr_line_length, result, curr_line = 0, [], []
    for word in words:
        if curr_line_length + len(word) + len(curr_line) > L:
            # Distribute equally between words in curr_line.
            for i in range(L - curr_line_length):
                curr_line[i % (len(curr_line) - 1 or 1)] += ' '
            result.append(''.join(curr_line))
            curr_line, curr_line_length = [], 0
        curr_line.append(word)
        curr_line_length += len(word)
    # Use ljust(L) to pad the last line with the appropriate number of blanks.
    return result + [' '.join(curr_line).ljust(L)]
# @exclude


def test_case(words, L, golden):
    result = justify_text(words, L)
    for s in result:
        print("'%s'" % s)
    print()
    assert result == golden


def main():
    words = ['Text', 'justification', 'is', 'trickier', 'than', 'it', 'seems!']
    golden = [
        'Text          ', 'justification ', 'is    trickier', 'than it seems!'
    ]
    L = 14
    print('L =', L)
    test_case(words, L, golden)
    words = ['Listen', 'to', 'many,', 'speak', 'to', 'a', 'few.']
    golden = ['Listen', 'to    ', 'many, ', 'speak ', 'to   a', 'few.  ']
    L = 6
    print('L =', L)
    test_case(words, L, golden)
    words = [
        'The', 'quick', 'brown', 'fox', 'jumped', 'over', 'the', 'lazy', 'dogs.'
    ]
    golden = [
        'The   quick', 'brown   fox', 'jumped over', 'the    lazy',
        'dogs.      '
    ]
    L = 11
    print('L =', L)
    test_case(words, L, golden)
    golden = ['The  quick brown', 'fox  jumped over', 'the lazy dogs.  ']
    L = 16
    print('L =', L)
    test_case(words, L, golden)
    golden = ['The  quick  brown', 'fox  jumped  over', 'the lazy dogs.   ']
    L = 17
    print('L =', L)
    test_case(words, L, golden)
    words = ['Hello', 'World']
    golden = ['Hello World   ']
    L = 14
    test_case(words, L, golden)


if __name__ == '__main__':
    main()
