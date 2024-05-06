package afleveringstuderende;

public class DictionaryDoubleHashing <K, V> implements Dictionary<K, V> {
    private Entry<K,V>[] table;
    private int size;

    private final Entry DELETED = new Entry(null,null);

    public DictionaryDoubleHashing(int length) {
        table =  new Entry[length];
        size = 0;
    }

    //Tidskompleksitet: O(n)
    @Override
    public V get(K key) {
        int hashKey = key.hashCode();
        int index = hashKey % table.length;
        int offset = 7 - (hashKey % 7);

        V value = null;

        if (table[index] != null) {
            if (table[index].key == key) {
                value = table[index].value;
            } else {
                boolean done = false;
                int x = 1;
                int newIndex = (index + offset) % table.length;
                while (index != newIndex && !done) {
                    newIndex = (index + (offset * x)) % table.length;
                    if (table[newIndex] == null){
                        done = true;
                    }
                    else if (table[newIndex].key == key) {
                        value = table[newIndex].value;
                        done = true;
                    }
                    else {
                        x++;
                    }
                }
            }
        }

        return value;
    }

    //Tidskompleksitet: O(1)
    @Override
    public boolean isEmpty() {
//        boolean empty = true;
//        int i = 0;
//        while (empty && i < table.length){
//            if (table[i] != null && table[i] != DELETED){
//                empty = false;
//            }
//            i++;
//        }
        return size == 0;
    }

    //Tidskompleksitet: O(n)
    @Override
    public V put(K key, V value) {
        int hashKey = key.hashCode();
        int index = hashKey % table.length;
        int offset = 7 - (hashKey % 7);

        V oldValue = null;

        if (table[index] == null){
            table[index] = new Entry<>(key,value);
            size++;
        }
        else {
            if (table[index].key != key){
                boolean done = false;
                int x = 1;
                int newIndex = (index + offset) % table.length;
                int bestPosition = -1;

                while (index != newIndex && !done) {
                    if (table[newIndex] == null){
                        table[newIndex] = new Entry<>(key, value);
                        done = true;
                        size++;
                    }
                    else if (table[newIndex].key == key) {
                        oldValue = table[newIndex].value;
                        table[newIndex].value = value;
                        done = true;
                    }
                    else{
                        if (bestPosition == -1 && table[newIndex].key == null){
                            bestPosition = newIndex;
                        }
                        x++;
                        newIndex = (index + ((offset * x)) % table.length);
                    }
                }

                if (!done) {
                    Entry<K, V> entry = new Entry<>(key, value);
                    table[bestPosition] = entry;
                    size++;
                }
            }
            else{
                oldValue = table[index].value;
                table[index].value = value;
            }
        }

        if ((size * 1.0 / table.length) > 0.5){
            rehash();
        }

        return oldValue;
    }

    //Tidskompleksitet: O(n)
    public void rehash(){
        Entry<K, V>[] tempTabel = table;
        Entry<K,V>[] newTabel = new Entry[2 * table.length];
        table = newTabel;
        size = 0;

        for (int i = 0; i < tempTabel.length; i++){
            if (tempTabel[i] != null && tempTabel[i].key != null) {
                put(tempTabel[i].key, tempTabel[i].value);
            }
        }
    }

    //Tidskompleksitet: O(n)
    @Override
    public V remove(K key) {
        int hashKey = key.hashCode();
        int index = hashKey % table.length;
        int offset = 7 - (hashKey % 7);

        V value = null;

        if (table[index] != null) {
            if (table[index].key != key){
                boolean done = false;
                int x = 1;
                int newIndex = (index + offset) % table.length;

                while (index != newIndex && !done) {
                    if (table[newIndex] == null){
                        done = true;
                    }
                    else {
                        if (table[newIndex].key != key){
                            x++;
                            newIndex = (index + (offset * x)) % table.length;
                        }
                        else {
                            value = table[newIndex].value;
                            table[newIndex] = DELETED;
                            done = true;
                            size--;
                        }
                    }
                }
            }
            else{
                value = table[index].value;
                table[index] = DELETED;
                size--;
            }
        }

        return value;
    }

    //Tidskompleksitet: O(1)
    @Override
    public int size() {
        return size;
    }


    // method only for test purpose
    public void writeOut() {
        for (int i = 0; i < table.length; i++) {
            System.out.println(i + "\t" + table[i]);
        }
    }

    public static class Entry<K,V>{
        private K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
        public String toString(){
            return "(" +key + " , " + value + ")";
        }
    }
}
