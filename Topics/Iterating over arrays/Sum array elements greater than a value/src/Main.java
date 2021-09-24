import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int length = Integer.parseInt(scanner.nextLine());

        int[] array = new int[length];
        String inputString = scanner.nextLine();
        String[] splitString = inputString.split("\\s+");
        for (int i = 0; i < length; i++) {
            array[i] = Integer.parseInt(splitString[i]);
        }

        int n = scanner.nextInt();

        int sum = 0;
        for (int value :
                array) {
            if (value > n) {
                sum += value;
            }
        }

        System.out.println(sum);
    }
}