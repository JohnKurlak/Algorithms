/**
 * This program counts the number of bits set to one in an integer.
 * If n is the number of bits in the original number, then this runs
 * in O(n^2) time.  If n is the value of the number, then this runs
 * in O(lg^2 n) time.
 *
 * @author      John Kurlak <john@kurlak.com>
 * @date        1/20/2013
 */
public class CountOneBits {
    public static void main(String[] args) {
        int num = 30;
        System.out.println("There are " + countOneBits(num) + " bits in the binary representation of " + num + ".");
    }

    public static int countOneBits(int num) {
        int count = 0;

        while (num > 0) {
            count++;

            // Shift number right until least significant 1 is removed
            num = num & (num - 1);
        }

        return count;
    }
}