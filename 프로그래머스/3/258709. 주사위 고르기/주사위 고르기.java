import java.util.*;

class Solution {
    
    static int n; 
    static int[][] dice;
    static int answer = 0;
    static int[] optimal;

    public int[] solution(int[][] dice) {
        
        this.dice = dice; 
        n = dice.length;
        
        int[] choice = new int[n/2];
        optimal = new int[n/2];
        back(0, choice, 0);
        
        for(int i=0; i<optimal.length; i++){
            optimal[i]++;
        }
        
        return optimal;
    }
        
    // 조합은 뭐다? 백트래킹이다.
    public static void back(int depth, int[] choice, int idx){
        
        if(depth == n/2){
            int result = cal(choice); 
            
            if(result > answer){
                optimal = choice.clone();
                answer = result; 
            }
            
            return; 
        }
        
        for(int i=idx; i<n; i++){
            choice[depth] = i;
            back(depth+1, choice, i+1);
            choice[depth] = 0;
        }
    }
    
    public static int cal(int[] choice){
        
        int m = n/2;
        // 일단 A 부터 
        int[][] dp = new int[m + 1][5001];

        dp[0][0] = 1;

        for (int i = 0; i < m; i++) {
            int curIdx = choice[i];

            for (int sum = 0; sum <= 5000; sum++) {
                if (dp[i][sum] == 0) continue;

                for (int k = 0; k < 6; k++) {
                    int curNum = dice[curIdx][k];
                    dp[i + 1][sum + curNum] += dp[i][sum];
                }
            }
        }
        
        // 그 다음 B
        int[][] dpB = new int[m + 1][5001];
        
        int[] choiceB = new int [n/2];
        boolean[] visited = new boolean [n];
        for(int i=0; i<n/2; i++){
            visited[choice[i]] = true;
        }
        int idx = 0;
        for(int i=0; i<n; i++){
            if(!visited[i]) {
                choiceB[idx] = i;
                idx++;
            }
        }
        
        dpB[0][0] = 1;

        for (int i = 0; i < m; i++) {
            int curIdx = choiceB[i];

            for (int sum = 0; sum <= 5000; sum++) {
                if (dpB[i][sum] == 0) continue;

                for (int k = 0; k < 6; k++) {
                    int curNum = dice[curIdx][k];
                    dpB[i + 1][sum + curNum] += dpB[i][sum];
                }
            }
        }
        
        int win = 0; 
        for (int aSum = 0; aSum <= 5000; aSum++) {
            if (dp[m][aSum] == 0) continue;

            for (int bSum = 0; bSum < aSum; bSum++) {
                if (dpB[m][bSum] == 0) continue;

                win += (long) dp[m][aSum] * dpB[m][bSum];
            }
        }

        return win;
    }
}