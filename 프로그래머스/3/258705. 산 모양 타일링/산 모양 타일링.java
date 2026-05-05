import java.util.*;

class Solution {
    
    static int[][] dp;
    static int answer = 0;
    static int MOD = 10007;
    
    public int solution(int n, int[] tops) {
        
        dp = new int [n+1][2];
        
        // L을 쓸 수 있는 상태
        dp[0][0] = 1;
        
        // L을 쓸 수 없는 상태
        dp[0][1] = 0;
        
        for(int i=0; i<tops.length; i++){
            
            int cur = tops[i];
            
            if(cur == 1){
                // 무조건 R울 쓰면 안됨
                dp[i+1][0] = (dp[i][0] * 3 + dp[i][1] * 2) % MOD;
                // 무조건 R을 마름모로 써야 됨
                dp[i+1][1] = (dp[i][0] + dp[i][1]) % MOD;
            }
            if(cur == 0){

                dp[i+1][0] = (dp[i][0] * 2 + dp[i][1]) % MOD;

                dp[i+1][1] = (dp[i][0] + dp[i][1]) % MOD;
            }
        }
        
        answer = (dp[n][0] + dp[n][1]) % MOD;
        return answer;
    }
}