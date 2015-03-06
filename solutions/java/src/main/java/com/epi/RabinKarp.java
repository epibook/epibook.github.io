public class RabinKarp {
	static int kBase = 26, kMod = 997;

	public static void main(String args[]) {
		System.out.println("Usage: text and search string");

		String t = "GACGCCA";
		String s = "CGC";
		if (args.length == 2) {
			t = args[0];
			s = args[1];
		} else {
			return;
		}
		int index = rabinKarp(t, s);
		if (index >= 0) {
			System.out.printf("Index of first occurrence of %s in %s: %d\n", s,
					t, rabinKarp(t, s));
		} else {
			System.out.println("No match found");
		}
		assert (rabinKarp("GACGCCA", "CGC") == 2);
		assert (rabinKarp("GATACCCATCGAGTCGGATCGAGT", "GAG") == 10);

	}

	// Returns the index of the first character of the substring if found, -1
	// otherwise.`
	public static int rabinKarp(String t, String s) {
		// s is not a substring of t.
		if (s.length() > t.length()) {
			return -1;
		}
		// Hash codes for the substring of t and s.
		int tHash = 0, sHash = 0;
		// The modulo result of kBase^|s|.
		int powerS = 1;
		for (int i = 0; i < s.length(); i++) {
			powerS = i > 0 ? powerS * kBase % kMod : 1;
			tHash = (tHash * kBase + t.charAt(i)) % kMod;
			sHash = (sHash * kBase + s.charAt(i)) % kMod;
		}
		for (int i = s.length(); i < t.length(); i++) {
			// In case of hash collision but two strings are not equal, checks
			// the two substrings are actually equal or not.
			if (tHash == sHash && t.substring(i - s.length(), i).equals(s)) {
				return i - s.length();
			}
			// Uses rolling hash to compute the new hash code.
			tHash -= (t.charAt(i - s.length()) * powerS) % kMod;
			if (tHash < 0) {
				tHash += kMod;
			}
			tHash = (tHash * kBase + t.charAt(i)) % kMod;
		}
		// Tries to match s and t[t.size() - s.size() : t.size() - 1].
		if (tHash == sHash
				&& t.subSequence(t.length() - s.length(), t.length()).equals(s)) {
			return t.length() - s.length();
		}
		// s is not a substring of t.
		return -1;
	}
}
