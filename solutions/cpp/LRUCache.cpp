// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <iostream>
#include <cassert>
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
    MoveToFront(isbn, it);  // Since isbn is the most recently used ISBN.
    return true;
  }

  void Insert(int isbn, int price) {
    auto it = isbn_price_table_.find(isbn);
    if (it != isbn_price_table_.end()) {
      MoveToFront(isbn, it);
    } else {
      if (isbn_price_table_.size() == capacity) {
        // Remove the least recently used ISBN to get space.
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

  // Moves isbn to the front of the LRU queue.
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
  LRUCache<3> c;
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
  c.Insert(2, 2);
  c.Insert(3, 3);
  c.Insert(4, 4);
  c.Insert(5, 5);
  assert(!c.Lookup(1, &val));
  return 0;
}
