import java.util.*;

class Solution {
    
    static int[] choice;
    static int n;
    static int answer = 0;
    static int[] answerP;
    static boolean[] visited;
    static int length;
    static int[][] dice;

    // 최대 6^(10)이기 때문에 그냥 완전탐색 X
    // 비트마스킹은 그때 힌트권에서 이경우는 choice가 아님   -> 그냥 주사위 조합 + 누적합
    public int[] solution(int[][] dice) {
        
        this.dice = dice;
        length = dice.length;
        n = dice.length / 2;
        choice = new int[n];
        answerP = new int[n];
        visited = new boolean [dice.length];
        
        back(0, 0);
        return answerP;
    }
    
    public static void back(int depth, int idx){
        
        if(depth == n){
            int count = cal();
            if(count > answer){
                answer = count;
                for(int i=0; i<n; i++){
                    answerP[i] = choice[i] + 1;
                }
            }
            return;
        }
        
        for(int i=idx; i<length; i++){
            choice[depth] = i;
            visited[i] = true;
            back(depth+1, i+1);
            choice[depth] = 0;
            visited[i] = false;
        }
    }
    
    // choice는 주사위의 인데스를 저장 예를 들어 #1, #4 이면 0, 3을 저장
    public static int cal(){
    
        int[][] dp = new int[n][501];
        int[][] dp1 = new int[n][501];

        int[] notChoice = new int[n];
        int idx = 0;

        for (int i = 0; i < length; i++) {
            if (!visited[i]) {
                notChoice[idx++] = i;
            }
        }

        // A 첫 번째 주사위
        for(int i = 0; i < 6; i++){
            int j = dice[choice[0]][i];
            dp[0][j]++;
        }

        // A DP
        for(int i = 1; i < n; i++){
            for(int j = 0; j < 501; j++){
                for(int k = 0; k < 6; k++){
                    int prev = j - dice[choice[i]][k];

                    if(prev >= 0){
                        dp[i][j] += dp[i - 1][prev];
                    }
                }
            }
        }

        // B 첫 번째 주사위
        for(int i = 0; i < 6; i++){
            int j = dice[notChoice[0]][i];
            dp1[0][j]++;
        }

        // B DP
        for(int i = 1; i < n; i++){
            for(int j = 0; j < 501; j++){
                for(int k = 0; k < 6; k++){
                    int prev = j - dice[notChoice[i]][k];

                    if(prev >= 0){
                        dp1[i][j] += dp1[i - 1][prev];
                    }
                }
            }
        }

        // B 누적합
        int[] prefixB = new int[501];
        prefixB[0] = dp1[n - 1][0];

        for(int i = 1; i < 501; i++){
            prefixB[i] = prefixB[i - 1] + dp1[n - 1][i];
        }

        int count = 0;

        // A 합이 i일 때, B 합이 i보다 작으면 A 승리
        for(int i = 1; i < 501; i++){
            count += dp[n - 1][i] * prefixB[i - 1];
        }

        return count;
    }
}