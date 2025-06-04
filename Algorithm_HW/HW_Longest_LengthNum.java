// 22210596 전선영

import java.util.Scanner;

class Solution2 {
    public Solution2() {
        Scanner sc = new Scanner(System.in);
        A = sc.nextLine();
        B = sc.nextLine();
    }

    String A, B;

    public void sol() {
        char[] result;

        char[] chA = A.toCharArray();
        char[] chB = B.toCharArray();
        int lenA = A.length();
        int lenB = B.length();
        int[][] answer = new int[lenA + 1][lenB + 1];

        for (int i = 1; i <= lenA; i++) {
            for (int j = 1; j <= lenB; j++) {
                if (chA[i - 1] == chB[j - 1]) {
                    answer[i][j] = answer[i - 1][j - 1] + 1;
                }
                else {
                    answer[i][j] = Math.max(answer[i - 1][j], answer[i][j - 1]);
                }
            }
        }

        result = new char[answer[lenA][lenB]];
        int i = lenA, j = lenB;
        int idx = 0;

        while (i > 0 && j > 0) {
            if (A.charAt(i - 1) == B.charAt(j - 1)) {
                result[idx++] = A.charAt(i - 1);
                i--;
                j--;
            } else if (answer[i - 1][j] >= answer[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }
        for (int k = result.length - 1; k >= 0; k--) {
            System.out.print(result[k]);
        }
        System.out.println();
        System.out.println(answer[lenA][lenB]);

    }



    public static void main(String[] args) {
        Solution2 sol = new Solution2();
        sol.sol();
    }
}