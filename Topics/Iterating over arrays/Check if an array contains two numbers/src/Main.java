import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        int length = scanner.nextInt();
        Integer[] array = new Integer[length];
        for (int i = 0; i < length; i++) {
            array[i] = scanner.nextInt();
        }

        int n = scanner.nextInt();
        int m = scanner.nextInt();
        boolean areNear = false;

        for (int i = 1; i < length - 1; i++) {
            if (array[i] == n && (array[i + 1] == m || array[i - 1] == m)) {
                areNear = true;
                break;
            }
            if (array[i] == m && (array[i + 1] == n || array[i - 1] == n)) {
                areNear = true;
                break;
            }
        }

        System.out.println(areNear);
    }
}