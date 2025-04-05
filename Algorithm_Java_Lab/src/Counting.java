public class Counting {
    public static int[] sort(int[] A, int K){
        int[] B = new int[A.length];
        int[] C = new int[K];
        for (int i = 0; i < A.length; i++) C[A[i]]++;
        for (int i = 1; i < K; i++) C[i] += C[i - 1];
        for (int i = A.length - 1; i >= 0; i--) B[--C[A[i]]] = A[i];
        // 앞에 있던 원소가 뒤로 밀려나는 것 막기 위해 뒤에서부터.
        return B;
    }
    public static void main(String[] args) {
        int[] A = {10, 4, 5, 8, 1, 8, 3, 6}, B;
        B = Counting.sort(A, 11);
        for (int i = 0; i < B.length; i++)
            System.out.print(B[i] + " ");
        System.out.println();
    }
}
