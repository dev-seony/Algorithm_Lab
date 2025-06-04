// 22210596 전선영

import java.util.ArrayList;
import java.util.Scanner;

class Solution2 {
    int n, k;
    ArrayList result = new ArrayList<Object>();
    int[] arr;
    int[] parseArr;

    public Solution2(){
        input();
        combination(0, k);
        System.out.print(result);
    }

    private void input() {
        Scanner sc = new Scanner(System.in);
        System.out.print("정수 n과 k를 입력? ");
        n = sc.nextInt();
        k = sc.nextInt();
        arr = new int[n];
        for (int i = 0; i < n ; i++) arr[i] = i + 1;
        parseArr = new int[n];
    }

    private void combination(int location, int k) {
        if (k == 0) {
            ArrayList<Integer> addArr = new ArrayList<>();
            for (int i = 0 ; i < n; i++) {
                if (parseArr[i] == 1) addArr.add(arr[i]);
            }
            result.add(addArr);
            return;
        }

        if (location == n) return;

        parseArr[location] = 1;
        combination(location + 1, k - 1);

        parseArr[location] = 0;
        combination(location + 1, k);
    }

    public static void main(String[] args) {
        Solution2 sol = new Solution2();
    }
}
