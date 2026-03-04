package Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class B_신입사원 {

    static int T;
    static int N;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        T = Integer.parseInt(st.nextToken());

        for(int i=0; i < T; i++) {
            N = Integer.parseInt(br.readLine());
            int[][] human = new int[N][2];
            for (int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine());
                human[j][0] = Integer.parseInt(st.nextToken());
                human[j][1] = Integer.parseInt(st.nextToken());
            }

            // 일단 오름 차순으로 정렬
            Arrays.sort(human, (a, b) -> Integer.compare(a[0], b[0]));

            int count = 1;
            int best = human[0][1];
            for (int j = 1; j < N; j++) {
                if (human[j][1] < best) {
                    count++;
                    best = human[j][1];
                }
            }

            sb.append(count).append("\n");
        }
        System.out.print(sb);
    }

}
