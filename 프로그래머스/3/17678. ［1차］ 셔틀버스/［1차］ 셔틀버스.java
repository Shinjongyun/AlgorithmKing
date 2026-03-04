import java.util.*;

class Solution {
    
    static PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
    static String answer;
    static Integer answerTime;
    
    public String solution(int n, int t, int m, String[] timetable) {

        for(int i=0; i<timetable.length; i++){
            int time = toValue(timetable[i]);
            pq.add(time);
        }
        
        int start = 60 * 9;
        
        // 루프 돌면서 버스 태우기
        for (int i = 0; i < n; i++) {
            int busTime = start + i * t;   // 버스 도착 시간
            int count = 0;
            int lastBoarded = -1;

            // 이번 셔틀에 탈 수 있는 사람 최대 m명 태우기
            while (count < m && !pq.isEmpty() && pq.peek() <= busTime) {
                lastBoarded = pq.poll();
                count++;
            }

            // 마지막 셔틀이면 콘 도착시간 결정
            if (i == n - 1) {
                if (count < m) {
                    // 자리가 남으면 콘은 셔틀 도착 시간에 와도 됨(맨 뒤 서도 탑승 가능)
                    answerTime = busTime;
                } else {
                    // 자리가 꽉 차면, 마지막으로 탄 크루보다 1분 빨리 와야 맨 뒤로 서도 탑승 가능
                    answerTime = lastBoarded - 1;
                }
                return toTime(answerTime);
            }
        }
        return toTime(answerTime);
    }
    
    
    public static int toValue(String time){
        String[] times = time.split(":");
        int total = 0;
        total += Integer.parseInt(times[0]) * 60;
        total += Integer.parseInt(times[1]);
        return total;
    }
    public static String toTime(int value){
        int hour = value / 60;
        int min = value % 60;

        String total;
        if(hour < 10){
            total = "0" + hour;
        } else{
            total = String.valueOf(hour);
        }

        total += ":";

        if(min < 10){
            total += "0" + min;
        } else{
            total += min;
        }

        return total;
    }
}