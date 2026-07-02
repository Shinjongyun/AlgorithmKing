import java.util.*;

class Solution {

    static int n; 
    static int answer = Integer.MAX_VALUE;
    
    public int solution(int[][] cost, int[][] hint) {

        n = cost.length;
        
        int mask = 1 << (n - 1);
        for(int step = 0; step<mask; step++){
            
            int totalCost = 0;
            int[] coupons = new int [n];
            for(int i=0; i<n; i++){
                
                // 참고로 0은 첫번째 스테이지 의미
                // 지금 있는 쿠폰을 사용하기
                int useCount = Math.min(coupons[i], n - 1);
                totalCost += cost[i][useCount];      
                
                // 지금 사야 된다면
                if((step & 1 << i) != 0){
                    totalCost += hint[i][0];
                    for(int k=1; k<hint[i].length; k++){
                        coupons[hint[i][k] - 1]++;
                    }
                }
            }
            answer = Math.min(answer, totalCost);
            
        }
        return answer; 
    }
}