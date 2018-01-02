package lib.lib.string;


public class StringUtils {

	/**
	 * Longest substring using DP O(n*m) time O(m + n) space.
	 * @return Length of longest match
	 *
	 */
	public static int longestSubString(String s, String t) {
		if (s.length() == 0 || t.length() == 0) {
			return 0;
		}

		int m = s.length();
		int n = t.length();
		int cost = 0;
		int maxLen = 0;
		int[] p = new int[n];
		int[] d = new int[n];

		for (int i = 0; i < m; ++i) {
			for (int j = 0; j < n; ++j) {
				// calculate cost/score
				if (s.charAt(i) != t.charAt(j)) {
					cost = 0;
				} else {
					if ((i == 0) || (j == 0)) {
						cost = 1;
					} else {
						cost = p[j - 1] + 1;
					}
				}
				d[j] = cost;

				if (cost > maxLen) {
					maxLen = cost;
				}
			} // for {}

			int[] swap = p;
			p = d;
			d = swap;
		}

		return maxLen;
	}
}
