

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] P;
    static int N;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        P = new int[N + 1];
        dp = new int[N + 1];

        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            P[i] = Integer.parseInt(st.nextToken());
        }

        dp[1] = P[1];
        if(N>1){
            dp[2] = P[1] + P[2];
        }
        for(int i = 3; i <= N; i++){
            dp[i] = Math.max(dp[i-2], dp[i-3] + P[i-1]) + P[i];
        }
        System.out.print(dp[N]);
    }
}
