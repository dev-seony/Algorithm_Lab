// 22210596 전선영

class Solution1 {
    public int solution(int[][] triangle) {
        int answer = 0;
        answer = sol(triangle);
        return answer;
    }

    public int sol(int[][] triangle) {
        int height = triangle.length;
        for (int i = height - 1; i >= 0; i--) {
            for (int j = 0; j < triangle[i].length; j++) {
                triangle[i][j] += Math.max(triangle[i + 1][j], triangle[i + 1][j + 1]);
            }
        }
        return triangle[0][0];
    }
}