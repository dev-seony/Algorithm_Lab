// 22210596 전선영

import java.util.Arrays;

class Solution1 {
    int[] result = new int[2];

    public int[] solution(int[][] arr) {
        partition(arr, 0, 0, arr.length);
        return result;
    }

    private void partition(int[][] arr, int x, int y, int size) {
        if (isOneNumber(arr, x, y, size)) {
            result[arr[x][y] == 0 ? 0 : 1]++;
            return;
        }

        int half = size / 2;
        partition(arr, x, y, half);
        partition(arr, x, y + half, half);
        partition(arr, x + half, y, half);
        partition(arr, x + half, y + half, half);
    }

    private boolean isOneNumber(int[][] arr, int x, int y, int size) {
        int num = arr[x][y];
        for (int i = x; i < x + size; i++){
            for (int j = y; j < y + size; j++){
                if (num != arr[i][j]) return false;
            }
        }
        return true;
    }

    public static void main(String args[]) {
        int[][] arr = {
                {1, 1, 1, 1, 1, 1, 1, 1},
                {0, 1, 1, 1, 1, 1, 1, 1},
                {0, 0, 0, 0, 1, 1, 1, 1},
                {0, 1, 0, 0, 1, 1, 1, 1},
                {0, 0, 0, 0, 0, 0, 1, 1},
                {0, 0, 0, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 1, 0, 0, 1},
                {0, 0, 0, 0, 1, 1, 1, 1}
        };

        Solution1 sol = new Solution1();
        int[] answer = sol.solution(arr);
        System.out.println(Arrays.toString(answer));
    }
}
