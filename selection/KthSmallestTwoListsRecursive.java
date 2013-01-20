/**
 * This program recursively finds the kth smallest number in the concatenation
 * of two sorted lists in O(lg k) time.
 *
 * @author      John Kurlak <john@kurlak.com>
 * @date        11/7/2012
 */
public class KthSmallestTwoListsRecursive {
    /**
     * Runs the program with  an example list.
     *
     * @param args      The command-line arguments.
     */
    public static void main(String[] args) {
        int[] list1 = new int[] { 3, 4, 10, 23, 45, 55, 56, 58, 60, 65 };
        int[] list2 = new int[] { 3, 3, 3, 15, 16, 28, 50, 70, 71, 72 };
        int k = 13;

        Integer kthSmallest = kthSmallest(list1, list2, k);

        if (kthSmallest != null) {
            System.out.println(k + "th smallest is " + kthSmallest);
        } else {
            System.out.println("There is no " + k + "th smallest value.");
        }
    }

    /**
     * Recursively computes the kth smallest of the two lists.
     *
     * @param list1     The first list.
     * @param list2     The second array.
     * @param k         The k value to use.
     * @return          The kth smallest value of the two lists.
     */
    public static Integer kthSmallest(int[] list1, int[] list2, int k) {
        // Ensure k is in proper range
        if (k < 1 || k > list1.length + list2.length) {
            return null;
        }

        return kthSmallest(list1, list2, k, 1, k, 1, k);
    }

    /**
     * Recursively computes the kth smallest of the two lists.
     *
     * @param list1     The first list.
     * @param list2     The second array.
     * @param k         The k value to use.
     * @param startPos1 The current starting position within the first list (starting from 1).
     * @param endPos1   The current ending position within the first list (starting from 1).
     * @param startPos2 The current starting position within the second list (starting from 1).
     * @param endPos2   The current ending position within the second list (starting from 1).
     * @return          The kth smallest value of the two lists.
     */
    public static Integer kthSmallest(int[] list1, int[] list2, int k, int startPos1, int endPos1, int startPos2, int endPos2) {
        int size1 = endPos1 - startPos1 + 1;
        int size2 = endPos2 - startPos2 + 1;
        int lastPos1 = Math.min(list1.length, endPos1);
        int lastPos2 = Math.min(list2.length, endPos2);

        // Base case
        if (size1 <= 2 && size2 <= 2) {
            int max1 = Math.max(list1[startPos1 - 1], list1[lastPos1 - 1]);
            int max2 = Math.max(list2[startPos2 - 1], list2[lastPos2 - 1]);
            int min = Math.min(max1, max2);

            return min;
        }

        // Recursive case

        int mid1 = (startPos1 + endPos1) / 2;
        int mid2 = k - mid1;

        if (mid1 > lastPos1) {
            mid2 = k - list1.length;
            mid1 = k - mid2;

            return Math.max(list1[mid1 - 1], list2[mid2 - 1]);
        }

        if (mid2 > lastPos2) {
            mid1 = k - list2.length;
            mid2 = k - mid1;

            return Math.max(list1[mid1 - 1], list2[mid2 - 1]);
        }

        int val1 = list1[mid1 - 1];
        int val2 = list2[mid2 - 1];

        if (val1 == val2) {
            return val1;
        } else if (val1 > val2) {
            endPos1 = mid1;
            startPos2 = mid2;
        } else {
            startPos1 = mid1;
            endPos2 = mid2;
        }

        return kthSmallest(list1, list2, k, startPos1, endPos1, startPos2, endPos2);
    }
}