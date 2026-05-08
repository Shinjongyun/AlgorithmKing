import java.util.*;

class Solution {
    
    static int[][] dice;
    static int n;
    static int[] choice;
    static int[] answer;
    static int total;

    public int[] solution(int[][] dice) {
        Solution.dice = dice;
        n = dice.length;
        choice = new int[n / 2];
        answer = new int[n / 2];
        total = 0;

        back(0, 0);

        return answer;
    }
        
    public static void back(int depth, int start) {
        if (depth == n / 2) {
            cal();
            return;
        }
        
        for (int i = start; i < n; i++) {
            choice[depth] = i;
            back(depth + 1, i + 1);
        }
    }
    
    public static void cal() {
        int maxSum = 500;

        int[][] dp = new int[maxSum + 1][n / 2];
        int[][] dp1 = new int[maxSum + 1][n / 2];
        
        boolean[] selected = new boolean[n];

        for (int i = 0; i < n / 2; i++) {
            selected[choice[i]] = true;
        }

        int[] bChoice = new int[n / 2];
        int idx = 0;

        for (int i = 0; i < n; i++) {
            if (!selected[i]) {
                bChoice[idx++] = i;
            }
        }
        
        for (int i = 0; i < 6; i++) {
            int cur = dice[choice[0]][i];
            dp[cur][0]++;
        }
        
        for (int d = 1; d < n / 2; d++) {
            for (int sum = 1; sum <= maxSum; sum++) {
                for (int j = 0; j < 6; j++) {
                    int cur = dice[choice[d]][j];

                    if (sum - cur >= 0) {
                        dp[sum][d] += dp[sum - cur][d - 1];
                    }
                }
            }
        }
        
        for (int i = 0; i < 6; i++) {
            int cur = dice[bChoice[0]][i];
            dp1[cur][0]++;
        }
        
        for (int d = 1; d < n / 2; d++) {
            for (int sum = 1; sum <= maxSum; sum++) {
                for (int j = 0; j < 6; j++) {
                    int cur = dice[bChoice[d]][j];

                    if (sum - cur >= 0) {
                        dp1[sum][d] += dp1[sum - cur][d - 1];
                    }
                }
            }
        }
        
        int count = 0;

        for (int aSum = 1; aSum <= maxSum; aSum++) {
            for (int bSum = 1; bSum < aSum; bSum++) {
                count += dp[aSum][n / 2 - 1] * dp1[bSum][n / 2 - 1];
            }
        }
        
        if (total < count) {
            total = count;

            for (int i = 0; i < n / 2; i++) {
                answer[i] = choice[i] + 1;
            }
        }
    }
}