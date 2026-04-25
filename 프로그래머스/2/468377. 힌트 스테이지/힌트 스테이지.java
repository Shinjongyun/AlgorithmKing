import java.util.*;

class Solution {

    static int answer;
    static int n;
    static int[][] cost;
    static int[][] hint;

    public int solution(int[][] cost, int[][] hint) {

        this.cost = cost;
        this.hint = hint;

        n = cost.length;
        answer = Integer.MAX_VALUE;

        int total = 1 << (n - 1);

        for (int mask = 0; mask < total; mask++) {
            int sum = 0;
            int[] count = new int[n];

            for (int stage = 0; stage < n; stage++) {

                int use = Math.min(n - 1, count[stage]);
                sum += cost[stage][use];

                if ((mask & (1 << stage)) != 0) {
                    sum += hint[stage][0];

                    for (int i = 1; i < hint[stage].length; i++) {
                        int targetStage = hint[stage][i] - 1;
                        count[targetStage]++;
                    }
                }
            }

            answer = Math.min(answer, sum);
        }

        return answer;
    }
}