import java.util.Arrays;

/**
 * Given a sorted list of integers, this program finds the smallest k values,
 * each of which can either be a number from the list or the sum of any two or
 * three numbers in the list.  It runs in O(n^3 lg n) time.
 */
public class KSmallestWithCombosOf2And3 {
    /**
     * Runs the program with an example list.
     *
     * @param args      The command-line arguments.
     */
    public static void main(String[] args) {
        int[] A = { 3, 4, 5, 15, 19, 20, 25 };
        Integer[] B = KSmallestWithCombosOf2And3(A, 63);

        System.out.print("[ ");

        for (int i = 0; i < B.length; i++) {
            String next = (i == B.length - 1) ? " " : ", ";
            System.out.print(B[i] + next);
        }

        System.out.println("]");
    }

    /**
     * Iteratively find the k smallest numbers.
     *
     * @param list  The list of numbers.
     * @param k     The k value to use.
     * @return      An Integer array of the k smallest numbers.
     */
    public static Integer[] KSmallestWithCombosOf2And3(int[] list, int k) {
        // Check edge cases
        if (k < 1 || list == null || list.length == 0) {
            return new Integer[0];
        }

        Integer[] result = new Integer[k];
        int[][] lists = new int[][] { list, getCombosOf2(list), getCombosOf3(list) };
        int[] ptrs = { 0, 0, 0 };

        // Merge the three lists in sorted order
        for (int i = 0; i < k; i++) {
            int min = 0;
            int minIndex = 0;
            boolean minAssigned = false;

            for (int j = 0; j < 3; j++) {
                if (ptrs[j] < lists[j].length && (lists[j][ptrs[j]] < min || !minAssigned)) {
                    min = lists[j][ptrs[j]];
                    minIndex = j;
                    minAssigned = true;
                }
            }

            if (minAssigned) {
                ptrs[minIndex]++;
                result[i] = min;
            } else {
                result[i] = null;
            }
        }

        return result;
    }

    /**
     * Iteratively find the k smallest numbers that are a sum of any two
     * numbers in the list.
     *
     * @param list  The list of numbers.
     * @return      An array of the k smallest numbers.
     */
    public static int[] getCombosOf2(int[] list) {
        int n = list.length;
        int numCombos = n * (n - 1) / 2;
        int[] result = new int[numCombos];

        for (int i = 0, c = 0; i < n; i++) {
            for (int j = 0; j < i; j++, c++) {
                result[c] = list[i] + list[j];
            }
        }

        Arrays.sort(result);

        return result;
    }

    /**
     * Iteratively find the k smallest numbers that are a sum of any three
     * numbers in the list.
     *
     * @param list  The list of numbers.
     * @return      An array of the k smallest numbers.
     */
    public static int[] getCombosOf3(int[] list) {
        int n = list.length;
        int numCombos = n * (n - 1) * (n - 2) / 6;
        int[] result = new int[numCombos];

        for (int i = 0, c = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                for (int k = 0; k < j; k++, c++) {
                    result[c] = list[i] + list[j] + list[k];
                }
            }
        }

        Arrays.sort(result);

        return result;
    }
}