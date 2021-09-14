import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int maxValue = Integer.MIN_VALUE;
        while (scanner.hasNext()) {
            int value = scanner.nextInt();
            if (value % 4 == 0 && value > maxValue) {
                maxValue = value;
            }
        }

        System.out.println(maxValue);
    }
}