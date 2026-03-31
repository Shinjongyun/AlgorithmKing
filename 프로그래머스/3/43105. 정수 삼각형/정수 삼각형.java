import java.util.*;

class Solution {
    public int solution(int[][] triangle) {
        
        int n = triangle.length;
        int m = triangle[n-1].length;
        int[][] dp = new int[n][m];
        
        dp[0][0] = triangle[0][0];
        for(int i=1; i<n; i++){
            for(int j=0; j<triangle[i].length; j++){
                if(j==0){
                    dp[i][0] = dp[i-1][0] + triangle[i][0];
                }
                
                else if(j==triangle[i].length-1){
                    dp[i][j] = dp[i-1][j-1] + triangle[i][j];
                }
                
                else{
                    dp[i][j] = Math.max(dp[i-1][j-1], dp[i-1][j]) + triangle[i][j];
                }
            }
        }
        
        int answer = 0;
        for(int i=0; i<triangle[n-1].length; i++){
            answer = Math.max(dp[n-1][i], answer);
        }
        return answer;
    }
}