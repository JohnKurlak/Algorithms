import java.util.Arrays;
import java.util.ArrayList;

/**
 * According to Goldbach's Conjecture, every even integer greater than
 * two can be expressed as the sum of two primes.  This program lists
 * all pairs of primes that add up to a given even number in
 * O(n lg lg n) time.
 *
 * @author      John Kurlak <john@kurlak.com>
 * @date        1/10/2013
 */
public class GoldbachsConjecture {
    /**
     * Runs the program with an example number.
     *
     * @param args      The command-line arguments.
     */
    public static void main(String[] args) {
        int num = 56;
        ArrayList<Pair> pairs = getPrimeSummands(100);

        for (Pair pair : pairs) {
            System.out.println("<" + pair.num1 + ", " + pair.num2 + ">");
        }
    }

    /**
     * Finds all pairs of prime numbers that add up to the given number.
     * It uses the Sieve of Eratosthenes for fast computation of prime numbers.
     *
     * @param num       An even number greater than 2.
     * @return          A list of pairs of prime numbers that add up to the
     *                  given number.
     */
    public static ArrayList<Pair> getPrimeSummands(int num) {
        ArrayList<Pair> pairs = new ArrayList<Pair>();

        // Check for invalid input
        if (num < 4 || num % 2 != 0) {
            return pairs;
        }

        // Compute the Sieve of Eratosthenes
        int halfNum = num / 2;
        boolean[] primeSieve = computePrimeSieve(num);

        // Loop through prime numbers
        for (int current = 2; current <= halfNum; current++) {
            if (!primeSieve[current]) {
                continue;
            }

            // Determine which number, when added to the current prime, equals the given number
            int needed = num - current;

            // If that number is a prime number, add it to our solution
            if (primeSieve[needed]) {
                pairs.add(new Pair(current, needed));
            }
        }

        return pairs;
    }

    /**
     * Computes the Sieve of Eratosthenes.
     *
     * @param num       The maximum number that we need to test primality for.
     * @return          A boolean array where true means the current index is a prime number.
     */
    public static boolean[] computePrimeSieve(int num) {
        boolean[] primeSieve = new boolean[num + 1];
        Arrays.fill(primeSieve, true);
        primeSieve[0] = primeSieve[1] = false;

        for (int i = 2; i <= num; i++) {
            if (!primeSieve[i]) {
                continue;
            }

            // Set any multiples of the current number as non-primes
            for (int j = 2, product = i * j; product <= num; j++, product = i * j) {
                primeSieve[product] = false;
            }
        }

        return primeSieve;
    }

    /**
     * This class represents a pair of integers.
     */
    private static class Pair {
        int num1 = 0;
        int num2 = 0;

        /**
         * Creates a new pair.
         *
         * @param num1      The first number.
         * @param num2      The second number.
         */
        public Pair(int num1, int num2) {
            this.num1 = num1;
            this.num2 = num2;
        }
    }
}