import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextInt();
        }
        boolean isPresent = false;
        for (int i = 0; i < n; i++) {
            isPresent = array[i] == binarySearch(array, i, 0, n - 1);
            if (isPresent) {
                break;
            }
        }

        System.out.println(isPresent);
    }

    public static int binarySearch(int[] array, int value, int left, int right) {
        if (right < left) {
            return -1;
        }

        int midpoint = (right + left) >>> 1;
        if (array[midpoint] == value) {
            return midpoint;
        } else if (value > array[midpoint]) {
            return binarySearch(array, value, midpoint + 1, right);
        } else {
            return binarySearch(array, value, left, midpoint - 1);
        }
    }
}