import java.util.Scanner;
import java.util.stream.IntStream;

class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        int[] array = IntStream.range(0, size).map(i -> scanner.nextInt()).toArray();

        int product = 0;
        for (int i = 0; i < size - 1; i++) {
            int currentProduct = array[i] * array[i + 1];
            if (currentProduct > product) {
                product = currentProduct;
            }
        }

        System.out.println(product);
    }
}