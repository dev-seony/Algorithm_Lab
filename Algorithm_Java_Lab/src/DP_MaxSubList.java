public class DP_MaxSubList {
    double maxSubList(double[] A, int n) {
        double[] B = new double[n];
        double max;
        int[] location = new int[n];
        int start = 0, end = 0;

        max = B[0] = A[0];

        for (int i = 1; i < n; i++) {
            if (B[i-1] <= 0) { B[i] = A[i]; location[i] = i; }
            else { B[i] = B[i-1] + A[i]; location[i] = location[i-1]; }
            if (max < B[i]) { max = B[i]; start = location[i]; end = i; }
        }

        System.out.print("부분 리스트 : ");
        for (int i = start; i <= end; i++) {
            System.out.print(A[i] + " ");
        }
        System.out.println();

        return max;
    }

    public static void main(String[] args) {
        DP_MaxSubList a = new DP_MaxSubList();
        double[] Ex = {2.3, 3.2, -4.5, 2.1 -5.3, 3.6, 4.1, -2.3, 3.5, -4.5};
        System.out.print("합 = " + String.format("%.1f", a.maxSubList(Ex, Ex.length)));
    }
}
