public class Insertion extends AbstractSort{
    public static void sort(Comparable[] a){
        for (int i = 0; i < a.length; i++){
            for (int j = i; j > 0 && less(a[j], a[j-1]); j--) {
                exch(a, j, j-1);
                // 한 칸씩 뒤로 밀림. j++의 경우 반복문을 통한 처리 필요
            }
        }
        assert isSorted(a);
    }

    public static void main(String[] args) {
        Integer[] a = {10, 4, 5, 2, 1, 8, 3, 6};
        Insertion.sort(a);
        Insertion.show(a);
    }
}
