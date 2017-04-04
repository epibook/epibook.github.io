// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <chrono>
#include <functional>
#include <iostream>
#include <mutex>
#include <thread>

using std::ref;
using std::cout;
using std::endl;
using std::chrono::milliseconds;
using std::thread;
using std::this_thread::sleep_for;
using std::recursive_mutex;
using std::lock_guard;

class Account {
 public:
  Account(int balance) : balance_(balance), id_(++global_id_) {}

  int get_balance() { return balance_; }

  static void Transfer(Account& from, Account& to, int amount) {
    thread(&Account::Move, &from, ref(to), amount).detach();
  }

 private:
  bool Move(Account& to, int amount) {
    // @include
    recursive_mutex& mx1 = (id_ < to.id_) ? mx_ : to.mx_;
    recursive_mutex& mx2 = (id_ < to.id_) ? to.mx_ : mx_;
    lock_guard<recursive_mutex> lock(mx1);
    {
      // Does not matter if lock1 equals lock2: since recursive_mutex locks
      // are reentrant, we will re-acquire lock2.
      lock_guard<recursive_mutex> lock(mx2);
      // @exclude
      if (amount > balance_) {
        return false;
      }
      to.balance_ += amount;
      balance_ -= amount;
      cout << "returning true" << endl;
      return true;
    }
  }

  int balance_;
  int id_;
  recursive_mutex mx_;
  static int global_id_;
};

int Account::global_id_ = 0;

int main(int argc, char* argv[]) {
  Account from(200);
  Account to(100);
  cout << "Initial balances = " << from.get_balance() << ' '
       << to.get_balance() << endl;
  Account::Transfer(from, to, 50);
  sleep_for(milliseconds(100));
  cout << "New balances = " << from.get_balance() << ' ' << to.get_balance()
       << endl;
  Account::Transfer(from, from, 50);
  sleep_for(milliseconds(100));
  cout << "New balances = " << from.get_balance() << ' ' << to.get_balance()
       << endl;
  return 0;
}
