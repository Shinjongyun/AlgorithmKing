import java.util.*;

class Solution {
    
    static int N;
    static int M = 6;
    static int[] answer;
    static int[][] dp;
    static boolean[] visited;
    static int[][] dice;
    static long maxWin = 0;
    
    public int[] solution(int[][] dice) {
        
        this.dice = dice;
        
        N = dice.length;
        
        visited = new boolean[N];
        
        answer = new int[N / 2];
        
        pickDice(0, 0);
        
        return answer;
    }
    
    public static void pickDice(int depth, int start) {
        if (depth == N / 2) {
            cal();
            return;
        }

        for (int i = start; i < N; i++) {
            visited[i] = true;
            pickDice(depth + 1, i + 1);
            visited[i] = false;
        }
    }
    
    public static void cal(){
        List<Integer> a = new ArrayList<>();
        List<Integer> b = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            if (visited[i]) a.add(i);
            else b.add(i);
        }

        long[] dpA = getDp(a);
        long[] dpB = getDp(b);

        long win = 0;

        for (int i = 0; i <= 500; i++) {
            for (int j = 0; j < i; j++) {
                win += dpA[i] * dpB[j];
            }
        }

        if (win > maxWin) {
            maxWin = win;

            int idx = 0;
            for (int i = 0; i < N; i++) {
                if (visited[i]) answer[idx++] = i + 1;
            }
        }
    }
    
    public static long[] getDp(List<Integer> pDice) {

        long[] dp = new long[501];
        dp[0] = 1; // 시작: 아무것도 안 굴렸을 때 합 0

        for (int i = 0; i < pDice.size(); i++) {

            int diceIndex = pDice.get(i);

            long[] newDp = new long[501];

            for (int sum = 0; sum <= 500; sum++) {

                if (dp[sum] == 0) continue;

                for (int k = 0; k < 6; k++) {
                    int nextSum = sum + dice[diceIndex][k];
                    newDp[nextSum] += dp[sum];
                }
            }

            dp = newDp; // 갱신
        }

        return dp;
    }
}