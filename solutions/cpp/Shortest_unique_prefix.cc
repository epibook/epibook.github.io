// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <random>
#include <string>
#include <unordered_map>
#include <unordered_set>
#include <utility>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::make_unique;
using std::pair;
using std::random_device;
using std::unique_ptr;
using std::string;
using std::uniform_int_distribution;
using std::unordered_map;
using std::unordered_set;

string RandString(int len) {
  default_random_engine gen((random_device())());
  string ret;
  while (len--) {
    uniform_int_distribution<int> dis(0, 25);
    ret += dis(gen) + 'a';
  }
  return ret;
}

// @include
class Trie {
 public:
  // @exclude
  virtual ~Trie() { Clear(); }
  // @include
  bool Insert(const string& s) {
    auto* p = root_.get();
    for (char c : s) {
      if (p->leaves.find(c) == p->leaves.cend()) {
        p->leaves[c] = make_unique<TrieNode>(TrieNode());
      }
      p = p->leaves[c].get();
    }

    // s already existed in this trie.
    if (p->isString) {
      return false;
    } else {  // p->isString == false.
      p->isString = true;  // Inserts s into this trie.
      return true;
    }
  }

  string GetShortestUniquePrefix(const string& s) {
    auto* p = root_.get();
    string prefix;
    for (char c : s) {
      prefix += c;
      if (p->leaves.find(c) == p->leaves.cend()) {
        return prefix;
      }
      p = p->leaves[c].get();
    }
    return {};
  }
  // @exclude
  void Clear() { Clear(&root_); }
  // @include

 private:
  struct TrieNode {
    bool isString = false;
    unordered_map<char, unique_ptr<TrieNode>> leaves;
  };

  unique_ptr<TrieNode> root_ = make_unique<TrieNode>(TrieNode());
  // @exclude
  void Clear(unique_ptr<TrieNode>* p) {
    for (auto& e : (*p)->leaves) {
      if (e.second) {
        Clear(&(e.second));
      }
    }
    p->reset(nullptr);
  }
  // @include
};

string FindShortestPrefix(const string& s, const unordered_set<string>& D) {
  // Builds a trie according to given dictionary D.
  Trie T;
  for (const string& word : D) {
    T.Insert(word);
  }
  return T.GetShortestUniquePrefix(s);
}
// @exclude

string CheckAns(const string& s, unordered_set<string>& D) {
  int len = 0;
  for (auto iter = D.cbegin(); iter != D.cend(); ++iter) {
    int i;
    for (i = 0; i < s.size() && i < (*iter).size(); ++i) {
      if (s[i] != (*iter)[i]) {
        break;
      }
    }
    if (i > len) {
      len = i;
    }
  }
  if (len == s.size()) {
    return string();
  } else {
    return s.substr(0, len + 1);
  }
}

int main(int argc, char* argv[]) {
  default_random_engine gen((random_device())());
  for (int times = 0; times < 100; ++times) {
    unordered_set<string> D;
    string s;
    if (argc == 2) {
      s = argv[1];
    } else {
      uniform_int_distribution<int> dis(1, 10);
      s = RandString(dis(gen));
    }
    uniform_int_distribution<int> dis(1, 10000);
    int n = dis(gen);
    while (n--) {
      uniform_int_distribution<int> dis(1, 10);
      D.emplace(RandString(dis(gen)));
    }
    cout << s << ' ' << "shortest prefix = " << FindShortestPrefix(s, D)
         << endl;
    assert(FindShortestPrefix(s, D) == CheckAns(s, D));
  }
  return 0;
}
