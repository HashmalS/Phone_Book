import java.util.Arrays;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] array = new int[scanner.nextInt()];
        Arrays.setAll(array, i -> scanner.nextInt());

        int sum = Arrays.stream(array).sum();

        System.out.println(sum);
    }
}