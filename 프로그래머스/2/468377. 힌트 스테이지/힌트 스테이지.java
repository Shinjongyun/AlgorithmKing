import java.util.*;

class Solution {

    public int solution(int[][] cost, int[][] hint) {

        int n = cost.length;
        int total = 1 << (n - 1);
        int answer = Integer.MAX_VALUE;

        for (int mask = 0; mask < total; mask++) {

            int[] hintCount = new int[n];
            int totalCost = 0;

            for (int i = 0; i < n; i++) {

                // 이번에 최대로 쓸 수 있는 힌트권의 수
                int use = Math.min(hintCount[i], n - 1);
                totalCost += cost[i][use];

                if ((mask & (1 << i)) != 0) {

                    totalCost += hint[i][0];

                    for (int j = 1; j < hint[i].length; j++) {
                        int target = hint[i][j] - 1;
                        hintCount[target]++;
                    }
                }
            }

            answer = Math.min(answer, totalCost);
        }

        return answer;
    }
}