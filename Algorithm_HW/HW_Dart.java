// 22210596 전선영

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Solution {
    public int solution(String dartResult) {
        int answer = 0;
        String regex = "(10|[0-9])([SDT])([*#]?)";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(dartResult);

        int[] result = new int[3];
        int resultNum = 0;

        while(m.find()) {
            int sum_i = 0;
            int num = Integer.parseInt(m.group(1));
            String bonus = m.group(2);

            int powNum = bonus.equals("S") ? 1 : bonus.equals("D") ? 2 : 3;
            sum_i += (int)Math.pow(num, powNum);

            String ops = m.group(3);
            if (ops.equals("*")) {
                sum_i *= 2;
                if (resultNum > 0) { result[resultNum - 1] *= 2; }
            } else if (ops.equals("#")) {
                sum_i *= -1;
            }
            result[resultNum++] = sum_i;
        }

        answer = result[0] + result[1] + result[2];
        System.out.println("answer = " + answer);
        return answer;
    }
}