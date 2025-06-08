public class DC_QuickSort extends AbstractSort{
    void quicksort(Comparable[] a) {
        quicksort(a, 0, a.length - 1);
    }
    private void quicksort(Comparable[] a, int low, int high) {
        if (high > low) {
            int pivot = partition(a, low, high);
            quicksort(a, low, pivot - 1);
            quicksort(a, pivot + 1, high);
        }
    }
    private int partition(Comparable[] a, int low, int high) {
        Comparable pivot = a[low];
        int swapPoint = low;
        for (int i = low + 1; i <= high; i++) {
            if (a[i].compareTo(pivot) < 0) { exch(a, i, ++swapPoint); }
        }
        exch(a, swapPoint, low);
        return swapPoint;
    }
    public static void main(String args[]) {
        DC_QuickSort qs = new DC_QuickSort();
        Comparable[] a = {15, 22, 13, 27, 12, 10, 20, 25};
        qs.quicksort(a);
        show(a);
    }
}
