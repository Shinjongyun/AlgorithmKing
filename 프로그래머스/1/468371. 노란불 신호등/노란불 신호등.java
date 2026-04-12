class Solution {
    public int solution(int[][] signals) {
        int lcmValue = 1;

        // 전체 주기의 최소공배수
        for (int[] signal : signals) {
            int cycle = signal[0] + signal[1] + signal[2];
            lcmValue = lcm(lcmValue, cycle);
        }

        // 1초부터 LCM까지 탐색
        for (int t = 1; t <= lcmValue; t++) {
            boolean allYellow = true;

            for (int[] signal : signals) {
                int g = signal[0];
                int y = signal[1];
                int r = signal[2];
                int cycle = g + y + r;

                int pos = (t - 1) % cycle;

                if (!(g <= pos && pos < g + y)) {
                    allYellow = false;
                    break;
                }
            }

            if (allYellow) return t;
        }

        return -1;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }

    private int lcm(int a, int b) {
        return a / gcd(a, b) * b;
    }
}