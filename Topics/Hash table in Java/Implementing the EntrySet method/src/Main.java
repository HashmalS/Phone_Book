import java.util.*;

public class Main {
    private static class TableEntry<T> {
        private final int key;
        private T value;

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

            if (table[index] != null) {
                table[index] = new TableEntry(key,
                        table[index].getValue().toString().concat(" ").concat(value.toString()));
                return true;
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

        public String entrySet() {
            List<Integer> keys = new ArrayList<>();
            for (TableEntry entry :
                    table) {
                if (entry != null && !keys.contains(entry.getKey())) {
                    keys.add(entry.getKey());
                }
            }
            List<String> entryStrings = new ArrayList<>(keys.size());
            for (int key :
                    keys) {
                entryStrings.add(String.valueOf(key).concat(": "));
            }

            for (int i = 0; i < entryStrings.size(); i++) {
                for (TableEntry entry :
                        table) {
                    if (!(entry == null) && entryStrings.get(i).startsWith(String.valueOf(entry.getKey()))) {
                        entryStrings.set(i, entryStrings.get(i).concat(entry.getValue().toString()).concat(" "));
                    }
                }
            }

            String outString = "";
            for (String s :
                    entryStrings) {
                outString = outString.concat(s).concat("\n");
            }

            return outString;
        }

        private int findKey(int key) {
            int hash = key % size;

            while (!(table[hash] == null || table[hash].getKey() == key)) {
                hash = (hash + 1) % size;

                if (hash == key % size) {
                    return -1;
                }
            }

            return hash;
        }

        private void rehash() {
            // put your code here
        }
    }


    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        HashTable<String> table = new HashTable<>(n);
        for (int i = 0; i < n; i++) {
            table.put(scanner.nextInt(), scanner.next());
        }

        System.out.println(table.entrySet());
    }
}