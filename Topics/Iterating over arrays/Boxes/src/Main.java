import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] box1Sizes = new int[3];
        int[] box2Sizes = new int[3];
        for (int i = 0; i < 3; i++) {
            box1Sizes[i] = scanner.nextInt();
        }
        for (int i = 0; i < 3; i++) {
            box2Sizes[i] = scanner.nextInt();
        }

        Arrays.sort(box1Sizes);
        Arrays.sort(box2Sizes);

        int a = 0;
        int b = 0;
        for (int i = 0; i < 3; i++) {
            if (box1Sizes[i] > box2Sizes[i]) {
                a++;
            } else if (box1Sizes[i] < box2Sizes[i]) {
                b++;
            }
        }

        System.out.println(a == 3 ? "Box 1 > Box 2" : b == 3 ? "Box 1 < Box 2" : "Incompatible");
    }
}