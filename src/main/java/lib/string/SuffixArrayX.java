package lib.string;

public class SuffixArrayX {
    private static final int CUTOFF = 5;   // cutoff to insertion sort (any value between 0 and 12)

    private final char[] text;
    private final int[] index;   // index[i] = j means text.substring(j) is ith largest suffix
    private final int N;         // number of characters in text

    /**
     * Initializes a suffix array for the given <tt>text</tt> string.
     *
     * @param text the input string
     */
    public SuffixArrayX(String text) {
        N = text.length();
        text = text + '\0';
        this.text = text.toCharArray();
        this.index = new int[N];
        for (int i = 0; i < N; i++)
            index[i] = i;

        // shuffle

        sort(0, N - 1, 0);
    }

    public int[] getSuffixArray() {
        return index;
    }

    // 3-way string quicksort lo..hi starting at dth character
    private void sort(int lo, int hi, int d) {

        // cutoff to insertion sort for small subarrays
        if (hi <= lo + CUTOFF) {
            insertion(lo, hi, d);
            return;
        }

        int lt = lo, gt = hi;
        char v = text[index[lo] + d];
        int i = lo + 1;
        while (i <= gt) {
            int t = text[index[i] + d];
            if (t < v) exch(lt++, i++);
            else if (t > v) exch(i, gt--);
            else i++;
        }

        // a[lo..lt-1] < v = a[lt..gt] < a[gt+1..hi]. 
        sort(lo, lt - 1, d);
        if (v > 0) sort(lt, gt, d + 1);
        sort(gt + 1, hi, d);
    }

    // sort from a[lo] to a[hi], starting at the dth character
    private void insertion(int lo, int hi, int d) {
        for (int i = lo; i <= hi; i++)
            for (int j = i; j > lo && less(index[j], index[j - 1], d); j--)
                exch(j, j - 1);
    }

    // is text[i+d..N) < text[j+d..N) ?
    private boolean less(int i, int j, int d) {
        if (i == j) return false;
        i = i + d;
        j = j + d;
        while (i < N && j < N) {
            if (text[i] < text[j]) return true;
            if (text[i] > text[j]) return false;
            i++;
            j++;
        }
        return i > j;
    }

    // exchange index[i] and index[j]
    private void exch(int i, int j) {
        int swap = index[i];
        index[i] = index[j];
        index[j] = swap;
    }

    /**
     * Returns the length of the input string.
     *
     * @return the length of the input string
     */
    public int length() {
        return N;
    }


    /**
     * Returns the index into the original string of the <em>i</em>th smallest suffix.
     * That is, <tt>text.substring(sa.index(i))</tt> is the <em>i</em> smallest suffix.
     *
     * @param i an integer between 0 and <em>N</em>-1
     * @return the index into the original string of the <em>i</em>th smallest suffix
     * @throws IndexOutOfBoundsException unless 0 &le; <em>i</em> &lt; <Em>N</em>
     */
    public int index(int i) {
        if (i < 0 || i >= N) throw new IndexOutOfBoundsException();
        return index[i];
    }

    /**
     * Returns the length of the longest common prefix of the <em>i</em>th
     * smallest suffix and the <em>i</em>-1st smallest suffix.
     *
     * @param i an integer between 1 and <em>N</em>-1
     * @return the length of the longest common prefix of the <em>i</em>th
     * smallest suffix and the <em>i</em>-1st smallest suffix.
     * @throws IndexOutOfBoundsException unless 1 &le; <em>i</em> &lt; <em>N</em>
     */
    public int lcp(int i) {
        if (i < 1 || i >= N) throw new IndexOutOfBoundsException();
        return lcp(index[i], index[i - 1]);
    }

    // longest common prefix of text[i..N) and text[j..N)
    private int lcp(int i, int j) {
        int length = 0;
        while (i < N && j < N) {
            if (text[i] != text[j]) return length;
            i++;
            j++;
            length++;
        }
        return length;
    }

    /**
     * Returns the <em>i</em>th smallest suffix as a string.
     *
     * @param i the index
     * @return the <em>i</em> smallest suffix as a string
     * @throws IndexOutOfBoundsException unless 0 &le; <em>i</em> &lt; <Em>N</em>
     */
    public String select(int i) {
        if (i < 0 || i >= N) throw new IndexOutOfBoundsException();
        return new String(text, index[i], N - index[i]);
    }

    /**
     * Returns the number of suffixes strictly less than the <tt>query</tt> string.
     * We note that <tt>rank(select(i))</tt> equals <tt>i</tt> for each <tt>i</tt>
     * between 0 and <em>N</em>-1.
     *
     * @param query the query string
     * @return the number of suffixes strictly less than <tt>query</tt>
     */
    public int rank(String query) {
        int lo = 0, hi = N - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = compare(query, index[mid]);
            if (cmp < 0) hi = mid - 1;
            else if (cmp > 0) lo = mid + 1;
            else return mid;
        }
        return lo;
    }

    // is query < text[i..N) ?
    private int compare(String query, int i) {
        int M = query.length();
        int j = 0;
        while (i < N && j < M) {
            if (query.charAt(j) != text[i]) return query.charAt(j) - text[i];
            i++;
            j++;

        }
        if (i < N) return -1;
        if (j < M) return +1;
        return 0;
    }


}