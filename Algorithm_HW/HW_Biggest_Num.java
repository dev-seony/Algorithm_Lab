// 22210596 전선영
class Solution {
    public String solution(String number, int k) {
        StringBuilder answer = new StringBuilder();
        int count = 0;

        for (int i = 0; i < number.length(); i++) {
            while (count < k && answer.length() > 0 && answer.charAt(answer.length() - 1) < number.charAt(i)) {
                answer.deleteCharAt(answer.length() - 1);
                count++;
            }
            answer.append(number.charAt(i));
        }

        return answer.substring(0, number.length() - k);
    }
}
