public class Parity3 {
  private static short[] precomputedParity;

  static {
    precomputedParity = new short[1 << 16];
    for (int i = 0; i < (1 << 16); ++i) {
      precomputedParity[i] = Parity1.parity(i);
    }
  }

  // @include
  public static short parity(long x) {
    final int MASK_SIZE = 16;
    final int BIT_MASK = 0xFFFF;
    // clang-format off
    return (short) (
        precomputedParity[(int)((x >>> (3 * MASK_SIZE)) & BIT_MASK)]
        ^ precomputedParity[(int)((x >>> (2 * MASK_SIZE)) & BIT_MASK)]
        ^ precomputedParity[(int)((x >>> MASK_SIZE) & BIT_MASK)]
        ^ precomputedParity[(int)(x & BIT_MASK)]);
    // clang-format on
  }
  // @exclude
}
