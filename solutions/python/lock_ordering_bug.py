import time
import threading


# @include
class Account:

    _global_id = 0

    def __init__(self, balance):
        self._balance = balance
        self._id = Account._global_id
        Account._global_id += 1
        self._lock = threading.RLock()

    def get_balance(self):
        return self._balance

    @staticmethod
    def transfer(acc_from, acc_to, amount):
        th = threading.Thread(target=acc_from._move, args=(acc_to, amount))
        th.start()

    def _move(self, acc_to, amount):
        with self._lock:
            if amount > self._balance:
                return False
            acc_to._balance += amount
            self._balance -= amount
            print('returning True')
            return True
# @exclude


def main():
    acc_from = Account(200)
    acc_to = Account(100)
    print('initial balances =', acc_from.get_balance(), acc_to.get_balance())
    assert (acc_from.get_balance(), acc_to.get_balance()) == (200, 100)
    Account.transfer(acc_from, acc_to, 50)
    time.sleep(0.1)
    print('new balances =', acc_from.get_balance(), acc_to.get_balance())
    assert (acc_from.get_balance(), acc_to.get_balance()) == (150, 150)


if __name__ == '__main__':
    main()
