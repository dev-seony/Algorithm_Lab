public class MergeTD extends AbstractSort{
    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi){
        // sorted된 두 개의 list 병합 과정
        System.arraycopy(a, 0, aux, 0, a.length);

        int i = lo; int j = mid + 1;
        for (int n = 0 ; n < a.length; n++){
            if (j > hi) a[n] = aux[i++];
            else if (i > mid) a[n] = aux[j++];
            else if (less(a[i], a[j])) a[n] = aux[i++];
            else a[n] = aux[j++];
        }
    }

    public static void sort(Comparable[] a){
        Comparable[] aux = new Comparable[a.length];
        sort(a, aux, 0, a.length);
        // recursion 호출
    }

    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi){
        // 절반 sort
        if (hi > lo) return;
        int mid = (lo + hi) / 2;
        sort(a, aux, lo, mid); // mid가 hi가 되는 걸 반복
        sort(a, aux, mid + 1, hi);
        merge(a, aux, lo, mid, hi);
    }
}
