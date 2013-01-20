import java.math.BigDecimal;

/**
 * This program determines whether a given number is Fibonacci number in
 * O(lg lg n) time.  It works for very large numbers.  The original algorithm
 * was devised by Anders Kaseorg based on matrix exponentiation.
 *
 * @author      John Kurlak <john@kurlak.com>
 * @date        1/16/2013
 */
public class FibonacciTesterMatrixMethod {
    private static BigDecimal zero = BigDecimal.valueOf(0);
    private static BigDecimal one = BigDecimal.valueOf(1);
    private static BigDecimal two = BigDecimal.valueOf(2);

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
     * @param n     The number to test.
     * @returns     True if the given number is Fibonacci number and false otherwise.
     */
    public static boolean isFibonacci(BigDecimal n) {
        // Check edge case
        if (n.compareTo(zero) <= 0) {
            return false;
        }

        BigDecimal[] outputs1 = unFib(one, one, n);
        BigDecimal a = outputs1[1];

        return n.compareTo(a) == 0;
    }

    /**
     * Runs the unFib sequence.
     *
     * @param a     The value fib(i).
     * @param b     The value fib(i + 1).
     * @param n     The number to test.
     * @returns     The triple { k, fib(i * k), fib(i * k + 1) } such that fib (i * k) <= n < fib((i + 1) * k).
     */
    private static BigDecimal[] unFib(BigDecimal a, BigDecimal b, BigDecimal n) {
        if (n.compareTo(a) < 0) {
            return new BigDecimal[] { zero, zero, one };
        }

        BigDecimal[] inputs1 = fibPlus(a, b, a, b);
        BigDecimal[] outputs1 = unFib(inputs1[0], inputs1[1], n);
        BigDecimal k = outputs1[0];
        BigDecimal c = outputs1[1];
        BigDecimal d = outputs1[2];

        BigDecimal[] outputs2 = fibPlus(a, b, c, d);
        BigDecimal e = outputs2[0];
        BigDecimal f = outputs2[1];

        if (n.compareTo(e) < 0) {
            return new BigDecimal[] { two.multiply(k), c, d };
        }

        return new BigDecimal[] { two.multiply(k).add(one), e, f };
    }

    /**
     * Runs the fibPlus sequence.
     *
     * @param a     The value fib(i).
     * @param b     The value fib(i + 1).
     * @param c     The value fib(j).
     * @param d     The value fib(j + 1).
     * @returns     The tuple { fib(i + j), fib(i + j + 1) }.
     */
    private static BigDecimal[] fibPlus(BigDecimal a, BigDecimal b, BigDecimal c, BigDecimal d) {
        BigDecimal bd = b.multiply(d);

        return new BigDecimal[] {
            bd.subtract(b.subtract(a).multiply(d.subtract(c))),
            a.multiply(c).add(bd)
        };
    }
}