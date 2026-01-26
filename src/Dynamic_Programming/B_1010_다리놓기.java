package Dynamic_Programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_1010_다리놓기 {

    static int T;
    static StringBuilder sb = new StringBuilder();
    static int[][] dp = new int[30][30];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < 30; i++) {
            dp[i][i] = 1;
            dp[i][0] = 1;
        }

        T = Integer.parseInt(st.nextToken());
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int answer = bridge(M, N);
            sb.append(answer).append("\n");
        }
        System.out.print(sb);
    }

    private static int bridge(int M, int N) {
        for (int i = 2; i < 30; i++) {
            for (int j = 1; j < 30; j++) {
                // mCn의 조합의 공식
                dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
            }
        }
        return dp[M][N];
    }
}
