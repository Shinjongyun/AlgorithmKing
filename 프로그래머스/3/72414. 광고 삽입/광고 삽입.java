import java.util.*;

class Solution {
    
    String answer;
    
    public String solution(String play_time, String adv_time, String[] logs) {
        
        int play = toVal(play_time);
        int adv = toVal(adv_time);
        
        long[] time = new long[play + 2];
        
        // 1. 차분배열 만들기
        for (String log : logs) {
            String[] arr = log.split("-");
            
            int start = toVal(arr[0]);
            int end = toVal(arr[1]);
            
            time[start] += 1;
            time[end] -= 1;
        }
        
        // 2. 첫 번째 누적합: 각 초마다 시청자 수
        for (int i = 1; i <= play; i++) {
            time[i] += time[i - 1];
        }
        
        // 3. 두 번째 누적합: 0초부터 i초까지 누적 시청시간
        for (int i = 1; i <= play; i++) {
            time[i] += time[i - 1];
        }
        
        // 광고를 0초에 넣었을 때 누적 시청시간
        long max = time[adv - 1];
        int startTime = 0;
        
        // 광고 시작 시간을 1초부터 검사
        for (int start = 1; start + adv <= play; start++) {
            int end = start + adv - 1;
            
            long sum = time[end] - time[start - 1];
            
            if (sum > max) {
                max = sum;
                startTime = start;
            }
        }
        
        answer = toTime(startTime);
        
        return answer;
    }
    
    public static String toTime(int val) {
        StringBuilder sb = new StringBuilder();
        
        int hour = val / 3600;
        int min = (val % 3600) / 60;
        int sec = val % 60;
        
        if (hour < 10) sb.append("0");
        sb.append(hour);
        sb.append(":");

        if (min < 10) sb.append("0");
        sb.append(min);
        sb.append(":");
        
        if (sec < 10) sb.append("0");
        sb.append(sec);
        
        return sb.toString();
    }
    
    public static int toVal(String time) {
        String[] t = time.split(":");
        
        int val = Integer.parseInt(t[0]) * 3600 
                + Integer.parseInt(t[1]) * 60 
                + Integer.parseInt(t[2]); 
        
        return val;
    }
}