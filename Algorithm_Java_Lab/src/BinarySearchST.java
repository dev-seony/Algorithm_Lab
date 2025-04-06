import java.util.ArrayList;

public class BinarySearchST<K extends Comparable<K>, V> {
    private static final int INIT_CAPACITY = 10;
    private K[] keys; // 키의 배열
    private V[] vals; // 값의 배열
    /* 하나의 배열로 (키, 값) 쌍을 표현하는 방법?
    private static class Entry<K, V> {
        K key;
        V value;
        Entry(K k, V v) {
            key = k;
            value = v;
        }
    }
    private Entry<K, V>[] entries;
     */

    private int N;

    public BinarySearchST() {
        keys = (K[]) new Comparable[INIT_CAPACITY];
        vals = (V[]) new Object[INIT_CAPACITY];
    }

    public BinarySearchST(int capacity) { // 초기 배열보다 많은 양의 데이터 저장용
        keys = (K[]) new Comparable[capacity];
        vals = (V[]) new Object[capacity];
    }

    public boolean contains(K key) {
        return get(key) != null;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    private void resize(int capacity) { // 배열 크기를 동적으로 변경
        K[] tempk = (K[]) new Comparable[capacity];
        V[] tempv = (V[]) new Object[capacity];
        for (int i = 0; i < N; i++) {
            tempk[i] = keys[i];
            tempv[i] = vals[i];
        }
        vals = tempv;
        keys = tempk;
        /*
        // System.arraycopy 사용 시 수월한 복사 가능
        K[] tempkarray = (K[]) new Comparable[capacity];
        V[] tempvarray = (V[]) new Object[capacity];
        System.arraycopy(keys, 0, tempkarray, 0, N);
        System.arraycopy(vals, 0, tempvarray, 0, N);
         */
    }

    private int search(K key) { // 이진 검색
        int lo = 0;
        int hi = N - 1; // N은 length + 1 -> 마지막 항은 N - 1
        while (lo <= hi) {
            int mid = (hi + lo) / 2;
            int cmp = key.compareTo(keys[mid]);

            if (cmp < 0) { hi = mid - 1; }
            else if (cmp > 0) { lo = mid + 1; }
            else return mid;
        }
        // 키가 없을 경우, -1이 아니라 lo가 반환
        return lo;
    }

    public V get(K key) {
        if (isEmpty()) return null; // i에 null return. compareTo에서 null과 비교 -> NullPointerException
        int i = search(key); // 이진 검색
        if (i < N && keys[i].compareTo(key) == 0) {
            return vals[i];
        }
        /*
        i < N의 존재 이유
        ex. [apple, banana]가 있을 때 search zebra가 들어오면 lo의 값 2가 return됨.
        이 경우 있는 값 변경의 항목에서 없는 값 추가가 되므로 오류 발생
         */
        else return null; // 키가 없으면 null을 반환
    }

    public void put(K key, V value) {
        int i = search(key); // 일단 키를 찾고…
        if (i < N && keys[i].compareTo(key) == 0) // 있으면, 값만 변경. 왜 비교를 다시? <- get과 동일한 사유
            { vals[i] = value; return; }
        if (N == keys.length) // 없으면, 추가해야 하니 배열 크기 확장
            resize(2 * keys.length);
        for (int j = N; j > i; j--) { // N 자체가 length + 1임.
            keys[j] = keys[j - 1];
            vals[j] = vals[j - 1];
        } // 추가될 곳의 공간 확보
        keys[i] = key;
        vals[i] = value;
        N++;
    }

    public void delete(K key) {
        if (isEmpty()) return;
        int i = search(key); // 이진 검색: 모든 키는 이진 검색으로 찾자!!!
        if (i == N || keys[i].compareTo(key) != 0) return; // 없으면, 그냥 반환

        for (int j = i; j < N - 1; j++) {
            keys[j] = keys[j + 1];
            vals[j] = vals[j + 1];
        } // 뒤에 있는 키들을 한칸 앞으로.
        N--;
        keys[N] = null;
        vals[N] = null;
        // null 반환 이유 -> Java의 경우 메모리 관리가 중요. Garbage 처리 위해서.
        if (N > INIT_CAPACITY && N == keys.length / 4) // 배열이 너무 크면, 다시 줄이자.
            resize(keys.length / 2);
    }

    public Iterable<K> keys() { // 연결 리스트의 경우와 거의 동일
        ArrayList<K> keyList = new ArrayList<K>(N);
        for (int i = 0; i < N; i++)
            keyList.add(keys[i]);
        return keyList;
    }
}