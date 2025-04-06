public class Node<K, V>{
    int N;
    int aux;
    Node<K, V> parent;
    Node<K, V> left, right;
    K key;
    V value;

    public Node(K key, V val){
        this.key = key;
        this.value = val;
        this.N = 1;
    }

    public int getAux() { return aux; }
    public void setAux(int value) { aux = value; }
}
