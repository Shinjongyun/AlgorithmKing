package Dynamic_Programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_12865_평범한배낭 {

    static int N;
    static int K;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int[] W = new int[N + 1]; // 무게
        int[] V = new int[N + 1]; // 가치
        dp = new int[N + 1][K + 1]; // i번째 물건까지 고려했을 때, 배낭 무게 한도가 j일 때 얻을 수 있는 최대 가치 기록

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            W[i] = Integer.parseInt(st.nextToken());
            V[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= K; j++) {
                if(W[i] > j) dp[i][j] = dp[i - 1][j];   // 배낭에 넣을 수 없는 경우
                else dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - W[i]] + V[i]);  // 넣을 수 있는 경우, 이제 기록과 비교
            }
        }

        System.out.print(dp[N][K]);
    }
}
