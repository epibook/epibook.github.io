// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <random>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::random_device;
using std::uniform_int_distribution;
using std::vector;

void RotateMatrix(vector<vector<int>>* A) {
  auto& A_ref = *A;
  for (int i = 0; i < (A_ref.size() / 2); ++i) {
    for (int j = i; j < A_ref.size() - i - 1; ++j) {
      int temp = A_ref[i][j];
      A_ref[i][j] = A_ref[A_ref.size() - 1 - j][i];
      A_ref[A_ref.size() - 1 - j][i] = A_ref[A_ref.size() - 1 - i][A_ref.size() - 1 - j];
      A_ref[A_ref.size() - 1 - i][A_ref.size() - 1 - j] = A_ref[j][A_ref.size() - 1 - i];
      A_ref[j][A_ref.size() - 1 - i] = temp;
    }
  }
}

// @include
class RotatedMatrix {
 public:
  explicit RotatedMatrix(const vector<vector<int>>& A) : A_(A) {}

  int ReadEntry(int i, int j) const { return A_[A_.size() - 1 - j][i]; }

  void WriteEntry(int i, int j, int v) { A_[A_.size() - 1 - j][i] = v; }

 private:
  vector<vector<int>> A_;
};
// @exclude

void CheckAnswer(const vector<vector<int>>& A, const vector<vector<int>>& B) {
  RotatedMatrix rA(A);
  for (int i = 0; i < A.size(); ++i) {
    for (int j = 0; j < A.size(); ++j) {
      assert(rA.ReadEntry(i, j) == B[i][j]);
    }
  }
}

int main(int argc, char* argv[]) {
  int n;
  if (argc == 2) {
    n = atoi(argv[1]);
    vector<vector<int>> A(1 << n, vector<int>(1 << n));
    int k = 1;
    for (int i = 0; i < A.size(); ++i) {
      for (int j = 0; j < A[i].size(); ++j) {
        A[i][j] = k++;
      }
    }
    vector<vector<int>> B(A);
    RotateMatrix(&B);
    CheckAnswer(A, B);
  } else {
    default_random_engine gen((random_device())());
    for (int times = 0; times < 100; ++times) {
      uniform_int_distribution<int> dis(1, 10);
      n = dis(gen);
      vector<vector<int>> A(1 << n, vector<int>(1 << n));
      int k = 1;
      for (int i = 0; i < A.size(); ++i) {
        for (int j = 0; j < A[i].size(); ++j) {
          A[i][j] = k++;
        }
      }
      vector<vector<int>> B(A);
      RotateMatrix(&B);
      CheckAnswer(A, B);
    }
  }
  return 0;
}
