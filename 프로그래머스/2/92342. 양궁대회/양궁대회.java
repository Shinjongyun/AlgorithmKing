class Solution {

    static int maxDiff;
    static int[] answer;
    static int[] info;

    public int[] solution(int n, int[] info) {
        this.info = info;
        maxDiff = 0;
        answer = new int[]{-1};

        int[] ryan = new int[11];

        back(0, ryan, n);

        return answer;
    }

    public static void back(int depth, int[] ryan, int remain) {

        if (depth == 11) {

            // 화살 n발을 정확히 다 쏜 경우만 검사
            if (remain != 0) return;

            int diff = cal(ryan);

            if (diff <= 0) return;

            if (diff > maxDiff || (diff == maxDiff && isBetter(ryan, answer))) {
                maxDiff = diff;
                answer = ryan.clone();
            }

            return;
        }

        for (int i = 0; i <= remain; i++) {
            ryan[10 - depth] = i;
            back(depth + 1, ryan, remain - i);
            ryan[10 - depth] = 0;
        }
    }

    public static int cal(int[] ryan) {

        int apeach = 0;
        int ryanTotal = 0;

        for (int i = 0; i < 11; i++) {

            if (info[i] == 0 && ryan[i] == 0) continue;

            if (info[i] >= ryan[i]) {
                apeach += 10 - i;
            } else {
                ryanTotal += 10 - i;
            }
        }

        return ryanTotal - apeach;
    }

    public static boolean isBetter(int[] ryan, int[] answer) {

        // 아직 정답이 없는 경우
        if (answer.length == 1 && answer[0] == -1) return true;

        // 낮은 점수부터 비교
        for (int i = 10; i >= 0; i--) {
            if (ryan[i] > answer[i]) return true;
            if (ryan[i] < answer[i]) return false;
        }

        return false;
    }
}