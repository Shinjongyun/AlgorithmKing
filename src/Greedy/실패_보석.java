package Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 실패_보석 {
    static int N;
    static int K;
    static Integer[] C;
    static int[] M;
    static int[] V;
    static PriorityQueue<int[]> pq;
    static int answer = 0;
    static boolean[] used;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        M = new int[N];
        V = new int[N];
        C = new Integer[K];
        used = new boolean[K];
        pq = new PriorityQueue<>((a, b) -> {
            if (a[1] == b[1]) return Integer.compare(a[0], b[0]);
            return Integer.compare(b[1], a[1]);
        });

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            M[i] = Integer.parseInt(st.nextToken());
            V[i] = Integer.parseInt(st.nextToken());
            pq.add(new int[]{M[i], V[i]});
        }

        for(int i = 0; i < K; i++){
            st = new StringTokenizer(br.readLine());
            C[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(C);

        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            for(int j = 0; j < K; j++){
                if(C[j] > cur[0] && !used[j]){
                    answer += cur[1];
                    used[j] = true;
                    break;
                }
            }
        }
        System.out.print(answer);
    }
}
