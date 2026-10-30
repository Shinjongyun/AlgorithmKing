import java.util.*;

class Solution {
    
    static int[] rocks;
    static int distance, n;
    
    public int solution(int distance, int[] rocks, int n) {
        int answer = 0;
        this.rocks = rocks;
        this.n = n;
        this.distance = distance;
    
        Arrays.sort(rocks);
        
        int low = 1; 
        int high = distance;
        while(low <= high){
            
            int mid = (low + high) / 2; 
            
            if(cal(mid)){
                answer = Math.max(mid, answer);
                low = mid + 1;
            }
            else{
                high = mid - 1;
            }
        }
        
        return answer;
    }
    
    public static boolean cal(int mid){
        
        int count = 0;
        int last = 0;
        
        for(int i=0; i<=rocks.length; i++){
            
            int dist; 
            if(i== rocks.length) dist = distance - last; 
            else dist = rocks[i] - last; 
            
            if(dist < mid) {
                count++;
            }
            else {
                if(i < rocks.length) last = rocks[i];
            }
            if(count > n) return false;
        }
        return true;
    }
}