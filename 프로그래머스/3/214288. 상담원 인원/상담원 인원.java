import java.util.*;

class Solution {

    static int K, N;
    static List<int[]>[] byType;
    static int[][] wait;
    static int answer = Integer.MAX_VALUE;

    public int solution(int k, int n, int[][] reqs) {
        K = k;
        N = n;

        byType = new ArrayList[K + 1];
        for (int i = 1; i <= K; i++) {
            byType[i] = new ArrayList<>();
        }

        for (int[] req : reqs) {
            int start = req[0];
            int time = req[1];
            int type = req[2];
            byType[type].add(new int[]{start, time});
        }

        // wait[type][m] = type 유형에 멘토 m명 배치했을 때 총 대기시간
        wait = new int[K + 1][N + 1];
        for (int type = 1; type <= K; type++) {
            for (int m = 1; m <= N; m++) {
                wait[type][m] = calcWait(byType[type], m);
            }
        }

        int[] mentors = new int[K + 1];
        Arrays.fill(mentors, 1); // 각 유형 최소 1명

        dfs(1, N - K, mentors);

        return answer;
    }

    static void dfs(int type, int remain, int[] mentors) {
        if (type == K + 1) {
            if (remain == 0) {
                int sum = 0;
                for (int i = 1; i <= K; i++) {
                    sum += wait[i][mentors[i]];
                }
                answer = Math.min(answer, sum);
            }
            return;
        }

        // 현재 type에 0명 추가 ~ remain명 추가
        for (int extra = 0; extra <= remain; extra++) {
            mentors[type] = 1 + extra;
            dfs(type + 1, remain - extra, mentors);
        }
    }

    static int calcWait(List<int[]> list, int mentorCount) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < mentorCount; i++) {
            pq.add(0);
        }

        int totalWait = 0;

        for (int[] req : list) {
            int start = req[0];
            int duration = req[1];

            int available = pq.poll();

            if (available <= start) {
                pq.add(start + duration);
            } else {
                totalWait += available - start;
                pq.add(available + duration);
            }
        }

        return totalWait;
    }
}