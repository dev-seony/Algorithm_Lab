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

        /*
        부분의 크기 <= 7인 경우 cutoff 설정, 해당 부분은 Insertion Sort
        int CUTOFF = 7;
        if (hi <= lo + CUTOFF - 1){
            Insertion.sort(a, lo, hi);
            return;
        }
         */

        int mid = lo + (lo + hi) / 2;
        sort(a, aux, lo, mid); // mid가 hi가 되는 걸 반복
        sort(a, aux, mid + 1, hi);
        // if (less(a[mid], a[mid + 1])) return; // 이미 정렬된 경우 pass
        merge(a, aux, lo, mid, hi);
    }

    public static void main(String[] args) {
        String[] a = {"다", "나", "가"};
        MergeTD.sort(a);
        MergeTD.show(a);
    }
}
