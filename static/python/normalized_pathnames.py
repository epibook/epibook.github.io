import sys


# @include
def shortest_equivalent_path(path):
    if not path:
        raise ValueError('Empty string is not a valid path.')

    path_names = []  # Uses list as a stack.
    # Special case: starts with '/', which is an absolute path.
    if path[0] == '/':
        path_names.append('/')

    for token in (token for token in path.split('/') if token not in ['.', '']):
        if token == '..':
            if not path_names or path_names[-1] == '..':
                path_names.append(token)
            else:
                if path_names[-1] == '/':
                    raise ValueError('Path error')
                path_names.pop()
        else:  # Must be a name.
            path_names.append(token)

    result = '/'.join(path_names)
    return result[result.startswith('//'):]  # Avoid starting '//'.
# @exclude


def main():
    assert shortest_equivalent_path('123/456') == '123/456'
    assert shortest_equivalent_path('/123/456') == '/123/456'
    assert shortest_equivalent_path('usr/lib/../bin/gcc') == 'usr/bin/gcc'
    assert shortest_equivalent_path('./../') == '..'
    assert shortest_equivalent_path('../../local') == '../../local'
    assert shortest_equivalent_path('./.././../local') == '../../local'
    assert shortest_equivalent_path('/foo/../foo/./../') == '/'
    try:
        shortest_equivalent_path('/foo.txt')
    except ValueError as e:
        print(e)
    try:
        shortest_equivalent_path('/..')
    except ValueError as e:
        print(e)
    try:
        shortest_equivalent_path('/cpp_name/bin/')
    except ValueError as e:
        print(e)
    assert (shortest_equivalent_path('scripts//./../scripts/awkscripts/././') ==
            'scripts/awkscripts')
    if len(sys.argv) == 2:
        print(shortest_equivalent_path(sys.argv[1]))


if __name__ == '__main__':
    main()
