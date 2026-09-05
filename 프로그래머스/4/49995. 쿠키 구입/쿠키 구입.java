import java.util.*;

class Solution {
    public int solution(int[] cookie) {
        int answer = 0;
        
        int n = cookie.length;
        
        for(int m = 0; m < n - 1; m++){
            
            int l = m;
            int r = m + 1;
            
            int first = cookie[l]; 
            int second = cookie[r];
            
            while(true){
            
                if(first == second) answer = Math.max(answer, first);
                
                if(first <= second){
                    l--;
                    if(l<0) break;
                    first += cookie[l];
                }
                else{
                    r++;
                    if(r>=n) break;
                    second += cookie[r];
                }
            }
        }
        
        return answer;
    }
}