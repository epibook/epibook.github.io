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

void RotateMatrix(vector<vector<int>>* A_ptr) {
  auto& A = *A_ptr;
  for (int i = 0; i < (A.size() / 2); ++i) {
    for (int j = i; j < A.size() - i - 1; ++j) {
      int temp = A[i][j];
      A[i][j] = A[A.size() - 1 - j][i];
      A[A.size() - 1 - j][i] = A[A.size() - 1 - i][A.size() - 1 - j];
      A[A.size() - 1 - i][A.size() - 1 - j] = A[j][A.size() - 1 - i];
      A[j][A.size() - 1 - i] = temp;
    }
  }
}

// @include
class RotatedMatrix {
 public:
  explicit RotatedMatrix(vector<vector<int>>* square_matrix)
      : square_matrix_(*square_matrix) {}

  int ReadEntry(int i, int j) const {
    return square_matrix_[square_matrix_.size() - 1 - j][i];
  }

  void WriteEntry(int i, int j, int v) {
    square_matrix_[square_matrix_.size() - 1 - j][i] = v;
  }

 private:
  vector<vector<int>>& square_matrix_;
};
// @exclude

void CheckAnswer(vector<vector<int>> A, const vector<vector<int>>& B) {
  RotatedMatrix rA(&A);
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
