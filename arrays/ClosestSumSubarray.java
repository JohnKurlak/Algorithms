/**
 * This program takes an array of unsorted integers and finds a subarray
 * whose values, when summed, come closest to k.
 *
 * It uses a modified version of Kadane's Algorith, and the run-time is
 * O(n).
 *
 * @author      John Kurlak <john@kurlak.com>
 * @date        1/26/2013
 */
public class ClosestSumSubarray {
    /**
     * Runs the program with an example array and k value.
     *
     * @param args      The command-line arguments.
     */
    public static void main(String[] args) {
        int[] list = new int[] { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
        int k = 6;
        boolean inclusive = true;
        int[] subarray = findClosestSumSubarray(list, k, inclusive);
        int subarrayStartIndex = subarray[0];
        int subarrayEndIndex = subarray[1];
        int sum = subarray[2];

        if (subarrayEndIndex < subarrayStartIndex) {
            System.out.println(
                "The subarray with the closest sum to " + k +
                " is an empty array with sum 0."
            );
        } else {
            System.out.println(
                "The subarray with the closest sum to " + k +
                " has indices [" + subarrayStartIndex + ", " +
                subarrayEndIndex + "] and has a sum of " + sum + "."
            );
        }
    }

    /**
     * This method accepts an array of unsorted integers and finds a subarray
     * whose values, when summed, come closest to k.
     *
     * @param list      The array of integers.  Values can be negative or
     *                  non-negative.
     * @param k         The target value.
     * @param inclusive Indicates whether k is a possible value for the
     *                  closest sum.
     * @returns         A triple in the form:
     *                  { subarrayStartIndex, subarrayEndIndex, sum }.
     */
    public static int[] findClosestSumSubarray(int[] list, int k, boolean inclusive) {
        // Make sure list is not null
        if (list == null) {
            return new int[] { 0, -1, 0 };
        }

        int n = list.length;

        // Make sure list is not empty
        if (n == 0) {
            return new int[] { 0, -1, 0 };
        }

        boolean inverseSigns = false;

        // If k is negative, we need to find the minimum sum closest to k.
        // An easy way to do this is to invert each list element and solve
        // the problem as if k were positive. Then, at the end, we can
        // invert the values again so that they remain unmodified.
        if (k < 0) {
            inverseSigns = true;
            k = -k;

            for (int i = 0; i < n; i++) {
                list[i] = -list[i];
            }
        }

        boolean allNegative = true;
        int maxValue = list[0];
        int maxValueIndex = 0;
        int maxSum = 0;
        int prevSumBeforeHere = 0;
        int subarrayStartIndex = 0;
        int subarrayEndIndex = -1;

        for (int i = 0; i < n; i++) {
            int currentNum = list[i];
            int currentSumEndingHere = prevSumBeforeHere + currentNum;

            // Update the previous sum for the next iteration
            if (currentSumEndingHere > 0) {
                prevSumBeforeHere = currentSumEndingHere;
            } else {
                prevSumBeforeHere = 0;
                subarrayStartIndex = i + 1;
            }

            boolean sumSatisfiesBounds = (inclusive)
                ? currentSumEndingHere <= k
                : currentSumEndingHere < k;

            // If the maximum sum that ends on the current index is closer to k
            // than our previous maximum sum, update it
            if (currentSumEndingHere > maxSum && sumSatisfiesBounds) {
                maxSum = currentSumEndingHere;
                subarrayEndIndex = i;
            }

            // If any of the numbers are non-negative, returning a zero-length
            // subarray is fine; if all of the numbers are negative, it is
            // undesirable to return a zero-length subarray
            if (currentNum >= 0) {
                allNegative = false;
            }

            // However, if all of the numbers end up being negative, we will
            // want to return the subarray that contains the largest one
            if (allNegative) {
                maxValue = Math.max(maxValue, currentNum);
                maxValueIndex = i;
            }
        }

        // If we inverted the signs in the beginning, undo that operation
        if (inverseSigns) {
            for (int i = 0; i < n; i++) {
                list[i] = -list[i];
            }

            // Also, invert our answer
            maxSum = -maxSum;
        }

        // If all of the numbers were negative, adjust our answer so that we
        // don't return a zero-length subarray
        if (allNegative) {
            subarrayStartIndex = maxValueIndex;
            subarrayEndIndex = maxValueIndex;
            maxSum = maxValue;
        }

        return new int[] { subarrayStartIndex, subarrayEndIndex, maxSum };
    }
}