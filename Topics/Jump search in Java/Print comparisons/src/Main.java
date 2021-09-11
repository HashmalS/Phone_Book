import java.util.*;

public class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int m = (int) Math.sqrt(n);
        final int tail = (n - 1) % m;
        final int body = n - tail;
        int[] results = new int[n];

        for (int i = 0; i < n; i++) {
            final int backsteps = i % m;
            results[i] = (i / m) + 1;
            if (backsteps > 0) {
                results[i] += m - backsteps + 1;
            }
            if (i >= body) {
                results[i] -= m - tail;
            }
        }

        Arrays.stream(results).forEach(result -> System.out.printf("%d ", result));
    }
}