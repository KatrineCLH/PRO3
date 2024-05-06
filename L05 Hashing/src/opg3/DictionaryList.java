package opg3;

import java.security.cert.TrustAnchor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DictionaryList<K, V> implements Dictionary<K, V> {

    private List<Entry<K,V>>[] tabel;
    private static int N = 13;

    /**
     * HashingMap constructor comment.
     */

    public DictionaryList() {
        tabel = new List[N];
        for (int i = 0; i < N; i++) {
            tabel[i] = new ArrayList<>();
        }
    }

    @Override
    public V get(K key) {
        int listIndex = hash(key.hashCode());
        V value = null;

        //man behøver ikke tjekke, om listen er tom, for loopet kører bare ikke på en tom liste
        if (!tabel[listIndex].isEmpty()){
            for (Entry<K, V> e : tabel[listIndex]){
                if(e.key.equals(key)){
                    value = e.value;
                }
            }
        }

        return value;
    }

    @Override
    public boolean isEmpty() {
        boolean empty = true;
        int i = 0;

        while(empty && i < tabel.length){
            if (!tabel[i].isEmpty()){
                empty = false;
            }
            i++;
        }

        return empty;
    }

    @Override
    public V put(K key, V value) {
        int listIndex = hash(key.hashCode());
        List<Entry<K,V>> list = tabel[listIndex];
        V oldValue = null;

        if ((size() * 1.0/N) > 0.5){
            rehash();
        }


        if (list.isEmpty()){
            list.add(new Entry<>(key, value));
        }
        else{
            boolean found = false;
            int i = 0;
            while(!found && i < tabel.length){
                if (list.get(i).key.equals(key)){
                    oldValue = list.get(i).value;
                    list.get(i).value = value;
                    found = true;
                }
                i++;
            }
            if (!found){
                list.add(new Entry<>(key, value));
            }
        }

        return oldValue;
    }

    @Override
    public V remove(K key) {
        int listIndex = hash(key.hashCode());
        List<Entry<K,V>> list = tabel[listIndex];
        V value = null;

        if (!list.isEmpty()){
            boolean found = false;
            int i = 0;
            while(!found && i < list.size()){
                if (list.get(i).key.equals(key)){
                    Entry<K,V> removedEntry = list.remove(i);
                    value = removedEntry.value;
                }
            }
        }

        return value;
    }

    @Override
    public int size() {
        int sum = 0;

        for (int i = 0; i < N; i++){
            if (!tabel[i].isEmpty()) {
                sum += tabel[i].size();
            }
        }

        return sum;
    }

    public int hash(int hashcode){
        if (hashcode < 0) {
            hashcode = -hashcode;
        }
        return hashcode % tabel.length;
    }

    public void rehash(){
        List<Entry<K,V>>[] tempTabel = tabel;
        List<Entry<K,V>>[] newTabel = new List[2 * N + 1];
        for (int i = 0; i < newTabel.length; i++){
            newTabel[i] = new ArrayList<>();
        }
        tabel = newTabel;

        for (int i = 0; i < tempTabel.length; i++){
            if (!tempTabel[i].isEmpty()){
                for (Entry<K,V> e : tempTabel[i]){
                    put(e.key, e.value);
                }
            }
        }
    }

    private class Entry<K,V>{
        public K key;
        public V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
