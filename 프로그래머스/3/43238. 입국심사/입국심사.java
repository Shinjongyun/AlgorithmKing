import java.util.*;

class Solution {
    
    static long answer = 0;
    static int N;
    static int M;
    
    public long solution(int n, int[] times) {
        N = n;
        M = times.length;
        Arrays.sort(times);
        
        long low = 1;
        long high = (long) times[M - 1] * n;
        answer = high;
        
        while (low <= high) {
            long count = 0;
            long mid = (low + high) / 2;
            
            for (int t : times) {
                count += mid / t;
                if (count >= N) break;
            }
             
            if (count >= N) {
                answer = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return answer;
    }
}