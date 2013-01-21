/**
 * This program rotates all of the elements in an array left by a given
 * k value.  It runs in O(n) time and uses O(1) additional space (it operates
 * in-place).
 *
 * @author      John Kurlak <john@kurlak.com>
 * @date        1/20/2013
 */
public class RotateArrayLeft {
    /**
     * Runs the program with an example array.
     *
     * @param args      The command-line arguments.
     */
    public static void main(String[] args) {
        int[] array = new int[] { 5, 3, 2, 18, 20 };
        int k = 3;

        printArray(array);
        System.out.println("rotated to the left " + k + " is:");
        rotateArrayLeft(array, k);
        printArray(array);
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
        int origIndex = 0;

        // Place each array item into its rotated index
        for (int i = 0; i < n; i++) {
            int shiftedIndex = (origIndex - k) % n;
            if (shiftedIndex < 0) {
                shiftedIndex += n;
            }

            swap(array, 0, shiftedIndex);
            origIndex = shiftedIndex;
        }
    }

    /**
     * Prints out the contents of an array.
     *
     * @param array     The array.
     */
    public static void printArray(int[] array) {
        System.out.print("{ ");

        if (array == null) {
            System.out.println("}");
            return;
        }

        for (int i = 0; i < array.length; i++) {
            String separator = (i == (array.length - 1)) ? " " : ", ";
            System.out.print(array[i] + separator);
        }

        System.out.println("}");
    }

    /**
     * Swaps two elements in a list.
     *
     * @param list      The list.
     * @param index1    The index of the first element to swap.
     * @param index2    The index of the second element to swap.
     */
    public static void swap(int[] list, int index1, int index2) {
        int temp = list[index1];
        list[index1] = list[index2];
        list[index2] = temp;
    }
}