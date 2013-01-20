import java.math.BigDecimal;

/**
 * This program determines whether a given number is Fibonacci number in
 * O(lg n) time.  It works for pretty large numbers.
 *
 * @author      John Kurlak <john@kurlak.com>
 * @date        1/11/2013
 */
public class FibonacciTesterAlgebraicMethod {
    private static BigDecimal zero = BigDecimal.valueOf(0);
    private static BigDecimal one = BigDecimal.valueOf(1);
    private static BigDecimal two = BigDecimal.valueOf(2);
    private static BigDecimal four = BigDecimal.valueOf(4);
    private static BigDecimal five = BigDecimal.valueOf(5);

    /**
     * Runs the program with an example number.
     *
     * @param args      The command-line arguments.
     */
    public static void main(String[] args) {
        BigDecimal num = new BigDecimal("280571172992510140037611932413038677189525");

        if (isFibonacci(num)) {
            System.out.println(num + " is a Fibonacci number.");
        } else {
            System.out.println(num + " is NOT a Fibonacci number.");
        }
    }

    /**
     * Determines whether the given number is a Fibonacci number.
     *
     * @param num   The number to test.
     * @returns     True if the given number is Fibonacci number and false otherwise.
     */
    public static boolean isFibonacci(BigDecimal num) {
        // Check edge case
        if (num.compareTo(zero) <= 0) {
            return false;
        }

        // n is a Fibonacci number if and only if (5n^2 + 4) or (5n^2 - 4) is a perfect square.

        BigDecimal base = num.multiply(num).multiply(five);
        BigDecimal possibility1 = base.add(four);
        BigDecimal possibility2 = base.subtract(four);

        return (isPerfectSquare(possibility1) || isPerfectSquare(possibility2));
    }

    /**
     * Determines whether the given number is a perfect square.
     *
     * @param num   The number to test.
     */
    public static boolean isPerfectSquare(BigDecimal num) {
        return isPerfectSquare(num, BigDecimal.valueOf(0), num.divide(two));
    }

    /**
     * Determines whether the given number is a perfect square using a
     * modified binary search.
     *
     * @param num   The number to test.
     * @param min   The minimum value for the square root of the given number.
     * @param max   The maximum value for the square root of the given number.
     */
    public static boolean isPerfectSquare(BigDecimal num, BigDecimal min, BigDecimal max) {
        while (max.compareTo(min) > 0) {
            BigDecimal mid = max.divide(two).add(min.divide(two));
            BigDecimal midFloor = mid.setScale(0, BigDecimal.ROUND_DOWN);
            BigDecimal currentSquare = midFloor.multiply(midFloor);
            int comparison = currentSquare.compareTo(num);

            if (comparison < 0) {
                min = mid.add(one);
            } else if (comparison > 0) {
                max = mid.subtract(one);
            } else {
                return true;
            }
        }

        return false;
    }
}