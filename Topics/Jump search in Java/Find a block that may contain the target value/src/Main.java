import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextInt();
        }

        int target = scanner.nextInt();

        jumpSearch(array, target);
    }

    public static void jumpSearch(int[] array, int target) {
        if (array[array.length - 1] < target) {
            System.out.println("-1");
            return;
        }

        int jumpLength = (int) Math.sqrt(array.length);

        int currentLeft = jumpLength - 1; // left border of the current block
        int currentRight = 0; // right border of the current block

        while (currentLeft < array.length - 1) {
            if (array[currentLeft] >= target) {
                System.out.printf("%d %d", currentRight, currentLeft);
                return;
            }

            if (array[currentLeft] > target) {
                System.out.printf("%d %d", currentLeft, currentLeft + jumpLength);
                return;
            }

            currentRight = currentLeft + 1;
            currentLeft = Math.min(array.length - 1, currentLeft + jumpLength);
        }

        System.out.printf("%d %d", currentLeft, array.length - 1);
    }
}