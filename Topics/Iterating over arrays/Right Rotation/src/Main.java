import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputSting = scanner.nextLine();
        String[] splitString = inputSting.split("\\s+");
        int[] array = new int[splitString.length];
        for (int i = 0; i < splitString.length; i++) {
            array[i] = Integer.parseInt(splitString[i]);
        }

        int numberOfRotations = scanner.nextInt();

        for (int i = 0; i < numberOfRotations % array.length; i++) {
            int lastValue = array[array.length - 1];
            System.arraycopy(array, 0, array, 1, array.length - 1);
            array[0] = lastValue;
        }

        for (int value :
                array) {
            System.out.print(value + " ");
        }
    }
}