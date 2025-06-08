public class DP_BinDynamic {
    //nCk
    public int binDynamic(int n, int k) {
        int[][] B = new int[n+1][k+1]; // 0~n * 0~k 배열이기 때문
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= Math.min(i, k); j++) {
                if (j == 0 || j == i) {
                    B[i][j] = 1; // 아무 것도 안 뽑거나 모두 뽑을 경우의 수 1
                } else {
                    B[i][j] = B[i-1][j-1] + B[i-1][j];
                }
            }
        }
        return B[n][k];
    }

    public static void main(String[] args) {
        DP_BinDynamic bin = new DP_BinDynamic();
        System.out.print(bin.binDynamic(5, 3));
    }
}
