public class Radix {
    public static void sort(int[] A){
        int exp = 1;
        int[] B = new int[A.length];
        int m = A[0];
        for (int i = 0; i < A.length; i++) if (m < A[i]) m = A[i];

        while (m / exp > 0) {
            int[] C = new int[10];
            for (int i = 0; i < A.length; i++) C[A[i]/exp % 10]++;
            for (int i = 1; i < 10; i++) C[i] += C[i - 1];
            for (int i = A.length - 1; i >= 0; i--) B[--C[(A[i] / exp) % 10]] = A[i];
            for (int i = 0; i < A.length; i++) A[i] = B[i];
            exp *= 10;
        }
    }
    public static void main(String[] args) {
        int[] A = {427, 103, 54, 207, 9, 125};
        Radix.sort(A);
        for (int i = 0; i < A.length; i++)
            System.out.print(A[i] + " ");
        System.out.println();
    }
}
