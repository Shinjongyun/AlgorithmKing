

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int T;
    static int[] answer;
    static StringBuilder sb = new StringBuilder();
    static long[] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());

        answer = new int[T];
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            answer[i] = Integer.parseInt(st.nextToken());
            dp = new long[answer[i]+1];
            make(answer[i]);
            sb.append(dp[answer[i]]).append("\n");
        }
        System.out.print(sb);
    }

    public static void make(int finish) {
        if (finish >= 1) dp[1] = 1;
        if (finish >= 2) dp[2] = 1;
        if (finish >= 3) dp[3] = 1;
        if (finish >= 4) dp[4] = 2;
        if (finish >= 5) dp[5] = 2;

        if (finish >= 6) {
            for (int i = 6; i <= finish; i++) {
                dp[i] = dp[i - 1] + dp[i - 5];
            }
        }
    }
}
