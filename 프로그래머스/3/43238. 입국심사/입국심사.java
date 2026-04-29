import java.util.*;

class Solution {
    
    static long answer = 0l;
    static int n;   // 기다리는 사람
    static int m;   // 심사관 수
    static int[] times;
    
    public long solution(int n, int[] times) {
        
        this.times = times;
        this.n = n;
        m = times.length;
        
        Arrays.sort(times);
        
        long low = 1l;
        long high = (long) times[m-1] * n;
        binary(low, high);
        
        return answer;
    }
    
    public static void binary(long low, long high){
        
        if(low>high) return;
        
        long mid = (low + high) / 2;
        
        if(cal(mid)){
            high = mid - 1;   // 되는 경우 더 큰 수에서 최소가 날 수 있기 때문
            answer = mid;
        } 
        else low = mid + 1;  // 안되면 수를 좁혀야 됨
        binary(low, high);
    }
    
    public static boolean cal(long mid){
        
        long count = 0;

        for (int time : times) {
            count += mid / time;

            // n명 이상 처리 가능하면 더 볼 필요 없음
            if (count >= n) {
                return true;
            }
        }

        return false;
    }
}