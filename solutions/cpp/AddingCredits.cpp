// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <map>
#include <string>
#include <unordered_set>
#include <unordered_map>

using std::map;
using std::string;
using std::unordered_map;
using std::unordered_set;

// @include
class ClientsCreditsInfo {
 public:
  bool Insert(const string& s, int c) {
    if (credits_.emplace(s, c - offset_).second) {
      inverse_credits_[c - offset_].emplace(s);
      return true;
    }
    return false;
  }

  bool Remove(const string& s) {
    auto credits_it = credits_.find(s);
    if (credits_it != credits_.end()) {
      inverse_credits_[credits_it->second].erase(s);
      credits_.erase(credits_it);
      return true;
    }
    return false;
  }

  int Lookup(const string& s) const {
    auto it = credits_.find(s);
    return it == credits_.cend() ? -1 : it->second + offset_;
  }

  void AddAll(int C) { offset_ += C; }

  string Max() const {
    auto it = inverse_credits_.crbegin();
    return it == inverse_credits_.crend() || it->second.empty()
               ? ""
               : *it->second.cbegin();
  }

 private:
  int offset_ = 0;
  unordered_map<string, int> credits_;
  map<int, unordered_set<string>> inverse_credits_;
};
// @exclude

int main(int argc, char* argv[]) {
  ClientsCreditsInfo a;
  assert(a.Max() == "");
  assert(!a.Remove("foo"));
  assert(a.Insert("foo", 1));
  assert(!a.Insert("foo", 10));
  assert(a.Insert("bar", 2));
  a.AddAll(5);
  assert(a.Insert("widget", 3));
  a.AddAll(5);
  a.Insert("dothis", 4);
  assert(11 == a.Lookup("foo"));
  assert(12 == a.Lookup("bar"));
  assert(8 == a.Lookup("widget"));
  assert(4 == a.Lookup("dothis"));
  assert(a.Remove("foo"));
  assert(-1 == a.Lookup("foo"));
  assert(a.Max().compare("bar") == 0);
  assert(a.Insert("xyz", 13));
  assert(a.Max().compare("xyz") == 0);
  a.Insert("dd", 15);
  assert(a.Max().compare("dd") == 0);
  return 0;
}
