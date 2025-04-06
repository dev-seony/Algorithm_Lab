import java.util.TreeMap;

public class ST<Key extends Comparable<Key>, Value> {
    private TreeMap<Key, Value> table;

    public ST() {
        table = new TreeMap<>();
    }

    public void put(Key k, Value v) {
        table.put(k, v);
    }

    public Value get(Key k) {
        return table.get(k);
    }

    public boolean contains(Key k) {
        return table.containsKey(k);
    }

    public void delete(Key k) {
        table.remove(k);
    }

    public boolean isEmpty() {
        return table.isEmpty();
    }

    public int size() {
        return table.size();
    }

    public Iterable<Key> keys() {
        return table.keySet(); // TreeMap의 keySet()은 Set<Key>이고, Set은 Iterable<Key>!
    }
}
