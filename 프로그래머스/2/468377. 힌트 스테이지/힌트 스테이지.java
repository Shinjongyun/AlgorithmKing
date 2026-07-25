import java.util.*;

class Solution {
    
    public int solution(int[][] cost, int[][] hint) {

        int n = cost.length;
        int answer = Integer.MAX_VALUE; 
        
        int limit = 1 << (n - 1);
        for(int mask = 0; mask<limit; mask++){
            
            int[] bag = new int [n];
            int toalCost = 0;
            
            // stage = 0이 1이고, n-2가 n-1 단계임
            for(int stage=0; stage<n; stage++){
                
                int couponNum = Math.min(bag[stage], n - 1);
                toalCost += cost[stage][couponNum];
                
                // 마지막 스테이지는 힌트 사는 거 없음
                if(stage == n -1) continue; 
                
                // 이번에 사야돼
                if((mask & 1 << stage) != 0){
                    
                    toalCost += hint[stage][0];
                    
                    for(int i=1; i<hint[stage].length; i++){
                        
                        int num = hint[stage][i] - 1; 
                        
                        bag[num]++;
                    }
                }  
                
            }
            
            answer = Math.min(answer, toalCost); 
        }
        return answer; 
    }
}