// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <random>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::ostream_iterator;
using std::random_device;
using std::uniform_int_distribution;
using std::vector;

void PrintMatrix(const vector<vector<int>>& A) {
  for (int i = 0; i < A.size(); ++i) {
    // copy(A[i].begin(), A[i].end(), ostream_iterator<int>(cout, " "));
    for (int j = 0; j < A.size(); ++j) {
      cout << "A[" << i << "," << j << "] = " << A[i][j] << "  ";
    }
    cout << endl;
  }
}

void CheckAnswer(const vector<vector<int>>& A) {
  int k = 1;
  for (int j = A.size() - 1; j >= 0; --j) {
    for (int i = 0; i < A.size(); ++i) {
      assert(k++ == A[i][j]);
    }
  }
}

// @include
void RotateMatrix(vector<vector<int>>* square_matrix_ptr) {
  vector<vector<int>>& square_matrix = *square_matrix_ptr;
  const int matrix_size = square_matrix.size() - 1;
  for (int i = 0; i < (square_matrix.size() / 2); ++i) {
    for (int j = i; j < matrix_size - i; ++j) {
      // Perform a 4-way exchange.
      int temp1 = square_matrix[matrix_size - j][i];
      int temp2 = square_matrix[matrix_size - i][matrix_size - j];
      int temp3 = square_matrix[j][matrix_size - i];
      int temp4 = square_matrix[i][j];
      square_matrix[i][j] = temp1;
      square_matrix[matrix_size - j][i] = temp2;
      square_matrix[matrix_size - i][matrix_size - j] = temp3;
      square_matrix[j][matrix_size - i] = temp4;
    }
  }
}
// @exclude

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
    PrintMatrix(A);
    RotateMatrix(&A);
    CheckAnswer(A);
    PrintMatrix(A);
  } else {
    default_random_engine gen((random_device())());
    uniform_int_distribution<int> dis(1, 10);
    for (int times = 0; times < 100; ++times) {
      n = dis(gen);
      vector<vector<int>> A(1 << n, vector<int>(1 << n));
      int k = 1;
      for (int i = 0; i < A.size(); ++i) {
        for (int j = 0; j < A[i].size(); ++j) {
          A[i][j] = k++;
        }
      }
      RotateMatrix(&A);
      CheckAnswer(A);
    }
  }
  return 0;
}
