package phonebook;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Person> phoneBookEntries =
                parsePhoneBook("D:\\Stuff\\Programming\\misc\\hyperskill_phonebook\\directory.txt");

        List<String> necessaryPeople =
                parseNecessaryPeople("D:\\Stuff\\Programming\\misc\\hyperskill_phonebook\\find.txt");

        findPeople(phoneBookEntries, necessaryPeople);

    }

    public static List<Person> parsePhoneBook(String pathToFile) {
        File phoneBook = new File(pathToFile);
        List<Person> phoneBookEntries = new ArrayList<>();
        try (Scanner scanner = new Scanner(phoneBook)) {
            while (scanner.hasNext()) {
                String entry = scanner.nextLine();
                String[] parsedEntry = entry.split("\\s+", 2);
                phoneBookEntries.add(new Person(Integer.parseInt(parsedEntry[0]), parsedEntry[1]));
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return phoneBookEntries;
    }

    public static List<String> parseNecessaryPeople(String pathToFile) {
        List<String> necessaryPeople = new ArrayList<>();
        File listOfPeople = new File(pathToFile);
        try (Scanner scanner = new Scanner(listOfPeople)) {
            while (scanner.hasNext()) {
                necessaryPeople.add(scanner.nextLine().trim());
            }
        } catch (FileNotFoundException e) {
            e.getMessage();
        }

        return necessaryPeople;
    }

    public static void findPeople(List<Person> peopleList, List<String> neededPeopleList) {
        int totalToFind = neededPeopleList.size();
        int found = 0;
        System.out.println("Start searching...");
        long startTime = System.currentTimeMillis();
        for (String person :
                neededPeopleList) {
            for (Person phoneBookEntry :
                    peopleList) {
                if (person.equals(phoneBookEntry.getName())) {
                    found++;
                }
            }
        }
        long totalTime = System.currentTimeMillis() - startTime;

        long milliseconds = totalTime % 1000;
        totalTime /= 1000;
        long seconds = totalTime % 60;
        totalTime /= 60;
        long minutes = totalTime % 60;

        System.out.printf("Found %d/%d entries. Time taken: %d min. %d sec. %d ms.",
                found, totalToFind, minutes, seconds, milliseconds);
    }
}
