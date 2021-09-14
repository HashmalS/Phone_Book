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

        List<Person> phoneBookEntriesForBubbleSort = new ArrayList<>(phoneBookEntries);
        List<Person> phoneBookEntriesForQuickSort = new ArrayList<>(phoneBookEntries);

        /**
         Linear search without sorting.
         **/
        System.out.println("Start searching (linear search)...");
        long startTime = System.currentTimeMillis();
        findPeople(phoneBookEntries, necessaryPeople);
        long totalLinearSearchTime = System.currentTimeMillis() - startTime;

        long timeLimit = 10L * totalLinearSearchTime;

        long milliseconds = totalLinearSearchTime % 1000;
        totalLinearSearchTime /= 1000;
        long seconds = totalLinearSearchTime % 60;
        totalLinearSearchTime /= 60;
        long minutes = totalLinearSearchTime % 60;

        System.out.printf("Time taken: %d min. %d sec. %d ms.\n\n",
                minutes, seconds, milliseconds);

        /**
         *Bubble sort and then Jump search if sorting is fast enough.
         **/
        System.out.println("Start searching (bubble sort + jump search)...");
        startTime = System.currentTimeMillis();
        boolean sorted = bubbleSort(phoneBookEntriesForBubbleSort, timeLimit);
        long totalBubbleSortTime = System.currentTimeMillis() - startTime;
        if (sorted) {
            int entriesFound = 0;
            long searchStartTime = System.currentTimeMillis();
            for (String target : necessaryPeople) {
                int index = jumpSearch(phoneBookEntriesForBubbleSort, target);
                if (index > -1) {
                    entriesFound++;
                }
            }
            long totalJumpSearchTime = System.currentTimeMillis() - searchStartTime;
            long totalSearchTime = System.currentTimeMillis() - startTime;
            milliseconds = totalSearchTime % 1000;
            totalSearchTime /= 1000;
            seconds = totalSearchTime % 60;
            totalSearchTime /= 60;
            minutes = totalSearchTime % 60;
            System.out.printf("Found %d/%d entries. ",
                    entriesFound, necessaryPeople.size());
            System.out.printf("Time taken: %d min. %d sec. %d ms.\n",
                    minutes, seconds, milliseconds);
            milliseconds = totalBubbleSortTime % 1000;
            totalBubbleSortTime /= 1000;
            seconds = totalBubbleSortTime % 60;
            totalBubbleSortTime /= 60;
            minutes = totalBubbleSortTime % 60;
            System.out.printf("Sorting time: %d min. %d sec. %d ms.\n",
                    minutes, seconds, milliseconds);

            milliseconds = totalJumpSearchTime % 1000;
            totalJumpSearchTime /= 1000;
            seconds = totalJumpSearchTime % 60;
            totalJumpSearchTime /= 60;
            minutes = totalJumpSearchTime % 60;
        } else {
            long linearSearchStartTime = System.currentTimeMillis();
            findPeople(phoneBookEntriesForBubbleSort, necessaryPeople);
            totalLinearSearchTime = System.currentTimeMillis() - linearSearchStartTime;
            long totalSearchTime = System.currentTimeMillis() - startTime;

            milliseconds = totalSearchTime % 1000;
            totalSearchTime /= 1000;
            seconds = totalSearchTime % 60;
            totalSearchTime /= 60;
            minutes = totalSearchTime % 60;
            System.out.printf("Time taken: %d min. %d sec. %d ms.\n",
                    minutes, seconds, milliseconds);
            milliseconds = totalBubbleSortTime % 1000;
            totalBubbleSortTime /= 1000;
            seconds = totalBubbleSortTime % 60;
            totalBubbleSortTime /= 60;
            minutes = totalBubbleSortTime % 60;
            System.out.printf("Sorting time: %d min. %d sec. %d ms. - STOPPED, moved to linear search\n",
                    minutes, seconds, milliseconds);

            milliseconds = totalLinearSearchTime % 1000;
            totalLinearSearchTime /= 1000;
            seconds = totalLinearSearchTime % 60;
            totalLinearSearchTime /= 60;
            minutes = totalLinearSearchTime % 60;

        }
        System.out.printf("Searching time: %d min. %d sec. %d ms.\n\n",
                minutes, seconds, milliseconds);

        /**
         *Quick sort and then binary search
         **/
        System.out.println("Start searching (quick sort + binary search)...");
        startTime = System.currentTimeMillis();
        quickSort(phoneBookEntriesForQuickSort, 0, phoneBookEntries.size() - 1);
        long totalQuickSortTime = System.currentTimeMillis() - startTime;
        int entriesFound = 0;
        long searchStartTime = System.currentTimeMillis();
        for (String target : necessaryPeople) {
            int index = binarySearch(phoneBookEntriesForQuickSort, target, 0, phoneBookEntries.size() - 1);
            if (index > -1) {
                entriesFound++;
            }
        }
        long totalBinarySearchTime = System.currentTimeMillis() - searchStartTime;
        long totalSearchTime = System.currentTimeMillis() - startTime;
        milliseconds = totalSearchTime % 1000;
        totalSearchTime /= 1000;
        seconds = totalSearchTime % 60;
        totalSearchTime /= 60;
        minutes = totalSearchTime % 60;
        System.out.printf("Found %d/%d entries. ",
                entriesFound, necessaryPeople.size());
        System.out.printf("Time taken: %d min. %d sec. %d ms.\n",
                minutes, seconds, milliseconds);
        milliseconds = totalQuickSortTime % 1000;
        totalQuickSortTime /= 1000;
        seconds = totalQuickSortTime % 60;
        totalQuickSortTime /= 60;
        minutes = totalQuickSortTime % 60;
        System.out.printf("Sorting time: %d min. %d sec. %d ms.\n",
                minutes, seconds, milliseconds);

        milliseconds = totalQuickSortTime % 1000;
        totalBinarySearchTime /= 1000;
        seconds = totalBinarySearchTime % 60;
        totalBinarySearchTime /= 60;
        minutes = totalBinarySearchTime % 60;
        System.out.printf("Searching time: %d min. %d sec. %d ms.\n\n",
                minutes, seconds, milliseconds);
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
                necessaryPeople.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.getMessage();
        }

        return necessaryPeople;
    }

    public static void findPeople(List<Person> peopleList, List<String> neededPeopleList) {
        int totalToFind = neededPeopleList.size();
        int found = 0;
        for (String person :
                neededPeopleList) {
            for (Person phoneBookEntry :
                    peopleList) {
                if (person.equals(phoneBookEntry.getName())) {
                    found++;
                }
            }
        }
        System.out.printf("Found %d/%d entries. ",
                found, totalToFind);
    }

    public static int jumpSearch(List<Person> personList, String target) {
        int currentRight = 0;
        int prevRight = 0;

        if (personList.size() == 0) {
            return -1;
        }

        if (personList.get(currentRight).getName().equals(target)) {
            return 0;
        }

        int jumpLength = (int) Math.sqrt(personList.size());
        while (currentRight < personList.size() - 1) {
            currentRight = Math.min(personList.size() - 1, currentRight + jumpLength);

            if (personList.get(currentRight).getName().compareTo(target) >= 0) {
                break;
            }

            prevRight = currentRight;
        }

        if ((currentRight == personList.size() - 1) && personList.get(currentRight).getName().compareTo(target) < 0) {
            return -1;
        }

        return backwardSearch(personList, target, prevRight, currentRight);
    }

    public static int backwardSearch(List<Person> personList, String target, int leftExcl, int rightIncl) {
        for (int i = rightIncl; i > leftExcl; i--) {
            if (target.equals(personList.get(i).getName())) {
                return i;
            }
        }
        return -1;
    }

    public static boolean bubbleSort(List<Person> personList, long timeLimit) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < personList.size() - 1; i++) {
            for (int j = 0; j < personList.size() - i - 1; j++) {
                if (System.currentTimeMillis() - startTime > timeLimit) {
                    return false;
                }
                if (personList.get(j).getName().compareTo(personList.get(j + 1).getName()) > 0) {
                    Person temp = personList.get(j);
                    personList.set(j, personList.get(j + 1));
                    personList.set(j + 1, temp);
                }
            }
        }

        return true;
    }

    public static void quickSort(List<Person> personList, int left, int right) {
        if (left < right) {
            int pivotIndex = partition(personList, left, right);
            quickSort(personList, left, pivotIndex - 1);
            quickSort(personList, pivotIndex + 1, right);
        }
    }

    private static int partition(List<Person> personList, int left, int right) {
        String pivot = personList.get(right).getName();
        int partitionIndex = left;

        for (int i = left; i < right; i++) {
            if (personList.get(i).getName().compareTo(pivot) < 0) {
                swap(personList, i, partitionIndex);
                partitionIndex++;
            }
        }

        swap(personList, partitionIndex, right); // put the pivot on a suitable position

        return partitionIndex;
    }

    private static void swap(List<Person> personList, int i, int j) {
        Person temp = personList.get(i);
        personList.set(i, personList.get(j));
        personList.set(j, temp);
    }

    public static int binarySearch(List<Person> personList, String name, int left, int right) {
        if (left > right) {
            return -1;
        }

        int mid = left + (right - left) / 2;

        if (name.equals(personList.get(mid).getName())) {
            return mid;
        } else if (personList.get(mid).getName().compareTo(name) > 0) {
            return binarySearch(personList, name, left, mid - 1);
        } else {
            return binarySearch(personList, name, mid + 1, right);
        }
    }
}
