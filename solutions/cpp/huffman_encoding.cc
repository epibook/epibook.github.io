// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <functional>
#include <iostream>
#include <memory>
#include <queue>
#include <random>
#include <string>
#include <unordered_map>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::function;
using std::make_unique;
using std::priority_queue;
using std::unique_ptr;
using std::random_device;
using std::shared_ptr;
using std::string;
using std::uniform_int_distribution;
using std::unordered_map;
using std::vector;

const double kEnglishFreq[] = {
    8.167, 1.492, 2.782, 4.253, 12.702, 2.228, 2.015, 6.094, 6.966,
    0.153, 0.772, 4.025, 2.406, 6.749,  7.507, 1.929, 0.095, 5.987,
    6.327, 9.056, 2.758, 0.978, 2.360,  0.150, 1.974, 0.074};

struct BinaryTreeNode;

void AssignHuffmanCode(const shared_ptr<BinaryTreeNode>&,
                       const unique_ptr<string>&,
                       unordered_map<char, string>*);

// @include
struct CharWithFrequency {
  char c;
  double freq;
};

struct BinaryTreeNode {
  double aggregate_freq;
  CharWithFrequency* s;
  shared_ptr<BinaryTreeNode> left, right;
};

unordered_map<char, string> HuffmanEncoding(
    vector<CharWithFrequency>* symbols) {
  // Initially assigns each symbol into candidates.
  priority_queue<
      shared_ptr<BinaryTreeNode>, vector<shared_ptr<BinaryTreeNode>>,
      function<bool(shared_ptr<BinaryTreeNode>, shared_ptr<BinaryTreeNode>)>>
  candidates([](const shared_ptr<BinaryTreeNode>& lhs,
                const shared_ptr<BinaryTreeNode>& rhs) {
    return lhs->aggregate_freq > rhs->aggregate_freq;
  });
  for (auto& s : *symbols) {
    candidates.emplace(new BinaryTreeNode{s.freq, &s, nullptr, nullptr});
  }

  // Keeps combining two nodes until there is one node left.
  while (candidates.size() > 1) {
    shared_ptr<BinaryTreeNode> left = candidates.top();
    candidates.pop();
    shared_ptr<BinaryTreeNode> right = candidates.top();
    candidates.pop();
    candidates.emplace(new BinaryTreeNode{
        left->aggregate_freq + right->aggregate_freq, nullptr, left, right});
  }

  unordered_map<char, string> huffman_encoding;
  // Traverses the binary tree, assigning codes to nodes.
  AssignHuffmanCode(candidates.top(), make_unique<string>(),
                    &huffman_encoding);
  return huffman_encoding;
}

void AssignHuffmanCode(const shared_ptr<BinaryTreeNode>& tree,
                       const unique_ptr<string>& code,
                       unordered_map<char, string>* huffman_encoding) {
  if (tree) {
    if (tree->s) {
      // This node is a leaf.
      (*huffman_encoding)[tree->s->c] = *code;
    } else {  // Non-leaf node.
      code->push_back('0');
      AssignHuffmanCode(tree->left, code, huffman_encoding);
      code->back() = '1';
      AssignHuffmanCode(tree->right, code, huffman_encoding);
      code->pop_back();
    }
  }
}
// @exclude

int main(int argc, char* argv[]) {
  int n;
  default_random_engine gen((random_device())());
  if (argc == 2) {
    if (0 != strcmp(argv[1], "huffman")) {
      n = atoi(argv[1]);
    } else {
      n = 26;
    }
  } else {
    uniform_int_distribution<int> dis(1, 255);
    n = dis(gen);
  }
  vector<CharWithFrequency> symbols;
  int sum = 0;
  if (argc == 1 || (0 != strcmp(argv[1], "huffman"))) {
    for (int i = 0; i < n; ++i) {
      CharWithFrequency t;
      t.c = i;
      uniform_int_distribution<int> dis(0, 100000);
      t.freq = dis(gen);
      sum += t.freq;
      symbols.emplace_back(t);
    }
    for (int i = 0; i < n; ++i) {
      symbols[i].freq /= sum;
    }
  } else {
    for (int i = 0; i < n; ++i) {
      CharWithFrequency t;
      t.c = 'a' + i;
      t.freq = kEnglishFreq[i];
      symbols.emplace_back(t);
    }
  }
  auto result = HuffmanEncoding(&symbols);
  double avg = 0.0;
  for (const auto& symbol : symbols) {
    cout << symbol.c << " " << symbol.freq << " " << result[symbol.c] << endl;
    avg += symbol.freq / 100 * result[symbol.c].size();
  }
  cout << "average huffman code length = " << avg << endl;
  return 0;
}
