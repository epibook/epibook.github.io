// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <map>
#include <string>
#include <unordered_map>
#include <unordered_set>

using std::map;
using std::string;
using std::unordered_map;
using std::unordered_set;

// @include
class ClientsCreditsInfo {
 public:
  void Insert(const string& client_id, int c) {
    Remove(client_id);
    client_to_credit_.emplace(client_id, c - offset_);
    credit_to_clients_[c - offset_].emplace(client_id);
  }

  bool Remove(const string& client_id) {
    auto credit_iter = client_to_credit_.find(client_id);
    if (credit_iter != client_to_credit_.end()) {
      credit_to_clients_[credit_iter->second].erase(client_id);
      if (credit_to_clients_[credit_iter->second].empty()) {
        credit_to_clients_.erase(credit_iter->second);
      }
      client_to_credit_.erase(credit_iter);
      return true;
    }
    return false;
  }

  int Lookup(const string& client_id) const {
    auto credit_iter = client_to_credit_.find(client_id);
    return credit_iter == client_to_credit_.cend()
               ? -1
               : credit_iter->second + offset_;
  }

  void AddAll(int C) { offset_ += C; }

  string Max() const {
    auto iter = credit_to_clients_.crbegin();
    return iter == credit_to_clients_.crend() || iter->second.empty()
               ? ""
               : *iter->second.cbegin();
  }

 private:
  int offset_ = 0;
  unordered_map<string, int> client_to_credit_;
  map<int, unordered_set<string>> credit_to_clients_;
};
// @exclude

int main(int argc, char* argv[]) {
  ClientsCreditsInfo a;
  assert(a.Max() == "");
  assert(!a.Remove("foo"));
  a.Insert("foo", 10);
  a.Insert("foo", 1);
  a.Insert("bar", 2);
  a.AddAll(5);
  a.Insert("widget", 3);
  a.AddAll(5);
  a.Insert("dothis", 4);
  assert(11 == a.Lookup("foo"));
  assert(12 == a.Lookup("bar"));
  assert(8 == a.Lookup("widget"));
  assert(4 == a.Lookup("dothis"));
  assert(a.Remove("foo"));
  assert(-1 == a.Lookup("foo"));
  assert(a.Max().compare("bar") == 0);
  a.Insert("xyz", 13);
  assert(a.Max().compare("xyz") == 0);
  a.Insert("dd", 15);
  assert(a.Max().compare("dd") == 0);
  return 0;
}
