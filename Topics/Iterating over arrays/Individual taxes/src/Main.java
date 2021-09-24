import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfCompanies = scanner.nextInt();

        int[] yearlyIncomes = new int[numberOfCompanies];
        double[] taxPercents = new double[numberOfCompanies];

        for (int i = 0; i < numberOfCompanies; i++) {
            yearlyIncomes[i] = scanner.nextInt();
        }

        for (int i = 0; i < numberOfCompanies; i++) {
            taxPercents[i] = scanner.nextInt() / 100.0;
        }

        int companyThatPaysTheMost = 1;
        double tax = 0.0;
        for (int i = 0; i < numberOfCompanies; i++) {
            double currentTax = yearlyIncomes[i] * taxPercents[i];
            if (currentTax > tax) {
                companyThatPaysTheMost = i + 1;
                tax = currentTax;
            }
        }

        System.out.println(companyThatPaysTheMost);
    }
}