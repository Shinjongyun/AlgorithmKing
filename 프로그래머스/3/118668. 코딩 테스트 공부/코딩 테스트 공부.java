import java.util.*;

class Solution {

    public int solution(int alp, int cop, int[][] problems) {

        int maxAlp = 0;
        int maxCop = 0;

        for (int i = 0; i < problems.length; i++) {
            maxAlp = Math.max(maxAlp, problems[i][0]);
            maxCop = Math.max(maxCop, problems[i][1]);
        }

        alp = Math.min(alp, maxAlp);
        cop = Math.min(cop, maxCop);

        int[][] dp = new int[maxAlp + 1][maxCop + 1];

        for (int i = 0; i <= maxAlp; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        dp[alp][cop] = 0;

        for (int al = alp; al <= maxAlp; al++) {
            for (int co = cop; co <= maxCop; co++) {

                if (dp[al][co] == Integer.MAX_VALUE) {
                    continue;
                }

                // 알고력 공부
                if (al + 1 <= maxAlp) {
                    dp[al + 1][co] = Math.min(dp[al + 1][co], dp[al][co] + 1);
                }

                // 코딩력 공부
                if (co + 1 <= maxCop) {
                    dp[al][co + 1] = Math.min(dp[al][co + 1], dp[al][co] + 1);
                }

                // 문제 풀기
                for (int i = 0; i < problems.length; i++) {

                    int curAlp = problems[i][0];
                    int curCop = problems[i][1];
                    int rwAlp = problems[i][2];
                    int rwCop = problems[i][3];
                    int cost = problems[i][4];

                    if (al >= curAlp && co >= curCop) {
                        int nextAlp = Math.min(maxAlp, al + rwAlp);
                        int nextCop = Math.min(maxCop, co + rwCop);

                        dp[nextAlp][nextCop] =
                                Math.min(dp[nextAlp][nextCop], dp[al][co] + cost);
                    }
                }
            }
        }

        return dp[maxAlp][maxCop];
    }
}