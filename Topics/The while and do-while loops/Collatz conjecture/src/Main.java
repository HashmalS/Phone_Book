import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        while (n != 1) {
            System.out.printf("%d ", n);
            n = n % 2 == 0 ? n / 2 : 3 * n + 1;
        }
        System.out.println(n);
    }
}