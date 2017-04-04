// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <string>
#include <unordered_set>
#include <vector>

using std::hash;
using std::string;
using std::unordered_set;
using std::vector;

// @include
struct ContactList {
  // Equal function for hash.
  bool operator==(const ContactList& that) const {
    return unordered_set<string>(names.begin(), names.end()) ==
           unordered_set<string>(that.names.begin(), that.names.end());
  }

  vector<string> names;
};

// Hash function for ContactList.
struct HashContactList {
  size_t operator()(const ContactList& contacts) const {
    size_t hash_code = 0;
    for (const string& name : unordered_set<string>(contacts.names.begin(),
                                                    contacts.names.end())) {
      hash_code ^= hash<string>()(name);
    }
    return hash_code;
  }
};

vector<ContactList> MergeContactLists(const vector<ContactList>& contacts) {
  unordered_set<ContactList, HashContactList> unique_contacts(
      contacts.begin(), contacts.end());
  return {unique_contacts.begin(), unique_contacts.end()};
}
// @exclude

int main(int argc, char* argv[]) {
  vector<ContactList> contacts = {
      {{"a", "b", "c"}}, {{"a", "c", "b"}}, {{"b", "c", "d"}}};
  auto result = MergeContactLists(contacts);
  assert(result.size() == 2);
  return 0;
}
