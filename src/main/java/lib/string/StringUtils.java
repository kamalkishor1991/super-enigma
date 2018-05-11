package lib.string;


import java.util.Arrays;

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

  public static int kmp(String s, String t) {
	  int pa[] = prefixArray(t);
	  int ti = 0;
	  for (int i = 0; i < s.length(); ) {
      if (t.charAt(ti) == s.charAt(i)) {
        ti++;
        i++;
      } else {
        if (ti != 0) ti = pa[ti - 1];
        else i++;
      }
      if (ti >= t.length()) {
        return i - t.length() + 1;
      }
    }
    return -1;
  }

  public static int[] prefixArray(String t) {
    int pArray[] = new int[t.length()];
	  pArray[0] = 0;
	  int j = 0;
    for (int i = 1; i < t.length(); i++) {
      if (t.charAt(i) == t.charAt(j)) {
        j++;
      } else {
        while (t.charAt(j) != t.charAt(i) && j > 0) {
          j = pArray[j - 1];
        }
        if (j != 0) {
          j++;
        }
      }
      pArray[i] = j;
    }
    return pArray;
  }

  public static void main(String args[]) {
    System.out.println(Arrays.toString(prefixArray("acacabacacabacacac")));
    System.out.println(kmp("acacabacacabacacac", "acacabacacabacacac"));
    System.out.println(kmp("xacaacacabacacabacacac", "acacabacacabacacac"));
    System.out.println(kmp("abcabdabc", "abd"));
  }
}
