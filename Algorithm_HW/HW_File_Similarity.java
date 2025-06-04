import java.io.*;
import java.util.*;

class Node<K, V> {
    int N;
    Node<K, V> parent;
    Node<K, V> left, right;
    K key;
    V value;

    public Node(K key, V val) {
        this.key = key;
        this.value = val;
        this.N = 1;
    }
}

public class HW2<K extends Comparable<K>, V> {
    protected Node<K, V> root;

    public int size() {
        return (root != null) ? root.N : 0;
    }

    private int size(Node<K, V> x) {
        return (x == null) ? 0 : x.N;
    }

    protected Node<K, V> treeSearch(K key) {
        Node<K, V> x = root;
        while (true) {
            int cmp = key.compareTo(x.key);
            if (cmp == 0) return x;
            else if (cmp < 0) {
                if (x.left == null) return x;
                else x = x.left;
            } else {
                if (x.right == null) return x;
                else x = x.right;
            }
        }
    }

    public V get(K key) {
        if (root == null) return null;
        Node<K, V> x = treeSearch(key);
        if (key.equals(x.key)) return x.value;
        else return null;
    }

    public void put(K key, V val) {
        if (root == null) {
            root = new Node<>(key, val);
            return;
        }
        Node<K, V> x = treeSearch(key);
        int cmp = key.compareTo(x.key);
        if (cmp == 0) x.value = val;
        else {
            Node<K, V> newNode = new Node<>(key, val);
            if (cmp < 0) x.left = newNode;
            else x.right = newNode;
            newNode.parent = x;
            resetSize(newNode.parent, 1);
        }
    }

    private void resetSize(Node<K, V> x, int value) {
        for (; x != null; x = x.parent)
            x.N += value;
    }

    public Iterable<K> keys() {
        ArrayList<K> keyList = new ArrayList<>();
        inorder(root, keyList);
        return keyList;
    }

    private void inorder(Node<K, V> x, ArrayList<K> keyList) {
        if (x != null) {
            inorder(x.left, keyList);
            keyList.add(x.key);
            inorder(x.right, keyList);
        }
    }

    public boolean contains(K key) {
        return get(key) != null;
    }
}

class ShingleSimilarity {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("첫번째 파일 이름? ");
        String fileNameA = scanner.next();
        System.out.print("두번째 파일 이름? ");
        String fileNameB = scanner.next();
        File fileA = new File(fileNameA);
        File fileB = new File(fileNameB);

        HW2<String, Integer> bstA = new HW2<>();
        HW2<String, Integer> bstB = new HW2<>();

        try {
            buildShingleBST(fileA, bstA);
            buildShingleBST(fileB, bstB);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        int intersection = 0;
        int union = 0;

        Set<String> allKeys = new HashSet<>();
        for (String key : bstA.keys()) allKeys.add(key);
        for (String key : bstB.keys()) allKeys.add(key);

        for (String key : allKeys) {
            int freqA = bstA.contains(key) ? bstA.get(key) : 0;
            int freqB = bstB.contains(key) ? bstB.get(key) : 0;
            union += Math.max(freqA, freqB);
            intersection += Math.min(freqA, freqB);
        }

        int countA = ((Collection<?>) bstA.keys()).size();
        int countB = ((Collection<?>) bstB.keys()).size();
        double similarity = (union == 0) ? 0.0 : (double) intersection / union;

        System.out.printf("파일 %s의 Shingle의 수 = %d\n", fileNameA, countA);
        System.out.printf("파일 %s의 Shingle의 수 = %d\n", fileNameB, countB);
        System.out.printf("두 파일에서 공통된 shingle의 수 = %d\n", intersection);
        System.out.printf("%s과 %s의 유사도 = %s\n", fileNameA, fileNameB, similarity);
    }

    private static void buildShingleBST(File file, HW2<String, Integer> bst) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "MS949"));
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line).append(" ");
        }
        br.close();

        StringTokenizer st = new StringTokenizer(sb.toString(), " \t\n=;,<>()");
        LinkedList<String> buffer = new LinkedList<>();

        while (st.hasMoreTokens()) {
            String token = st.nextToken();
            buffer.add(token);
            if (buffer.size() == 5) {
                StringBuilder shingle = new StringBuilder();
                for (String word : buffer) shingle.append(word).append(" ");
                String shingleStr = shingle.toString().trim();
                if (!bst.contains(shingleStr)) bst.put(shingleStr, 1);
                else bst.put(shingleStr, bst.get(shingleStr) + 1);
                buffer.removeFirst();
            }
        }
    }
}
