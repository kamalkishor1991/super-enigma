package lib.math;


import java.util.ArrayList;
import java.util.Arrays;

public class MathUtils {

    public static long pow(long a, long b, long MOD) {
        long ans = 1;
        while (b != 0) {
            if (b % 2 == 1) {
                ans = (ans * a) % MOD;
            }
            b /= 2;
            a = a * a;
            a %= MOD;
        }
        return ans % MOD;
    }


    public static long pow(long a, long b) {
        long ans = 1;
        while (b != 0) {
            if (b % 2 == 1) {
                ans = (ans * a);
            }
            b /= 2;
            a = a * a;

        }
        return ans;
    }

    public static int gcd(int a, int b) {
        while (b != 0) {
            int t = b;
            b = a % b;
            a = t;
        }
        return a;
    }

    public static long gcd(long a, long b) {
        while (b != 0) {
            long t = b;
            b = a % b;
            a = t;
        }
        return a;
    }

    public static int lcm(int a, int b) {
        return (int) (((long) a) * b) / gcd(a, b);
    }

    public static long lcm(long a, long b) {
        return (a * b) / gcd(a, b);
    }


    public static boolean nextPerm(int a[]) {
        int si = -1;
        int ei = -1;
        //find largest index
        for (int i = a.length - 2; i >= 0; i--) {
            if (a[i] < a[i + 1]) {
                si = i;
                break;
            }
        }
        if (si == -1) return false;
        for (int i = a.length - 1; i >= si + 1; i--) {
            if (a[i] > a[si]) {
                ei = i;
                break;
            }
        }
        int t = a[si];
        a[si] = a[ei];
        a[ei] = t;
        //swap all element after si(reverse)
        for (int i = 0; i <= (a.length - si - 2) / 2; i++) {
            t = a[si + i + 1];
            a[si + i + 1] = a[a.length - 1 - i];
            a[a.length - 1 - i] = t;
        }
        return true;
    }


    public static boolean nextPerm(long a[]) {
        int si = -1;
        int ei = -1;
        //find largest index
        for (int i = a.length - 2; i >= 0; i--) {
            if (a[i] < a[i + 1]) {
                si = i;
                break;
            }
        }
        if (si == -1) return false;
        for (int i = a.length - 1; i >= si + 1; i--) {
            if (a[i] > a[si]) {
                ei = i;
                break;
            }
        }
        long t = a[si];
        a[si] = a[ei];
        a[ei] = t;
        //swap all element after si(reverse)
        for (int i = 0; i <= (a.length - si - 2) / 2; i++) {
            t = a[si + i + 1];
            a[si + i + 1] = a[a.length - 1 - i];
            a[a.length - 1 - i] = t;
        }
        return true;
    }

    /**
     * O(sqrt(N))
     *
     * @param n Number
     * @return Number of relative prime numbers for n
     * //Not prop tested.
     */
    public long countTotientFunction(long n) {
        long v = 1;
        long nn = n;
        long d = 1;
        for (long i = 2; i * i <= nn; i += i == 2 ? 1 : 2) {
            if (n % i == 0) {
                while (n % i == 0) {
                    n /= i;
                }
                v *= (i - 1);
                d *= i;
                //System.out.println(i);
            }
        }
        if (n > 2) {
            v *= (n - 1);
            d *= n;
        }
        return (v * nn / d);
    }

    public static long fact(long n, long MOD) {
        long ans = 1;
        n = n % MOD;
        while (n != 0) {
            ans *= n;
            ans %= MOD;
            n--;
        }
        return ans;
    }


    public static long inverse(long n, int MOD) {
        return pow(n, MOD - 2, MOD);
    }


    public static long max(long a, long... b) {
        long max = a;
        for (long el : b) max = Math.max(max, el);
        return max;
    }

    public static int max(int a, int... b) {
        int max = a;
        for (int el : b) max = Math.max(max, el);
        return max;
    }

    public static int min(int a, int... b) {
        int min = a;
        for (int el : b) min = Math.min(min, el);
        return min;
    }

    public static long numberOfDivisors(int n) {
        long ans = 1;
        for (int i = 2; i * i <= n; i++) {
            int count = 0;
            while (n % i == 0) {
                n /= i;
                count++;
            }
            ans *= (count + 1);
        }
        return ans;
    }

    public static boolean[] primesSieveOfEratosthenes(int n) {
        boolean isPrime[] = new boolean[n + 1];
        Arrays.fill(isPrime, true);
        for (int i = 2; i * i <= n; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= n; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        return isPrime;
    }

    public static ArrayList<Integer> primes(int n) {
        boolean isPrime[] = MathUtils.primesSieveOfEratosthenes(n);
        ArrayList<Integer> primes = new ArrayList<>();
        for (int i = 2; i < isPrime.length; i++) {
            if (isPrime[i]) primes.add(i);
        }
        return primes;
    }
}
