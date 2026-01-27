package Dynamic_Programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_1535_안녕 {

    static int N;
    static int[] L;
    static int[] J;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        L = new int[N+1];
        J = new int[N+1];
        dp = new int[N+1][100];

        int K =1;
        while (st.hasMoreTokens()) {
            L[K]=Integer.parseInt(st.nextToken());
            K++;
        }

        st = new StringTokenizer(br.readLine());
        K =1;
        while (st.hasMoreTokens()) {
            J[K]=Integer.parseInt(st.nextToken());
            K++;
        }

        for(int i=1;i<=N;i++){
            for(int j=1;j<=99;j++){
                if(L[i]>j) dp[i][j]=dp[i-1][j];
                else dp[i][j]=Math.max(dp[i-1][j], dp[i-1][j-L[i]]+J[i]);
            }
        }

        System.out.print(dp[N][99]);
    }
}
