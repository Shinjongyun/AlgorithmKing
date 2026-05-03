import java.util.*;

class Solution {

    public String solution(String new_id) {

        // 1단계
        String one = new_id.toLowerCase();

        // 2단계
        StringBuilder two = new StringBuilder();

        for (int i = 0; i < one.length(); i++) {
            char cur = one.charAt(i);

            if ((cur >= 'a' && cur <= 'z') ||
                (cur >= '0' && cur <= '9') ||
                cur == '-' || cur == '_' || cur == '.') {
                two.append(cur);
            }
        }

        // 3단계
        StringBuilder three = new StringBuilder();

        for (int i = 0; i < two.length(); i++) {
            char cur = two.charAt(i);

            if (cur == '.') {
                if (three.length() > 0 && three.charAt(three.length() - 1) == '.') {
                    continue;
                }
            }

            three.append(cur);
        }

        // 4단계
        StringBuilder four = new StringBuilder(three.toString());

        if (four.length() > 0 && four.charAt(0) == '.') {
            four.deleteCharAt(0);
        }

        if (four.length() > 0 && four.charAt(four.length() - 1) == '.') {
            four.deleteCharAt(four.length() - 1);
        }

        // 5단계
        if (four.length() == 0) {
            four.append("a");
        }

        // 6단계
        if (four.length() >= 16) {
            four = new StringBuilder(four.substring(0, 15));

            if (four.charAt(four.length() - 1) == '.') {
                four.deleteCharAt(four.length() - 1);
            }
        }

        // 7단계
        while (four.length() <= 2) {
            four.append(four.charAt(four.length() - 1));
        }

        return four.toString();
    }
}