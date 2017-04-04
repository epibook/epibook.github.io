// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <list>
#include <unordered_map>
#include <utility>

using std::cout;
using std::endl;
using std::list;
using std::pair;
using std::unordered_map;

// @include
template <size_t capacity>
class LRUCache {
 public:
  bool Lookup(int isbn, int* price) {
    auto it = isbn_price_table_.find(isbn);
    if (it == isbn_price_table_.end()) {
      return false;
    }

    *price = it->second.second;
    // Since key has just been accessed, move it to the front.
    MoveToFront(isbn, it);
    return true;
  }

  void Insert(int isbn, int price) {
    auto it = isbn_price_table_.find(isbn);
    // We add the value for key only if key is not present - we don't update
    // existing values.
    if (it != isbn_price_table_.end()) {
      // Specification says we should make isbn the most recently used.
      MoveToFront(isbn, it);
    } else {
      if (isbn_price_table_.size() == capacity) {
        // Removes the least recently used ISBN to get space.
        isbn_price_table_.erase(lru_queue_.back());
        lru_queue_.pop_back();
      }
      lru_queue_.emplace_front(isbn);
      isbn_price_table_[isbn] = {lru_queue_.begin(), price};
    }
  }

  bool Erase(int isbn) {
    auto it = isbn_price_table_.find(isbn);
    if (it == isbn_price_table_.end()) {
      return false;
    }

    lru_queue_.erase(it->second.first);
    isbn_price_table_.erase(it);
    return true;
  }

 private:
  typedef unordered_map<int, pair<list<int>::iterator, int>> Table;

  // Forces this key-value pair to move to the front.
  void MoveToFront(int isbn, const Table::iterator& it) {
    lru_queue_.erase(it->second.first);
    lru_queue_.emplace_front(isbn);
    it->second.first = lru_queue_.begin();
  }

  Table isbn_price_table_;
  list<int> lru_queue_;
};
// @exclude

int main(int argc, char* argv[]) {
  const int kCapacity = 2;
  {
    LRUCache<kCapacity> c;
    cout << "c.Insert(1, 1)" << endl;
    c.Insert(1, 1);
    cout << "c.Insert(1, 10)" << endl;
    c.Insert(1, 10);
    int val;
    cout << "c.Lookup(2, val)" << endl;
    assert(!c.Lookup(2, &val));
    cout << "c.Lookup(1, val)" << endl;
    assert(c.Lookup(1, &val));
    assert(val == 1);
    c.Erase(1);
    assert(!c.Lookup(1, &val));
  }

  // test capacity constraints honored, also FIFO ordering
  {
    LRUCache<kCapacity> c;
    c.Insert(1, 1);
    c.Insert(2, 1);
    c.Insert(3, 1);
    c.Insert(4, 1);
    int val;
    assert(!c.Lookup(1, &val));
    assert(!c.Lookup(2, &val));
    assert(c.Lookup(3, &val));
    assert(val == 1);
    assert(c.Lookup(4, &val));
    assert(val == 1);
  }

  // test retrieval moves to front
  {
    LRUCache<kCapacity> c;
    c.Insert(1, 1);
    c.Insert(2, 1);
    c.Insert(3, 1);
    int val;
    c.Lookup(2, &val);
    c.Insert(4, 1);
    assert(!c.Lookup(1, &val));
    assert(c.Lookup(2, &val));
    assert(val == 1);
    assert(!c.Lookup(3, &val));
    assert(c.Lookup(4, &val));
    assert(val == 1);
  }

  // test update moves to front
  {
    LRUCache<kCapacity> c;
    c.Insert(1, 1);
    c.Insert(2, 1);
    c.Insert(3, 1);
    c.Insert(2, 2);
    c.Insert(4, 1);
    int val;
    assert(!c.Lookup(1, &val));
    assert(c.Lookup(2, &val));
    assert(val == 1);
    assert(!c.Lookup(3, &val));
    assert(c.Lookup(4, &val));
    assert(val == 1);
  }

  // test remove
  {
    LRUCache<kCapacity> c;
    c.Insert(1, 1);
    c.Insert(2, 1);
    c.Erase(2);
    c.Insert(3, 3);
    int val;
    assert(c.Lookup(1, &val));
    assert(val == 1);
    assert(!c.Lookup(2, &val));
    assert(c.Lookup(3, &val));
    assert(val == 3);
  }
  return 0;
}
