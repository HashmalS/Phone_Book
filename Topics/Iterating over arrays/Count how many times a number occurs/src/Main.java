import java.util.Arrays;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] array = new int[scanner.nextInt()];
        Arrays.setAll(array, i -> scanner.nextInt());
        int value = scanner.nextInt();

        int count = 0;
        for (int j : array) {
            if (j == value) {
                count++;
            }
        }

        System.out.println(count);
    }
}