package com.epi;

/*
   @slug
    parity

    @title
    Parity

    @context
    The parity of a binary word is 1 if the number of 1s in the word is odd;
   otherwise, it is 0.
    For example, the parity of 1011 is 1, and the parity of 10001000 is 0.
    <p>
    Parity checks are used to detect single bit errors in data storage and
   communication.
    It is fairly straightforward to write code that computes the parity of a
   single 64-bit word.
    <p>

    @summary
    Compute the parity of a long.

    @problem
    How would you compute the parity of a very large number of 64-bit words?

    @hint
    Be prepared to mask and shift.

    @hint2
    Think about the case where there are many checks.

 */

public class Parity1 {
  // @include
  // @judge-include-display
  public static short parity(long x) {
    // @judge-exclude-display
    short result = 0;
    while (x != 0) {
      result ^= (x & 1);
      // clang-format off
      x >>>= 1;
      // clang-format on
    }
    return result;
    // @judge-include-display
  }
  // @judge-exclude-display
  // @exclude

  static String longToBinaryString(long L) {
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < Long.numberOfLeadingZeros(L); i++) {
      sb.append('0');
    }
    sb.append(Long.toBinaryString(L));
    return sb.toString();
  }

  static void unitTest(long L, short expectedParity) {
    short got = parity(L);
    if (got != expectedParity) {
      System.err.println("Wrong result for " + longToBinaryString(L));
      System.err.println("Expected " + expectedParity);
      System.err.println("Got " + got);
      System.exit(-1);
    }
  }

  // slowest implementation, bit by bit
  private static short parityBitByBit(long x) {
    short result = 0;
    for (int i = 0; i < 64; i++) {
      result ^= 1 & (x >> i);
    }
    return result;
  }

  private static short parityBitByBitSmart(long x) {
    short result = 0;
    while (x != 0) {
      result ^= 1;
      x &= (x - 1); // Drops the lowest set bit of x.
    }
    return result;
  }

  // very fast implementation, uses associativity
  private static short parityAssoc(long x) {
    x ^= x >>> 32;
    x ^= x >>> 16;
    x ^= x >>> 8;
    x ^= x >>> 4;
    x ^= x >>> 2;
    x ^= x >>> 1;
    return (short)(x & 0x1);
  }

  // another very fast implementation, uses lookuptable
  private static short[] precomputedParity;

  static {
    precomputedParity = new short[1 << 16];
    for (int i = 0; i < (1 << 16); ++i) {
      precomputedParity[i] = Parity1.parity(i);
    }
  }

  public static short parityTable(long x) {
    final int WORD_SIZE = 16;
    final int BIT_MASK = 0xFFFF;
    // final int BIT_MASK = 0xFF;
    // clang-format off
    ///*
    int offset = WORD_SIZE;
    short result = precomputedParity[(int)(x & BIT_MASK)];
    x = x >>> offset;
    result ^= precomputedParity[(int)(x) & BIT_MASK];
    x = x >>> offset;
    result ^= precomputedParity[(int)(x) & BIT_MASK];
    x = x >>> offset;
    result ^= precomputedParity[(int)(x) & BIT_MASK];
    return result;
    //*/
    
    /*
    return (short) (
       precomputedParity[(int)((x >>> (3 * WORD_SIZE)) & BIT_MASK)]
       ^ precomputedParity[(int)((x >>> (2 * WORD_SIZE)) & BIT_MASK)]
       ^ precomputedParity[(int)((x >>> WORD_SIZE) & BIT_MASK)]
       ^ precomputedParity[(int)(x & BIT_MASK)]
       ^ precomputedParity[(int)((x >>> (4 * WORD_SIZE)) & BIT_MASK)]
       ^ precomputedParity[(int)((x >>> (5 * WORD_SIZE)) & BIT_MASK)]
       ^ precomputedParity[(int)((x >>> (6 * WORD_SIZE)) & BIT_MASK)]
       ^ precomputedParity[(int)((x >>> (7 * WORD_SIZE)) & BIT_MASK)]);
       */
    // clang-format on
  }

  static long[] testcase;
  static int N = 1000000;
  static {
    testcase = new long[N];
    for (int i = 0; i < N; i++) {
      testcase[i] = (i | i << 16 | ~(i << 32) | i << 48);
      // testcase[i] = i;
    }
  }
  static final long SCALE = 1000000L;

  private static long stressTestSolution() {
    long startTime = System.nanoTime();
    long totalXor = 0;
    for (int i = 0; i < N; i++) {
      totalXor ^= parity(testcase[i]);
    }
    long finishTime = System.nanoTime();
    return finishTime - startTime;
  }

  private static long stressTestBitByBit() {
    long startTime = System.nanoTime();
    long totalXor = 0;
    for (int i = 0; i < N; i++) {
      totalXor ^= parityBitByBit(testcase[i]);
    }
    long finishTime = System.nanoTime();
    return finishTime - startTime;
  }

  private static long stressTestBitByBitSmart() {
    long startTime = System.nanoTime();
    long totalXor = 0;
    for (int i = 0; i < N; i++) {
      totalXor ^= parityBitByBitSmart(testcase[i]);
    }
    long finishTime = System.nanoTime();
    return finishTime - startTime;
  }

  private static long stressTestTable() {
    long startTime = System.nanoTime();
    long totalXor = 0;
    for (int i = 0; i < N; i++) {
      totalXor ^= parityTable(testcase[i]);
    }
    long finishTime = System.nanoTime();
    return finishTime - startTime;
  }

  private static long stressTestAssoc() {
    long startTime = System.nanoTime();
    long totalXor = 0;
    for (int i = 0; i < N; i++) {
      totalXor ^= parityAssoc(testcase[i]);
      // inlining to see perf diff
      // long x = testcase[i];
      // x ^= x >>> 32;
      // x ^= x >>> 16;
      // x ^= x >>> 8;
      // x ^= x >>> 4;
      // x ^= x >>> 2;
      // x ^= x >>> 1;
      // totalXor ^= (short)(x & 0x1);
    }
    long finishTime = System.nanoTime();
    return finishTime - startTime;
  }

  public static void compareApproaches() {
    long bitByBitSolutionTime = stressTestBitByBit();
    long bitByBitSmartSolutionTime = stressTestBitByBitSmart();
    long tableSolutionTime = stressTestTable();
    long assocSolutionTime = stressTestAssoc();

    System.out.println(
        "bit-by-bit, bit-smart, table, assoc = " + bitByBitSolutionTime / SCALE
        + ", " + bitByBitSmartSolutionTime / SCALE + ", "
        + tableSolutionTime / SCALE + ", " + assocSolutionTime / SCALE);
  }

  private static void stressTest() {
    long bitByBitSolutionTime = stressTestBitByBit();
    long userSolutionTime = stressTestSolution();
    long tableSolutionTime = stressTestTable();
    if (userSolutionTime > 2 * tableSolutionTime) {
      System.err.println("Your program fails because it's too slow.");
      System.err.println("Your program took " + userSolutionTime / SCALE
                         + " milliseconds for " + N + " inputs.");
      System.err.println("Your target time should be less than "
                         + 2 * tableSolutionTime / SCALE + " milliseconds.");
      System.exit(-1);
    }
  }

  public static void main(String[] args) {
    // compareApproaches();
    unitTest(
        0b1000000000000000000000000000000000000000000000000000000000000000L,
        (short)1);
    unitTest(
        0b1000000000000000000000000000000000000000000000000000000000000001L,
        (short)0);
    unitTest(
        0b0000000000000000000000000000000000000000000000000000000000000001L,
        (short)1);
    unitTest(
        0b1111111111111111111111111111111111111111111111111111111111111111L,
        (short)0);
    unitTest(
        0b0111111111111111111111111111111111111111111111111111111111111111L,
        (short)1);
    unitTest(
        0b1010000101000101101000010100010110100001010001011010000101000101L,
        (short)0);
    stressTest();
    System.out.println("You passed all tests!");
  }
}
