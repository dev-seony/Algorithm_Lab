// 22210596 전선영

class Solution {
    public int solution(int[][] routes) {
        int answer = 0;
        int cameraPosition = -30001;

        for (int i = 0; i < routes.length - 1; i++) {
            for (int j = i + 1; j < routes.length; j++) {
                if (routes[i][1] > routes[j][1]) {
                    int[] temp = routes[i];
                    routes[i] = routes[j];
                    routes[j] = temp;
                }
            }
        }

        for (int[] route : routes) {
            if (cameraPosition < route[0]) {
                answer++;
                cameraPosition = route[1];
            }
        }

        return answer;
    }
}
