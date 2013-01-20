/**
 * This program determines whether an unsorted list of integers can be
 * rearranged into a list of consecutive integers in O(n) time.
 *
 * @author      John Kurlak <john@kurlak.com>
 * @date        12/8/2012
 */
public class ConsecutiveIntegers {
    /**
     * Runs the program with an example list.
     *
     * @param args      The command-line arguments.
     */
    public static void main(String[] args) {
        boolean areConsecutive = areConsecutiveIntegers(new int[] { 46, 45, 47, 48, 49, 50 });

        if (areConsecutive) {
            System.out.println("The integers are consecutive.");
        } else {
            System.out.println("The integers are not consecutive.");
        }
    }

    /**
     * Determines whether the list of integers can be rearranged into a
     * list of consecutive integers in O(n) time.
     *
     * @param list      The list of integers.
     * @return          True if the integers can be rearranged into a list
     *                  of consecutive integers and false otherwise.
     */
    public static boolean areConsecutiveIntegers(int[] list) {
        // Check edge case
        if (list == null) {
            return false;
        }

        int n = list.length;

        // Check additional edge cases
        if (n == 0) {
            return false;
        } else if (n == 1) {
            return true;
        }

        int min = list[0];

        // Find the minimum value in the list
        for (int i = 1; i < n; i++) {
            if (list[i] < min) {
                min = list[i];
            }
        }

        int numProcessed = 0;

        // Iterate over a maximum of n values, placing each list item into
        // its expected index
        for (int i = 0; i < n; i++) {
            int temp = list[i];
            int expectedIndex = temp - min;

            while (expectedIndex != i) {
                if (expectedIndex > n - 1 || numProcessed == n) {
                    return false;
                }

                // Swap list[i] and list[expectedIndex]
                list[i] = list[expectedIndex];
                list[expectedIndex] = temp;

                // Update temp, expectedIndex, and numProcessed
                temp = list[i];
                expectedIndex = temp - min;
                numProcessed++;
            }
        }

        // Verify that the "sorted" list is indeed consecutive
        for (int i = 1; i < n; i++) {
            if (list[i] != list[i - 1] + 1) {
                return false;
            }
        }

        return true;
    }
}