
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] T;
    static int[] P;
    static int N;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        T = new int[N+2];
        P = new int[N+2];
        dp = new int[N + 2];

        for (int i = 1; i < N+1; i++) {
            st = new StringTokenizer(br.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= N + 1; i++) {
            // 오늘 상담을 안 하는 경우
            if (i <= N) {
                dp[i + 1] = Math.max(dp[i + 1], dp[i]);
            }

            // 오늘 상담을 하는 경우
            if (i <= N && i + T[i] <= N + 1) {
                dp[i + T[i]] = Math.max(dp[i + T[i]], dp[i] + P[i]);
            }
        }

        System.out.print(dp[N + 1]);
    }
}
