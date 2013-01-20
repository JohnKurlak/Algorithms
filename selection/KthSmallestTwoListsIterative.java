/**
 * This program iteratively finds the kth smallest number in the concatenation
 * of two sorted lists in O(lg k) time.
 *
 * @author      John Kurlak <john@kurlak.com>
 * @date        1/17/2013
 */
public class KthSmallestTwoListsIterative {
    /**
     * Runs the program with an example list.
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
     * Iteratively computes the kth smallest of the two lists.
     *
     * @param list1     The first list.
     * @param list2     The second array.
     * @param k         The k value to use.
     * @return          The kth smallest value of the two lists.
     */
    public static Integer kthSmallest(int[] list1, int[] list2, int k) {
        int listSize1 = list1.length;
        int listSize2 = list2.length;
        int startPos1 = 1;
        int startPos2 = 1;
        int endPos1 = k;
        int endPos2 = k;
        int size1 = k;
        int size2 = k;
        int lastPos1 = listSize1;
        int lastPos2 = listSize2;

        // Edge case: k is out of bounds
        if (k < 1 || k > listSize1 + listSize2) {
            return null;
        }

        // Edge case: first list has length 0
        if (listSize1 == 0) {
            return list2[k - 1];
        }

        // Edge case: second list has length 0
        if (listSize2 == 0) {
            return list1[k - 1];
        }

        while (size1 > 2 || size2 > 2) {
            int mid1 = (startPos1 + endPos1) / 2;
            int mid2 = k - mid1;
            int val1;
            int val2;

            if (mid1 > lastPos1) {
                val2 = list2[mid2 - 1];
                val1 = val2 + 1; // "Infinity"
            } else if (mid2 > lastPos2) {
                val1 = list1[mid1 - 1];
                val2 = val1 + 1; // "Infinity"
            } else {
                val1 = list1[mid1 - 1];
                val2 = list2[mid2 - 1];
            }

            // Modify start and end positions in binary search fashion
            if (val1 == val2) {
                return val1;
            } else if (val1 > val2) {
                endPos1 = mid1;
                startPos2 = mid2;
            } else {
                startPos1 = mid1;
                endPos2 = mid2;
            }

            size1 = endPos1 - startPos1 + 1;
            size2 = endPos2 - startPos2 + 1;
            lastPos1 = Math.min(listSize1, endPos1);
            lastPos2 = Math.min(listSize2, endPos2);
        }

        if (k <= 2) {
            int val1 = Math.min(list1[startPos1 - 1], list1[lastPos1 - 1]);
            int val2 = Math.min(list2[startPos2 - 1], list2[lastPos2 - 1]);
            return (k == 1) ? Math.min(val1, val2) : Math.max(val1, val2);
        }

        if (startPos1 == lastPos1) {
            int val1 = list1[startPos1 - 1];
            int val2 = list2[k - startPos1 - 1];
            return Math.max(val1, val2);
        }

        if (startPos2 == lastPos2) {
            int val2 = list2[startPos2 - 1];
            int val1 = list1[k - startPos2 - 1];
            return Math.max(val1, val2);
        }

        int max1 = Math.max(list1[startPos1 - 1], list1[lastPos1 - 1]);
        int max2 = Math.max(list2[startPos2 - 1], list2[lastPos2 - 1]);
        int min = Math.min(max1, max2);

        return min;
    }
}