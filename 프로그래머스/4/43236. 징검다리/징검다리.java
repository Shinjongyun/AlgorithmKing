import java.util.*;

class Solution {
    
    static int[] rocks;
    static int answer;
    static int distance;
    static int n;
    
    public int solution(int distance, int[] rocks, int n) {
        
        Arrays.sort(rocks);
        
        this.rocks = rocks;
        this.distance = distance;
        this.n = n;
        this.answer = 0;

        binary(1, distance);

        return answer;
    }
    
    public static void binary(int low, int high) {
        if (low > high) return;
        
        int mid = (low + high) / 2;
        
        if (cal(mid)) {
            answer = mid;
            binary(mid + 1, high); // 가능하면 더 큰 최소 거리 도전
        } else {
            binary(low, mid - 1); // 불가능하면 거리 줄이기
        }
    }
    
    public static boolean cal(int mid) {
        int remove = 0;
        int prev = 0;
        
        for (int i = 0; i < rocks.length; i++) {
            int cur = rocks[i];
            
            if (cur - prev < mid) {
                remove++;
            } else {
                prev = cur;
            }
        }
        
        // 마지막 바위에서 도착지점까지 거리 체크
        if (distance - prev < mid) {
            remove++;
        }
        
        return remove <= n;
    }
}