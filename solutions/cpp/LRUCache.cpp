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
    auto it = cache_.find(isbn);
    if (it == cache_.end()) {
      return false;
    }

    *price = it->second.second;
    MoveToFront(isbn, it);
    return true;
  }

  void Insert(int isbn, int price) {
    auto it = cache_.find(isbn);
    if (it != cache_.end()) {
      MoveToFront(isbn, it);
    } else {
      // Remove the least recently used.
      if (cache_.size() == capacity) {
        cache_.erase(data_.back());
        data_.pop_back();
      }

      data_.emplace_front(isbn);
      cache_[isbn] = {data_.begin(), price};
    }
  }

  bool Erase(int isbn) {
    auto it = cache_.find(isbn);
    if (it == cache_.end()) {
      return false;
    }

    data_.erase(it->second.first);
    cache_.erase(it);
    return true;
  }

 private:
  typedef unordered_map<int, pair<list<int>::iterator, int>> Table;

  // Moves the most recent accessed item to the front.
  void MoveToFront(int isbn, const Table::iterator& it) {
    data_.erase(it->second.first);
    data_.emplace_front(isbn);
    it->second.first = data_.begin();
  }

  Table cache_;
  list<int> data_;
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
