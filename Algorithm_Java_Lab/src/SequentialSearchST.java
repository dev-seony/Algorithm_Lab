import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class SequentialNode<K,V> { // 하나의 노드를 표현
    // (키, 값, 다음 노드를 가리키는 참조)의 쌍으로 구성
    K key; // 크기 비교 할 필요 없으니 K Generic Type
    V value;
    SequentialNode<K, V> next;

    public SequentialNode(K key, V value, SequentialNode<K, V> next) {
        this.key = key;
        this.value = value;
        this.next = next;
    }
}

public class SequentialSearchST<K, V>{
    private SequentialNode<K, V> first;
    int N; // 연결 리스트 노드 수

    public V get(K key) {
        for (SequentialNode<K,V> x = first; x != null; x = x.next) { // 연결 리스트를 스캔
            if (key.equals(x.key)) {
                return x.value; // search hit
            }
        }
        return null; // search miss
    }

    public void put (K key, V value) {
        for (SequentialNode<K, V> x = first; x != null; x = x.next){
            if (key.equals(x.key)) {
                x.value = value;
                return;
            }
        }
        first = new SequentialNode<K, V>(key, value, first); // first 자리가 next임. 즉, 기존 first와 포인터 연결
        N++;
    }

    public void delete(K key){
        if (key.equals(first.key)) {
            first = first.next;
            N--;
            return;
        }
        for (SequentialNode<K, V> x = first; x.next != null; x = x.next){
            if (key.equals(x.next.key)){
                // next pointer 연결은 전 node에서 해야 함
                // -> next node가 삭제 대상인지 검색
                x.next = x.next.next;
                N--;
                return;
            }
        }
    }

    public Iterable<K> keys(){ // iterable 구현의 좋은 예시
        ArrayList<K> keyList = new ArrayList<K>(N);
        for (SequentialNode<K, V> x = first; x != null; x = x.next){
            keyList.add(x.key);
        }
        return keyList;
    }

    public boolean contains(K key) { return get(key) != null; }

    public boolean isEmpty() { return N == 0; }

    public int size() { return N; }


    // same as FrequencyCounter
    public static void main(String[] args) {
        int minlen = Integer.parseInt(args[0]);
        SequentialSearchST<String, Integer> st = new SequentialSearchST<>();
        File file;
        final JFileChooser fc = new JFileChooser(); // 파일 선택기를 사용
        if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) file = fc.getSelectedFile();
        else {
            JOptionPane.showMessageDialog(null, "파일을 선택하세요.", "오류", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Scanner sc = null;

        try {
            sc = new Scanner(file);
            long start = System.currentTimeMillis(); // 실행 시간 측정
            while (sc.hasNext()) {
                String word = sc.next();
                if (word.length() < minlen) continue;
                if (!st.contains(word)) st.put(word, 1);
                else st.put(word, st.get(word) + 1);
            }
            String maxKey = "";
            int maxValue = 0;
            for (String word : st.keys()) {
                if (st.get(word) > maxValue) {
                    maxValue = st.get(word);
                    maxKey = word;
                }
            }
            long end = System.currentTimeMillis();
            System.out.println(maxKey + " " + maxValue);
            System.out.println("소요 시간 = " + (end - start) + "ms");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (sc != null) sc.close();
    }
}