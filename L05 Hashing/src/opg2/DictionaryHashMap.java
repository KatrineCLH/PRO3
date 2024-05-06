package opg2;

import java.util.HashMap;
import java.util.Map;

public class DictionaryHashMap<K, V> implements Dictionary<K, V> {

    private Map<K, V>[] tabel;
    private static int N = 13;

    /**
     * HashingMap constructor comment.
     */

    public DictionaryHashMap() {
        tabel = new HashMap[N];
        for (int i = 0; i < N; i++) {
            tabel[i] = new HashMap<K, V>();
        }
    }

    @Override
    public V get(K key) {
        V value = null;
        int keyHashCode = key.hashCode();
        int bucketIndex = keyHashCode % tabel.length;

        value = tabel[bucketIndex].get(key);

        return value;
    }

    @Override
    public boolean isEmpty() {
        boolean isEmpty = true;
        int i = 0;
        while (isEmpty && i < tabel.length){
            if (!tabel[i].isEmpty()){
                isEmpty = false;
            }
            i++;
        }
        return isEmpty;
    }

    @Override
    public V put(K key, V value) {
        int keyHashCode = key.hashCode();
        int bucketIndex = keyHashCode % tabel.length;
        Map<K,V> destMap = tabel[bucketIndex];
        return destMap.put(key, value);
    }

    @Override
    public V remove(K key) {
        int keyHashCode = key.hashCode();
        int bucketIndex = keyHashCode % tabel.length;
        Map<K,V> destMap = tabel[bucketIndex];
        return destMap.remove(key);
    }

    @Override
    public int size() {
        int sum = 0;
        for (int i = 0; i < tabel.length; i++){
            Map<K,V> currentMap = tabel[i];
            sum += currentMap.size();
        }
        return sum;
    }

}
