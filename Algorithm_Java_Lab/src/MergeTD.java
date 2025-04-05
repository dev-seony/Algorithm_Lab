public class MergeTD extends AbstractSort{
    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi){
        // sorted된 배열의 lo ~ hi 병합 과정
        System.arraycopy(a, lo, aux, lo, hi - lo + 1);

        int i = lo; int j = mid + 1;
        for (int n = lo ; n <= hi; n++){
            if (j > hi)                 a[n] = aux[i++];
            else if (i > mid)           a[n] = aux[j++];
            else if (less(aux[i], aux[j]))  a[n] = aux[i++];
            else                        a[n] = aux[j++];
        }
    }

    public static void sort(Comparable[] a){
        Comparable[] aux = new Comparable[a.length];
        sort(a, aux, 0, a.length - 1);
        // recursion 호출
    }

    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi){
        // 절반 sort
        if (hi <= lo) return;
        int mid = lo + (lo + hi) / 2;
        sort(a, aux, lo, mid); // mid가 hi가 되는 걸 반복
        sort(a, aux, mid + 1, hi);
        merge(a, aux, lo, mid, hi);
    }

    public static void main(String[] args) {
        String[] a = {"다", "나", "가"};
        MergeTD.sort(a);
        MergeTD.show(a);
    }
}
