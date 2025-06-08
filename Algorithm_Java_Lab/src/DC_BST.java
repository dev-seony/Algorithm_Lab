public class DC_BST {
    public int location(int[] a, int key) {
        int mid = location(a, 0, a.length - 1, key);
        return mid;
    }

    private int location (int[] a, int low, int high, int key) {
        int mid;
        if (low > high) return -1;
        else {
            mid = (low + high) / 2;
            if (key == a[mid]) return mid;
            else if (key < a[mid]) return location(a, low, mid - 1, key);
            else return location(a, mid + 1, high, key);
        }
    }

    public static void main(String args[]) {
        int[] a = {1, 2, 5, 7, 8};
        DC_BST bst = new DC_BST();
        int location = bst.location(a, 8);
        System.out.print(location);
    }
}
