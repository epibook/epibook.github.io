package com.epi.utils;

public class BinaryOperators {
  public static BinaryOperator<Double> ADD = new BinaryOperator<Double>() {
    @Override
    public Double apply(Double arg1, Double arg2) {
      // skip nulls
      return (arg1 == null ? 0 : arg1) + (arg2 == null ? 0 : arg2);
    }
  };

  public static BinaryOperator<Integer> MULTIPLIES =
      new BinaryOperator<Integer>() {

        @Override
        public Integer apply(Integer arg1, Integer arg2) {
          return (arg1 == null ? 1 : arg1) * (arg2 == null ? 1 : arg2);
        }
      };
}
