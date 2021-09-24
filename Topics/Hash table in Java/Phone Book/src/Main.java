import java.util.Scanner;

public class Main {
    private static class TableEntry<T> {
        private final int key;
        private final T value;
        private boolean removed;

        public TableEntry(int key, T value) {
            this.key = key;
            this.value = value;
        }

        public int getKey() {
            return key;
        }

        public T getValue() {
            return value;
        }

        public void remove() {
             removed = true;   
        }

        public boolean isRemoved() {
             return removed;   
        }
    }

    private static class HashTable<T> {
        private int size;
        private TableEntry[] table;

        public HashTable(int size) {
            this.size = size;
            table = new TableEntry[size];
        }

        public boolean put(int key, T value) {
            int index = findKey(key);

            if (index == -1) {
                return false;
            }

            table[index] = new TableEntry(key, value);
            return true;
        }

        public T get(int key) {
            int index = findKey(key);

            if (index == -1 || table[index] == null) {
                return null;
            }

            return (T) table[index].getValue();
        }

        public void remove(int key) {
            int index = findKey(key);
            if (index == -1) {
                return;
            }

            table[index] = null;
        }

        private int findKey(int key) {
            int hash = key % size;

            while (!(table[hash] == null || table[hash].getKey() == key)) {
                hash = key % size;

                if (hash == key % size) {
                    return -1;
                }
            }

            return hash;
        }
    }

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        HashTable<String> hashTable = new HashTable<>(n);
        for (int i = 0; i < n; i++) {
            String command = scanner.next();
            switch (command) {
                case "put":
                    hashTable.put(scanner.nextInt(), scanner.next());
                    break;
                case "get":
                    String value = hashTable.get(scanner.nextInt());
                    System.out.println(value == null ? "-1" : value);
                    break;
                case "remove":
                    hashTable.remove(scanner.nextInt());
                    break;
                default:
                    break;
            }
        }
    }
}