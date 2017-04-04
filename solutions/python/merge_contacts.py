# @include
class ContactList:

    def __init__(self, names):
        '''
        names is a list of strings.
        '''
        self.names = names

    def __hash__(self):
        # Conceptually we want to hash the set of names. Since the set type is
        # mutable, it cannot be hashed. Therefore we use frozenset.
        return hash(frozenset(self.names))

    def __eq__(self, other):
        return set(self.names) == set(other.names)


def merge_contact_lists(contacts):
    '''
    contacts is a list of ContactList.
    '''
    return list(set(contacts))
# @exclude


def main():
    contacts = [
        ContactList(('a', 'b', 'c')), ContactList(('a', 'c', 'b')), ContactList(
            ('b', 'c', 'd'))
    ]
    result = merge_contact_lists(contacts)
    assert len(result) == 2


if __name__ == '__main__':
    main()
