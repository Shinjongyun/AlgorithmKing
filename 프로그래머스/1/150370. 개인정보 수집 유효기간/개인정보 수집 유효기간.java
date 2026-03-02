import java.util.*;
import java.io.*;

class Solution {
    
    static int[] answer;
    
    public int[] solution(String today, String[] terms, String[] privacies) {
        Map<String, Integer> termMap = new HashMap<>();
        for (String t : terms) {
            String[] parts = t.split(" ");
            termMap.put(parts[0], Integer.parseInt(parts[1]));
        }

        int todayVal = toDays(today);
        List<Integer> res = new ArrayList<>();

        for (int i = 0; i < privacies.length; i++) {
            String[] parts = privacies[i].split(" ");
            String date = parts[0];
            String type = parts[1];

            int start = toDays(date);
            int months = termMap.get(type);

            int expireLast = start + months * 28 - 1;  // 마지막 유효 날짜 계산
            if (expireLast < todayVal) {     // 오늘이 유효 날짜보다 크다면?
                res.add(i + 1); 
            }
        }
        answer = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            answer[i] = res.get(i);
        }
        return answer;
    }

    private int toDays(String ymd) {
        String[] p = ymd.split("\\.");
        int y = Integer.parseInt(p[0]);
        int m = Integer.parseInt(p[1]);
        int d = Integer.parseInt(p[2]);

        return y * 12 * 28 + m * 28 + d;
    }
}