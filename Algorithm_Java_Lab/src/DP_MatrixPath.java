public class DP_MatrixPath {
    public int matrixPath(int[][] Matrix) {
        int n = Matrix.length;
        int[][] A = new int[n][n];

        A[0][0] = Matrix[0][0];
        for (int i = 1; i < n; i++) {
            A[i][0] = Matrix[i][0] + A[i-1][0];
            A[0][i] = Matrix[0][i] + A[0][i-1];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                A[i][j] = Matrix[i][j] + Math.min(A[i-1][j], A[i][j-1]);
            }
        }

        return A[n-1][n-1];
    }
}
