import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long m = scanner.nextLong();
        long i = 0;
        do {
            i++;
        } while (m >= fact(i));
        System.out.println(i);
    }

    static long fact(long m) {
        if (m == 0 || m == 1) {
            return 1;
        }
        return m * fact(m - 1);
    }
}