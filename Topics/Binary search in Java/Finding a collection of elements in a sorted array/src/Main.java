import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        int[] inputArray = new int[n];
        for (int i = 0; i < n; i++) {
            inputArray[i] = scanner.nextInt();
        }

        int k = scanner.nextInt();
        int[] valuesToFind = new int[k];
        for (int i = 0; i < k; i++) {
            valuesToFind[i] = scanner.nextInt();
        }

        for (int i = 0; i < k; i++) {
            System.out.printf("%d ", binarySearch(inputArray, valuesToFind[i], 0, n) + 1);
        }
    }

    public static int binarySearch(int[] array, int value, int left, int right) {
        if (value > array[array.length - 1]) {
            return -2;
        }
        if (left > right) {
            return -2;
        }

        int midpoint = (left + right) >>> 1;
        if (array[midpoint] == value) {
            return midpoint;
        } else if (value > array[midpoint]) {
            return binarySearch(array, value, midpoint + 1, right);
        } else {
            return binarySearch(array, value, left, midpoint - 1);
        }
    }
}