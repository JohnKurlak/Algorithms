import java.util.Arrays;

/**
 * This program rotates all of the elements in an array left by a given k
 * value.  It runs in O(n) time and uses O(1) additional space (it operates
 * in-place).
 *
 * @author      John Kurlak <john@kurlak.com>
 * @date        5/30/2013
 */
public class JugglingAlgorithm {
    /**
     * Runs the program with an example array.
     *
     * @param args      The command-line arguments.
     */
    public static void main(String[] args) {
        int[] array = new int[] { 1, 2, 3, 4, 5, 6, 7 };
        int k = 2;

        System.out.println(Arrays.toString(array));
        System.out.println("rotated to the left " + k + " is:");
        rotateArrayLeft(array, k);
        System.out.println(Arrays.toString(array));
    }

    /**
     * Rotates all of the elements in an array left by the given k value.
     * If k is negative, it rotates the elements in the array right.
     * This method modifies the array in place, so it does not return
     * anything.
     *
     * @param array The array to shift.
     * @param k     The number of indices by which to shift the array.
     */
    public static void rotateArrayLeft(int[] array, int k) {
        if (array == null) {
            return;
        }

        int n = array.length;

        // Handle negative k values (rotate to right)
        if (k < 0) {
            k = n - Math.abs(k);
        }

        // Ensure k is in interval [0, n)
        k = ((k % n) + n) % n;

        // Perform juggling algoritm
        for (int i = 0, gcd = gcd(k, n); i < gcd; i++) {
            int temp = array[i];
            int j = i;

            while (true) {
                int p = j + k;

                if (p >= n) {
                    p = p - n;
                }

                if (p == i) {
                    break;
                }

                array[j] = array[p];
                j = p;
            }

            array[j] = temp;
        }
    }

    /**
     * Uses Euclid's algorithm to find the greatest common divisor of
     * two numbers.
     *
     * @param a     The first number.
     * @param b     The second number.
     * @returns     The great common divisor of `a` and `b`.
     */
    public static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return gcd(b, a % b);
        }
    }
}