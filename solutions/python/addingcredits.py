import bintrees


# @include
class ClientsCreditsInfo:

    def __init__(self):
        self._offset = 0
        self._client_to_credit = {}
        self._credit_to_clients = bintrees.RBTree()

    def insert(self, client_id, c):
        self.remove(client_id)
        self._client_to_credit[client_id] = c - self._offset
        self._credit_to_clients.setdefault(c - self._offset,
                                           set()).add(client_id)

    def remove(self, client_id):
        credit = self._client_to_credit.get(client_id)
        if credit is not None:
            self._credit_to_clients[credit].remove(client_id)
            if not self._credit_to_clients[credit]:
                del self._credit_to_clients[credit]
            del self._client_to_credit[client_id]
            return True
        return False

    def lookup(self, client_id):
        credit = self._client_to_credit.get(client_id)
        return -1 if credit is None else credit + self._offset

    def add_all(self, C):
        self._offset += C

    def max(self):
        if not self._credit_to_clients:
            return ''
        clients = self._credit_to_clients.max_item()[1]
        return '' if not clients else next(iter(clients))
# @exclude


def main():
    a = ClientsCreditsInfo()
    assert a.max() == ''
    assert not a.remove('foo')
    a.insert('foo', 10)
    a.insert('foo', 1)
    a.insert('bar', 2)
    a.add_all(5)
    a.insert('widget', 3)
    a.add_all(5)
    a.insert('dothis', 4)
    assert 11 == a.lookup('foo')
    assert 12 == a.lookup('bar')
    assert 8 == a.lookup('widget')
    assert 4 == a.lookup('dothis')
    assert a.remove('foo')
    assert -1 == a.lookup('foo')
    assert a.max() == 'bar'
    a.insert('xyz', 13)
    assert a.max() == 'xyz'
    a.insert('dd', 15)
    assert a.max() == 'dd'


if __name__ == '__main__':
    main()
