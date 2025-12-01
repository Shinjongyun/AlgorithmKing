package Dynamic_Programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class B_9095_1and2and3 {

    static int N;
    static List<Integer> test;
    static int[] answer;
    static int[] dp;
;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        test = new ArrayList<>();
        answer = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            test.add(Integer.parseInt(st.nextToken()));
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            dp = new int[test.get(i) + 1];
            if (test.get(i) >= 1) dp[1] = 1;
            if (test.get(i) >= 2) dp[2] = 2;
            if (test.get(i) >= 3) dp[3] = 4;
            answer[i] = getNum(test.get(i), i);
            sb.append(answer[i]).append("\n");
        }
        System.out.print(sb);
    }


    static int getNum(int x, int i) {
        if(x==1) answer[i]=dp[1];
        if(x==2) answer[i]=dp[2];
        if(x==3) answer[i]=dp[3];
        if(x>=4) {
            for (int j = 4; j <= x; j++) {
                dp[j] = (dp[j-1] + dp[j-2] + dp[j-3]);
            }
            answer[i]=dp[x];
        }
        return answer[i];
    }
}
