package Dynamic_Programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B_1003_Fibonacci {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        int MAX = 40;
        int[] zero = new int[MAX + 1];
        int[] one = new int[MAX + 1];

        zero[0] = 1; one[0] = 0;
        zero[1] = 0; one[1] = 1;

        for (int i = 2; i <= MAX; i++) {
            zero[i] = zero[i - 1] + zero[i - 2];
            one[i] = one[i - 1] + one[i - 2];
        }

        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            int n = Integer.parseInt(br.readLine());
            sb.append(zero[n]).append(" ").append(one[n]).append("\n");
        }

        System.out.print(sb);
    }
}