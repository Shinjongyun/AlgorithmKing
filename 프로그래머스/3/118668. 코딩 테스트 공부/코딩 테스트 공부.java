import java.util.*;

class Solution {

    static int INF = Integer.MAX_VALUE;

    public int solution(int alp, int cop, int[][] problems) {

        int[][] dp = new int[181][181];

        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], INF);
        }

        int targetA = alp;
        int targetC = cop;

        for (int[] p : problems) {
            targetA = Math.max(targetA, p[0]);
            targetC = Math.max(targetC, p[1]);
        }

        // 목표보다 이미 높으면 목표까지만 취급
        alp = Math.min(alp, targetA);
        cop = Math.min(cop, targetC);

        dp[alp][cop] = 0;

        for (int a = alp; a <= targetA; a++) {
            for (int c = cop; c <= targetC; c++) {

                if (dp[a][c] == INF) continue;

                // 알고력 공부
                if (a < targetA) {
                    dp[a + 1][c] =
                            Math.min(dp[a + 1][c], dp[a][c] + 1);
                }

                // 코딩력 공부
                if (c < targetC) {
                    dp[a][c + 1] =
                            Math.min(dp[a][c + 1], dp[a][c] + 1);
                }

                // 문제 풀기
                for (int[] p : problems) {

                    if (a >= p[0] && c >= p[1]) {

                        int nextA = Math.min(targetA, a + p[2]);
                        int nextC = Math.min(targetC, c + p[3]);

                        dp[nextA][nextC] =
                                Math.min(
                                        dp[nextA][nextC],
                                        dp[a][c] + p[4]
                                );
                    }
                }
            }
        }

        return dp[targetA][targetC];
    }
}